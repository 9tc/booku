package com.example.booku.form

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class BookSearchByTitleForm {
    @NotBlank
    @Size(max = 64)
    var name: String? = null
}