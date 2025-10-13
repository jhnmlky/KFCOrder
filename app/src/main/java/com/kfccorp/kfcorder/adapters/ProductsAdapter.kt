package com.kfccorp.kfcorder.adapters

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.kfccorp.kfcorder.databinding.ProductBinding
import com.kfccorp.kfcorder.interfaces.*
import com.kfccorp.kfcorder.viewholders.ProductsViewHolder

class ProductsAdapter(
    val products : List<ProductInterface>,
    private val productClickListener: ProductClickListener
)
    : RecyclerView.Adapter<ProductsViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = ProductBinding.inflate(from, parent,false)
        return ProductsViewHolder(binding, productClickListener)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bindProduct(products[position])
    }
}