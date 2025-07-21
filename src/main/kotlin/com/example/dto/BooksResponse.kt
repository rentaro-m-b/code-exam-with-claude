package com.example.dto

import com.example.model.Book

data class BooksResponse(
    val books: List<Book>
)