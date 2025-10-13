package com.kfccorp.kfcorder.models

data class CartItem(
    val id: String? = null,
    val name: String? = null,
    val customs: List<String?>? = null,
    val price: Int? = null,
    val amount: Int? = null,
    val total: Int? = null
)
