package com.example.booku.form

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class BookUpdateForm {

    @NotBlank
    @Size(max = 64)
    var name: String? = null

    @NotBlank
    @Size(max = 64)
    var author: String? = null

    @Size(max = 64)
    var isbn: String? = null

    @NotNull
    var height: Long? = null

    @NotNull
    var width: Long? = null

    @NotNull
    var pages: Long? = null

    var read: Boolean = false
    var delete: Boolean = false
}