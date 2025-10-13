package com.kfccorp.kfcorder.interfaces

import com.kfccorp.kfcorder.viewholders.CategoryViewHolder

interface CategoryClickListener
{
    fun onCategoryClick(position : Int)
    fun getViewHolder(position: Int): List<CategoryViewHolder>
}