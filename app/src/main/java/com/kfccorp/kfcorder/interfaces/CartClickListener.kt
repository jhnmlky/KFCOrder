package com.kfccorp.kfcorder.interfaces

import android.widget.*

interface CartClickListener {
    fun onRemoveClick(
        position: Int,
        amount: Int,
        price: Int,
        tvAmount: TextView,
        tvPrice: TextView,
        btnRemove : ImageButton,
        btnAdd: ImageButton
    )

    fun onAddClick(
        position: Int,
        amount: Int,
        price: Int,
        tvAmount: TextView,
        tvPrice: TextView,
        btnRemove : ImageButton,
        btnAdd: ImageButton
    )

    fun onDeleteClick(position: Int)

    fun onEditClick(position : Int)
}