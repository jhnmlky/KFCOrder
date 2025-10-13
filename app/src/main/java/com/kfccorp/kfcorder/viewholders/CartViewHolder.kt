package com.kfccorp.kfcorder.viewholders

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kfccorp.kfcorder.R
import com.kfccorp.kfcorder.databinding.CartItemBinding
import com.kfccorp.kfcorder.interfaces.CartClickListener
import com.kfccorp.kfcorder.models.CartListItem

@SuppressLint("SetTextI18n")
class CartViewHolder(
    private var cartItemBinding: CartItemBinding,
    private val cartClickListener: CartClickListener
) : RecyclerView.ViewHolder(cartItemBinding.root)
{
    fun bindCartItem(cartList: CartListItem) {
        cartItemBinding.tvProduct.text = cartList.name
        cartItemBinding.tvCustom.text = cartList.customs.filterNotNull().joinToString(", ")
        if (cartList.customs.isEmpty()) cartItemBinding.tvCustom.visibility = View.GONE
        cartItemBinding.tvPrice.text = "₱${"%,d".format(cartList.total)}.00"
        cartItemBinding.tvAmount.text = cartList.amount.toString()
        if (cartList.amount == 1) cartItemBinding.btnRemove.setImageResource(R.drawable.remove_cart_gray)
        cartItemBinding.btnAdd.setOnClickListener {
            cartClickListener.onAddClick(
                bindingAdapterPosition,
                cartList.amount,
                cartList.price,
                cartItemBinding.tvAmount,
                cartItemBinding.tvPrice,
                cartItemBinding.btnRemove,
                cartItemBinding.btnAdd
            )
        }
        cartItemBinding.btnRemove.setOnClickListener {
            cartClickListener.onRemoveClick(
                bindingAdapterPosition,
                cartList.amount,
                cartList.price,
                cartItemBinding.tvAmount,
                cartItemBinding.tvPrice,
                cartItemBinding.btnRemove,
                cartItemBinding.btnAdd
            )
        }
        cartItemBinding.btnEdit.setOnClickListener {
            cartClickListener.onEditClick(bindingAdapterPosition)
        }
        cartItemBinding.btnDelete.setOnClickListener {
            cartClickListener.onDeleteClick(bindingAdapterPosition)
        }
    }
}