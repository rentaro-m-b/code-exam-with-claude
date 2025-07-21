package com.example.mapper

import com.example.model.Book
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select

@Mapper
interface BookMapper {
    
    @Select("SELECT id, title, author, publisher, price FROM books")
    fun findAll(): List<Book>
    
    @Insert("INSERT INTO books (id, title, author, publisher, price) VALUES (nextval('BOOK_ID_SEQ'), #{title}, #{author}, #{publisher}, #{price})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    fun insert(book: Book): Int
    
    @Select("SELECT id, title, author, publisher, price FROM books WHERE id = #{id}")
    fun findById(id: Int?): Book?
}