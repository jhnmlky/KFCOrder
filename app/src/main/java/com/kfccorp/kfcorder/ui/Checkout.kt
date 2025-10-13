package com.kfccorp.kfcorder.ui

import android.annotation.SuppressLint
import android.os.*
import androidx.fragment.app.Fragment
import android.view.*
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.database.*
import com.kfccorp.kfcorder.databinding.CheckoutBinding
import com.kfccorp.kfcorder.models.*
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@SuppressLint("SetTextI18n")
class Checkout : Fragment() {

    private lateinit var binding: CheckoutBinding
    private lateinit var countDownTimer : CountDownTimer
    private var total = 0F
    private lateinit var database: FirebaseDatabase
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = Firebase.database
        dbRef = database.reference
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CheckoutBinding.inflate(inflater, container, false)

        binding.tvExpire.text = "This order will EXPIRE in\napproximately " +
                "${Global.EXPIRE}-${Global.EXPIRE + 2} MINUTES"

        val orderID = LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmss"))
        val time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
        val cart = mutableListOf<CartItem>()
        for (item in cartList)
            cart.add(CartItem(item.id, item.name, item.customs, item.price,
                item.amount, item.total))
        for (item in cart) total += item.total!!
        val order = Order(orderID, total, time, cart)
        dbRef.child("Orders").child(orderID).setValue(order)
            .addOnSuccessListener {
                for (item in cart) updateDatabase(item.id!!, item.amount!!)
                binding.tvOrderID.text = orderID
                cartList.clear()
                countDownTimer = object : CountDownTimer(5000, 1000) {     // TIMER FOR FRAGMENT CLOSING
                    override fun onTick(p0: Long) {}
                    override fun onFinish() {
                        activity?.supportFragmentManager?.popBackStack()
                        activity?.supportFragmentManager?.popBackStack()
                        activity?.supportFragmentManager?.popBackStack()
                        activity?.supportFragmentManager?.popBackStack()
                    }
                }
                countDownTimer.start()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Error: ${it.message}. Please retry", Toast.LENGTH_SHORT).show()
                activity?.supportFragmentManager?.popBackStack()
            }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }

    private fun updateDatabase(id: String, amount: Int) {
        dbRef.child("temp_Inventory").child(id).child("quantity")
            .runTransaction( object : Transaction.Handler {
                override fun doTransaction(currentData: MutableData): Transaction.Result {
                    val quantity = currentData.getValue(Int::class.java)
                    currentData.value = quantity!! -amount
                    return Transaction.success(currentData)
                }
                override fun onComplete(
                    error: DatabaseError?,
                    committed: Boolean,
                    currentData: DataSnapshot?
                ) {}
            })
    }
}