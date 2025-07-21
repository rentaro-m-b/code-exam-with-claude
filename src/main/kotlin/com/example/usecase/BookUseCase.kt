package com.example.usecase

import com.example.mapper.BookMapper
import com.example.model.Book
import com.example.dto.CreateBookRequest
import org.springframework.stereotype.Service

@Service
class BookUseCase(private val bookMapper: BookMapper) {
    
    fun getAllBooks(): List<Book> {
        return bookMapper.findAll()
    }
    
    fun createBook(request: CreateBookRequest): Book {
        val book = Book(
            id = null,
            title = request.title,
            author = request.author,
            publisher = request.publisher,
            price = request.price
        )
        bookMapper.insert(book)
        return book
    }
}