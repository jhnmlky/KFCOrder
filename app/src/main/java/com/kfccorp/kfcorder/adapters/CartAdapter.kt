package com.kfccorp.kfcorder.adapters

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.kfccorp.kfcorder.databinding.CartItemBinding
import com.kfccorp.kfcorder.interfaces.CartClickListener
import com.kfccorp.kfcorder.models.CartListItem
import com.kfccorp.kfcorder.viewholders.CartViewHolder

class CartAdapter (
    private val cartListItems : List<CartListItem>,
    private val cartClickListener: CartClickListener
)
    : RecyclerView.Adapter<CartViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = CartItemBinding.inflate(from, parent, false)
        return CartViewHolder(binding, cartClickListener)
    }

    override fun getItemCount(): Int = cartListItems.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bindCartItem(cartListItems[position])
    }

}