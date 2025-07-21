package com.example.model

data class Book(
    var id: Int? = null,
    val title: String,
    val author: String,
    val publisher: String,
    val price: Int
)