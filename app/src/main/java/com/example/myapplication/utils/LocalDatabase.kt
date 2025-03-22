package com.example.myapplication.utils

data class User(var name:String, var quantity:Int)

object LocalDatabase {
    var inventoryItems:ArrayList<User> = ArrayList()
}