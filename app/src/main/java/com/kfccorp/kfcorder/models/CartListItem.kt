package com.kfccorp.kfcorder.models

var cartList = mutableListOf<CartListItem>()

class CartListItem(
    var id: String,
    var image: Int,
    var name: String,
    var customs: List<String?>,
    var selection: List<Int>,
    var increase: List<Int>,
    var select: List<Boolean>,
    var total: Int,
    var amount: Int,
    var price: Int,
    var categoryPos: Int,
    var productPos: Int,
    var originalPrice: Int,
    var varPrice: Int,
    var quantity: Int
)