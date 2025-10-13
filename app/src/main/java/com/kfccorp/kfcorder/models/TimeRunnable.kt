package com.kfccorp.kfcorder.models

import android.os.Handler
import com.google.firebase.Firebase
import com.google.firebase.database.*
import java.time.*

class TimeRunnable(
    private val handler: Handler,
    private val expire: Int
) : Runnable
{
    private val database = Firebase.database
    private val dbRef = database.reference

    override fun run() {
        dbRef.child("Orders").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (item in snapshot.children) {
                        val order = item.getValue(Order::class.java)
                        dbRef.child("Orders").child(order!!.id!!).runTransaction(object : Transaction.Handler {
                            override fun doTransaction(currentData: MutableData): Transaction.Result {
                                val data = currentData.getValue(Order::class.java)
                                if (Duration.between(LocalTime.parse(data!!.time), LocalTime.now()).toMinutes() >= expire) {
                                    currentData.value = null
                                    for (product in data.order!! as List<*>) {
                                        dbRef.child("temp_Inventory").child((product as CartItem).id!!).child("quantity")
                                            .runTransaction(object : Transaction.Handler {
                                                override fun doTransaction(currentData: MutableData): Transaction.Result {
                                                    val quantity = currentData.getValue(Int::class.java)
                                                    currentData.value = quantity!! + product.amount!!
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
            }
            override fun onCancelled(error: DatabaseError) {}
        })
        handler.postDelayed(this, 5 * 30 * 1000)
    }
}