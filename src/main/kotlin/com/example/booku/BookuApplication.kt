package com.example.booku

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BookuApplication

fun main(args: Array<String>) {
	runApplication<BookuApplication>(*args)
}