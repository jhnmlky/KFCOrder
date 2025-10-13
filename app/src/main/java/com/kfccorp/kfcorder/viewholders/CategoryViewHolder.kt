package com.kfccorp.kfcorder.viewholders

import android.graphics.Typeface
import android.util.*
import android.view.View
import androidx.recyclerview.widget.*
import com.kfccorp.kfcorder.R
import com.kfccorp.kfcorder.databinding.CategoryBinding
import com.kfccorp.kfcorder.interfaces.CategoryClickListener
import com.kfccorp.kfcorder.models.*

class CategoryViewHolder(
    private var categoryBinding: CategoryBinding,
    private val categoryClickListener: CategoryClickListener,
    private val layoutManager: CircularLinearLayoutManager
) : RecyclerView.ViewHolder(categoryBinding.root) {

    fun bindCategory(category: Category) {
        categoryBinding.categoryImg.setImageResource(category.img)
        categoryBinding.categoryName.text = category.name

        if (bindingAdapterPosition % categoryList.size == Global.previous % categoryList.size) {
            categoryBinding.cvCategory.radius = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,10f, itemView.resources.displayMetrics)
            categoryBinding.categoryName.setTypeface(null, Typeface.BOLD)
            categoryBinding.layout.setBackgroundResource(R.drawable.background)
        } else {
            categoryBinding.cvCategory.radius = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 20f, itemView.resources.displayMetrics)
            categoryBinding.categoryName.setTypeface(null, Typeface.NORMAL)
            categoryBinding.layout.background = null
        }

        categoryBinding.cvCategory.setOnClickListener {
            if (Global.previous != RecyclerView.NO_POSITION) {
                resetCategoryImgSize(Global.previous)
            }
            categoryBinding.cvCategory.radius = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,10f, itemView.resources.displayMetrics)
            categoryBinding.categoryName.setTypeface(null, Typeface.BOLD)
            categoryBinding.layout.setBackgroundResource(R.drawable.background)
            categoryBinding.cvCategory.requestLayout()

            Global.isClick = true
            smoothScrollToPosition(bindingAdapterPosition)

            Global.previous = bindingAdapterPosition

            categoryClickListener.onCategoryClick(bindingAdapterPosition)
        }
    }

    fun smoothScrollToPosition(position: Int) {
        val smoothScroller = object : LinearSmoothScroller(categoryBinding.root.context) {
            override fun calculateDxToMakeVisible(view: View, snapPreference: Int): Int {
                return layoutManager!!.width / 2 - view.left - view.width / 2
            }
            override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                return 0.4f
            }
        }
        smoothScroller.targetPosition = position
        layoutManager.startSmoothScroll(smoothScroller)
    }

    private fun resetCategoryImgSize(position : Int) {
        val previousSelectedViewHolders = categoryClickListener.getViewHolder(position)
        for (viewHolders in previousSelectedViewHolders) {
            viewHolders.categoryBinding.cvCategory.radius = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 20f, viewHolders.itemView.resources.displayMetrics)
            viewHolders.categoryBinding.categoryName.setTypeface(null, Typeface.NORMAL)
            viewHolders.categoryBinding.layout.background = null
            viewHolders.categoryBinding.categoryImg.requestLayout()
        }
    }
}