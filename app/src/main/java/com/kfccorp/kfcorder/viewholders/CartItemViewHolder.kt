package com.kfccorp.kfcorder.viewholders

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kfccorp.kfcorder.R
import com.kfccorp.kfcorder.databinding.CartMenuBinding
import com.kfccorp.kfcorder.interfaces.CartClickListener
import com.kfccorp.kfcorder.models.CartListItem

@SuppressLint("SetTextI18n")
class CartItemViewHolder(
    private var cartMenuBinding: CartMenuBinding,
    private val cartClickListener: CartClickListener
) : RecyclerView.ViewHolder(cartMenuBinding.root)
{
    fun bindCartMenu(cartList : CartListItem) {
        cartMenuBinding.tvProduct.text = cartList.name
        cartMenuBinding.tvCustom.text = cartList.customs.filterNotNull().joinToString(", ")
        if (cartList.customs.isEmpty()) cartMenuBinding.tvCustom.visibility = View.GONE
        cartMenuBinding.tvPrice.text = "₱${"%,d".format(cartList.total)}.00"
        cartMenuBinding.tvAmount.text = cartList.amount.toString()
        if (cartList.amount == 1) cartMenuBinding.btnRemove.setImageResource(R.drawable.remove_cart_gray)
        cartMenuBinding.btnAdd.setOnClickListener {
            cartClickListener.onAddClick(
                bindingAdapterPosition,
                cartList.amount,
                cartList.price,
                cartMenuBinding.tvAmount,
                cartMenuBinding.tvPrice,
                cartMenuBinding.btnRemove,
                cartMenuBinding.btnAdd
            )
        }
        cartMenuBinding.btnRemove.setOnClickListener {
            cartClickListener.onRemoveClick(
                bindingAdapterPosition,
                cartList.amount,
                cartList.price,
                cartMenuBinding.tvAmount,
                cartMenuBinding.tvPrice,
                cartMenuBinding.btnRemove,
                cartMenuBinding.btnAdd
            )
        }
        cartMenuBinding.btnEdit.setOnClickListener {
            cartClickListener.onEditClick(bindingAdapterPosition)
        }
        cartMenuBinding.btnDelete.setOnClickListener {
            cartClickListener.onDeleteClick(bindingAdapterPosition)
        }
    }
}