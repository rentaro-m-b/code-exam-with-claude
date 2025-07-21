package com.example.usecase

import com.example.mapper.BookMapper
import com.example.model.Book
import com.example.dto.CreateBookRequest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever
import org.mockito.kotlin.any
import org.junit.jupiter.api.Assertions.assertEquals

@ExtendWith(MockitoExtension::class)
class BookUseCaseTest {

    @Mock
    private lateinit var bookMapper: BookMapper

    @InjectMocks
    private lateinit var bookUseCase: BookUseCase

    @Test
    fun `getAllBooks should return all books from mapper`() {
        val books = listOf(
            Book(id = 1, title = "テスト駆動開発", author = "Kent Beck", publisher = "オーム社", price = 3080),
            Book(id = 2, title = "アジャイルサムライ", author = "Jonathan Rasmusson", publisher = "オーム社", price = 2860)
        )

        whenever(bookMapper.findAll()).thenReturn(books)

        val result = bookUseCase.getAllBooks()

        assertEquals(books, result)
    }

    @Test
    fun `createBook should call insert and return created book`() {
        val request = CreateBookRequest(
            title = "Clean Agile",
            author = "Robert C. Martin",
            publisher = "ドワンゴ",
            price = 2640
        )
        val expectedBook = Book(id = 3, title = request.title, author = request.author, publisher = request.publisher, price = request.price)
        
        whenever(bookMapper.insert(any())).thenAnswer { invocation ->
            val book = invocation.getArgument<Book>(0)
            book.id = expectedBook.id
            1
        }

        val result = bookUseCase.createBook(request)

        assertEquals(expectedBook, result)
    }
}