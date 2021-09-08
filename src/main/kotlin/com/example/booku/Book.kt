package com.example.booku

data class Book(
    val id: Long = -1L,
    val name: String = "",
    val author: String = "",
    val isbn: String = "",
    val read: Boolean = false,
    val height: Long = -1L,
    val width: Long = -1L,
    val pages: Long = -1L
    )
