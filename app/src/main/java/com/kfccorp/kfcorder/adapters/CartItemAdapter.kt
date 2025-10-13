package com.kfccorp.kfcorder.adapters

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.kfccorp.kfcorder.databinding.CartMenuBinding
import com.kfccorp.kfcorder.interfaces.CartClickListener
import com.kfccorp.kfcorder.models.CartListItem
import com.kfccorp.kfcorder.viewholders.CartItemViewHolder

class CartItemAdapter(
    private val cartListItems : List<CartListItem>,
    private val cartClickListener: CartClickListener
)
    : RecyclerView.Adapter<CartItemViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = CartMenuBinding.inflate(from, parent, false)
        return CartItemViewHolder(binding, cartClickListener)
    }

    override fun getItemCount(): Int = cartListItems.size

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        holder.bindCartMenu(cartListItems[position])
    }
}