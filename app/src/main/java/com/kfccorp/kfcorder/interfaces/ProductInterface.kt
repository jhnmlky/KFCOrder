package com.kfccorp.kfcorder.interfaces

interface ProductInterface {
    fun getID(): String
    fun getImage() : Int
    fun getName() : String
    fun getPrice() : Int
    fun getQuantity(): Int
}