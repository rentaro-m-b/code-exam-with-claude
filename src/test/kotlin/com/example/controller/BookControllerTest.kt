package com.example.controller

import com.example.usecase.BookUseCase
import com.example.model.Book
import com.example.dto.CreateBookRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.mockito.kotlin.any
import org.junit.jupiter.api.Test
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(BookController::class)
class BookControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var bookUseCase: BookUseCase

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `GET books should return all books`() {
        val books = listOf(
            Book(id = 1, title = "テスト駆動開発", author = "Kent Beck", publisher = "オーム社", price = 3080),
            Book(id = 2, title = "アジャイルサムライ", author = "Jonathan Rasmusson", publisher = "オーム社", price = 2860)
        )
        
        whenever(bookUseCase.getAllBooks()).thenReturn(books)

        mockMvc.perform(get("/books"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.books").isArray)
            .andExpect(jsonPath("$.books.length()").value(2))
            .andExpect(jsonPath("$.books[0].id").value("1"))
            .andExpect(jsonPath("$.books[0].title").value("テスト駆動開発"))
            .andExpect(jsonPath("$.books[0].author").value("Kent Beck"))
            .andExpect(jsonPath("$.books[0].publisher").value("オーム社"))
            .andExpect(jsonPath("$.books[0].price").value(3080))
            .andExpect(jsonPath("$.books[1].id").value("2"))
            .andExpect(jsonPath("$.books[1].title").value("アジャイルサムライ"))
            .andExpect(jsonPath("$.books[1].author").value("Jonathan Rasmusson"))
            .andExpect(jsonPath("$.books[1].publisher").value("オーム社"))
            .andExpect(jsonPath("$.books[1].price").value(2860))
    }

    @Test
    fun `POST books should create new book and return 201 with Location header`() {
        val request = CreateBookRequest(
            title = "Clean Agile",
            author = "Robert C. Martin",
            publisher = "ドワンゴ",
            price = 2640
        )
        val createdBook = Book(id = 3, title = request.title, author = request.author, publisher = request.publisher, price = request.price)
        
        whenever(bookUseCase.createBook(any())).thenReturn(createdBook)

        mockMvc.perform(
            post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isCreated)
            .andExpect(header().string("Location", "/books/3"))
    }
}