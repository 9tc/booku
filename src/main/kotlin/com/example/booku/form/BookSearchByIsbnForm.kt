package com.example.booku.form

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class BookSearchByIsbnForm {
    @NotBlank
    @Size(max = 13)
    var isbn: Long? = null
}