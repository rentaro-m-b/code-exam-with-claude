package com.example.mapper

import com.example.model.Book
import org.junit.jupiter.api.Test
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.test.context.ActiveProfiles
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertNotNull

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class BookMapperTest {

    @Autowired
    private lateinit var bookMapper: BookMapper

    @Test
    fun `findAll should return all books from database`() {
        val books = bookMapper.findAll()
        
        assertTrue(books.isNotEmpty())
        assertEquals("テスト駆動開発", books[0].title)
        assertEquals("Kent Beck", books[0].author)
    }

    @Test
    fun `insert and findById should save and retrieve book correctly`() {
        val newBook = Book(
            id = null,
            title = "Clean Agile",
            author = "Robert C. Martin",
            publisher = "ドワンゴ",
            price = 2640
        )
        
        val insertedRows = bookMapper.insert(newBook)
        
        assertEquals(1, insertedRows)
        assertTrue(newBook.id!! > 0)
        
        val retrievedBook = bookMapper.findById(newBook.id)
        
        assertNotNull(retrievedBook)
        assertEquals(newBook.id, retrievedBook!!.id)
        assertEquals("Clean Agile", retrievedBook.title)
        assertEquals("Robert C. Martin", retrievedBook.author)
        assertEquals("ドワンゴ", retrievedBook.publisher)
        assertEquals(2640, retrievedBook.price)
    }
}