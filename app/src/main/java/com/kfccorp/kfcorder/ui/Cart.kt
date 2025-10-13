package com.kfccorp.kfcorder.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import android.widget.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kfccorp.kfcorder.R
import com.kfccorp.kfcorder.adapters.CartAdapter
import com.kfccorp.kfcorder.databinding.CartBinding
import com.kfccorp.kfcorder.interfaces.CartClickListener
import com.kfccorp.kfcorder.models.*

@SuppressLint("SetTextI18n")
class Cart : Fragment(), CartClickListener {

    private lateinit var binding: CartBinding
    private var total : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CartBinding.inflate(inflater, container, false)

        binding.btnBack.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        binding.btnPayment.setOnClickListener {
            Global.productPosition = -1
            Global.categoryPosition = 0
            Global.productList = emptyList()
            Global.previous = 11
            findNavController().navigate(CartDirections.actionCartToCheckout())                         // NAVIGATE TO CHECKOUT
        }

        total = 0
        for (cartItem in cartList) {
            total += cartItem.total                                                                     // SUMMATION OF ALL CART LIST ITEM PRICE
        }
        if (total > 0) binding.tvTotalValue.text = "₱${"%,d".format(total)}.00"

        binding.rvCartList.layoutManager = LinearLayoutManager(requireActivity())                       // SET THE LAYOUT OF CART ITEM LIST
        binding.rvCartList.adapter = CartAdapter(cartList, this)                           // SET ADAPTER FOR CART ITEM LIST

        return binding.root
    }

    override fun onRemoveClick(                                                                         // HANDLE REDUCE ITEM AMOUNT CLICK ON CART ITEMS
        position: Int,
        amount: Int,
        price: Int,
        tvAmount: TextView,
        tvPrice: TextView,
        btnRemove : ImageButton,
        btnAdd: ImageButton
    ) {
        btnAdd.setImageResource(R.drawable.add_cart)
        var amount1 = amount
        var price1 = price
        if (amount1 > 1) {
            amount1 -= 1
            price1 *= amount1
            val total1 = price1
            cartList[position].amount = amount1                                                         // SAVE NEW VALUES TO ITEM IN CART LIST
            cartList[position].total = total1
            tvAmount.text = amount1.toString()
            tvPrice.text = "₱${"%,d".format(total1)}.00"
            total = 0
            for (cartItem in cartList) {
                total += cartItem.total                                                                 // SUMMATION OF ALL CART LIST ITEM PRICE
            }
            binding.tvTotalValue.text = "₱${"%,d".format(total)}.00"
        }
        if (amount1 == 1) btnRemove.setImageResource(R.drawable.remove_cart_gray)
    }

    override fun onAddClick(                                                                            // HANDLE ADD ITEM COUNT CLICK ON CART ITEMS
        position: Int,
        amount: Int,
        price: Int,
        tvAmount: TextView,
        tvPrice: TextView,
        btnRemove : ImageButton,
        btnAdd: ImageButton
    ) {
        btnRemove.setImageResource(R.drawable.remove_cart)
        var amount1 = amount
        var price1 = price
        if (amount1 + 1 <= cartList[position].quantity) {
            amount1 += 1
            price1 *= amount1
            val total1 = price1
            cartList[position].amount = amount1                                                             // SAVE NEW VALUE TO ITEM IN CART LIST
            cartList[position].total = total1
            tvAmount.text = amount1.toString()
            tvPrice.text = "₱${"%,d".format(total1)}.00"
            total = 0
            for (cartItem in cartList) {
                total += cartItem.total                                                                     // SUMMATION OF ALL CART LIST ITEM PRICE
            }
            binding.tvTotalValue.text = "₱${"%,d".format(total)}.00"
        }
        if (amount1 == cartList[position].quantity)
            btnAdd.setImageResource(R.drawable.add_cart_gray)
    }

    override fun onDeleteClick(position: Int) {                                                         // HANDE DELETE CART ITEM CLICK ON CART ITEMS
        cartList.removeAt(position)                                                                     // DELETE ITEM FROM CART LIST
        binding.rvCartList.adapter = CartAdapter(cartList, this)                           // UPDATE CART ITEMS
        if (cartList.isEmpty()) activity?.supportFragmentManager?.popBackStack()                        // RETURN TO MENU WHEN CART IS EMPTY
        total = 0
        for (cartItem in cartList) {
            total += cartItem.total                                                                     // SUMMATION OF ALL CART LIST ITEM PRICE
        }
        if (total > 0) {
            binding.tvTotalValue.text = "₱${"%,d".format(total)}.00"
        } else {
            binding.tvTotalValue.text = "Total"
        }
    }

    override fun onEditClick(position: Int) {
        Global.isEdit = 1
        Global.cartItemPos = position
        findNavController().navigate(CartDirections.actionCartToDetails())
    }
}