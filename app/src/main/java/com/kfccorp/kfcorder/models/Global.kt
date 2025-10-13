package com.kfccorp.kfcorder.models

import com.kfccorp.kfcorder.interfaces.ProductInterface

object Global {
    const val EXPIRE: Int = 5
    var productPosition : Int = -1
    var categoryPosition : Int = 0
    var isEdit : Int = 0
    var cartItemPos : Int = -1
    var productList : List<ProductInterface> = emptyList()
    var previous : Int = 11
    var isClick : Boolean = false
}