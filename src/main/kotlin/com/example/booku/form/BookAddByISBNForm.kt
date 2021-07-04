package com.example.booku.form

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class BookAddByISBNForm {
    @NotBlank
    @Size(max = 13)
    var isbn: String? = null
}