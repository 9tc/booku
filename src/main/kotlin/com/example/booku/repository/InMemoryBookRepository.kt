package com.example.booku.repository

import com.example.booku.Book
import org.springframework.stereotype.Repository
import java.lang.Integer.max

@Repository
class InMemoryBookRepository : BookRepository {
    private val books: MutableList<Book> = mutableListOf()

    private val maxId: Long get() = books.map(Book::id).maxOrNull() ?: 0

    override fun create(name: String, author: String, isbn: String, height: Long, width: Long, pages: Long): Book {
        val id = maxId + 1
        val book = Book(id, name, author, isbn, false, height, width, pages)
        books += book

        return book
    }

    override fun delete(id: Long) {
        books.remove(findById(id))
    }


    override fun update(book: Book) {
        books.replaceAll {t ->
            if(t.id == book.id) book
            else t
        }
    }

    override fun findAll(): List<Book> = books.toList()

    override fun findById(id: Long): Book? = books.find{it.id == id}

    override fun toString(): String {
        var s = ""
        for(book in books) s += book.toString() + "\n"
        return s
    }

    override fun count(): Long {
        return books.size.toLong()
    }

    override fun maxLength(): Long? {
        var r = 0
        for(book in books){
            r = max(r, book.toString().length)
        }
        return r.toLong() * 2
    }
}