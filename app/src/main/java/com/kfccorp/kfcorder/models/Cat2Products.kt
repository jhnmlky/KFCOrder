package com.kfccorp.kfcorder.models

import com.kfccorp.kfcorder.interfaces.ProductInterface

var cat2Products = mutableListOf<Cat2Products>()

class Cat2Products(
    var _id: String,
    var _img: Int,
    var _name: String,
    var _price: Int,
    var _quantity: Int
) : ProductInterface {
    override fun getID(): String = _id
    override fun getImage(): Int = _img
    override fun getName(): String = _name
    override fun getPrice(): Int = _price
    override fun getQuantity(): Int = _quantity
}