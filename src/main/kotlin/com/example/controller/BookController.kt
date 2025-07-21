package com.example.controller

import com.example.dto.BooksResponse
import com.example.dto.CreateBookRequest
import com.example.usecase.BookUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class BookController(private val bookUseCase: BookUseCase) {

    @GetMapping("/books")
    fun getBooks(): ResponseEntity<BooksResponse> {
        val books = bookUseCase.getAllBooks()
        return ResponseEntity.ok(BooksResponse(books))
    }

    @PostMapping("/books")
    fun createBook(@RequestBody request: CreateBookRequest): ResponseEntity<Void> {
        val createdBook = bookUseCase.createBook(request)
        val location = URI.create("/books/${createdBook.id}")
        return ResponseEntity.created(location).build()
    }
}