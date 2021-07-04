package com.example.booku.repository

import com.example.booku.Book

interface BookRepository {
    fun create(name: String, author: String, isbn: String): Book

    fun delete(id: Long)

    fun update(book: Book)

    fun findAll(): List<Book>

    fun findById(id: Long): Book?
}