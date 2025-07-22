package com.example.dto

data class CreateBookRequest(
    val title: String,
    val author: String,
    val publisher: String,
    val price: Int
)