package com.kfccorp.kfcorder.viewholders

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.kfccorp.kfcorder.databinding.ProductBinding
import com.kfccorp.kfcorder.interfaces.*

@SuppressLint("SetTextI18n")
class ProductsViewHolder(
    private val productBinding: ProductBinding,
    private val productClickListener: ProductClickListener
) :
    RecyclerView.ViewHolder(productBinding.root)
{
    fun bindProduct(product: ProductInterface) {
        if (product.getQuantity() > 0) {
            productBinding.productName.text = product.getName()
            productBinding.cvProduct.setOnClickListener {
                productClickListener.onProductClick(bindingAdapterPosition)
            }
        } else productBinding.productName.text = "${product.getName()} - Out of stock"
        productBinding.productImg.setImageResource(product.getImage())
        productBinding.productPrice.text = "₱${"%,d".format(product.getPrice())}.00"
    }
}