package com.kfccorp.kfcorder.adapters

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.kfccorp.kfcorder.databinding.CategoryBinding
import com.kfccorp.kfcorder.interfaces.CategoryClickListener
import com.kfccorp.kfcorder.models.*
import com.kfccorp.kfcorder.viewholders.CategoryViewHolder

class CategoryAdapter (
    private val categories: ArrayList<Category>,
    private val categoryClickListener: CategoryClickListener,
    private val layoutManager : CircularLinearLayoutManager
) : RecyclerView.Adapter<CategoryViewHolder>() {

    private val viewHolders = mutableListOf<CategoryViewHolder?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = CategoryViewHolder(binding, categoryClickListener, layoutManager)
        viewHolders.add(viewHolder)
        return viewHolder
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindCategory(categories[position])
        if (position == categories.size-1) {
            layoutManager.scrollToPositionWithOffset(4, -30)
            if (Global.isClick) {
                holder.smoothScrollToPosition(Global.previous)
                Global.isClick = false
            }
        } else if (position == 0) {
            layoutManager.scrollToPositionWithOffset(13, 0)
            if (Global.isClick) {
                holder.smoothScrollToPosition(Global.previous)
                Global.isClick = false
            }
        }
    }

    fun getCategoryViewHolders(position: Int): List<CategoryViewHolder> {
        val validViewHolders = mutableListOf<CategoryViewHolder>()
        for (viewHolder in viewHolders) {
            if (viewHolder != null && viewHolder.bindingAdapterPosition % categoryList.size == position % categoryList.size) {
                validViewHolders.add(viewHolder)
            }
        }
        return validViewHolders
    }
}