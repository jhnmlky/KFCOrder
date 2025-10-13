package com.kfccorp.kfcorder.models

import android.content.Context
import androidx.recyclerview.widget.*

class CircularLinearLayoutManager(
    context: Context,
    orientation : Int,
    reverseLayout : Boolean
) :
    LinearLayoutManager(context, orientation, reverseLayout)
{
    override fun findFirstCompletelyVisibleItemPosition(): Int {
        val firstVisibleItemPosition = super.findFirstCompletelyVisibleItemPosition()
        if (firstVisibleItemPosition == RecyclerView.NO_POSITION) {
            return RecyclerView.NO_POSITION
        }
        return firstVisibleItemPosition % itemCount
    }
}