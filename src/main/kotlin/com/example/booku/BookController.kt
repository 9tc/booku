package com.example.booku

import com.example.booku.data.OpenDBData
import com.example.booku.form.BookAddByISBNForm
import com.example.booku.form.BookAddForm
import com.example.booku.form.BookUpdateForm
import com.example.booku.repository.BookRepository
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate


@Controller
@RequestMapping("books")
class BookController(private val bookRepository: BookRepository) {
    var localBook: Book = Book()
    //index 本棚の内容の取得
    @GetMapping("")
    fun index(model: Model): String{
        model.addAttribute("books", bookRepository.findAll())
        return "books/index"
    }

    //本棚に本の追加
    @GetMapping("add")
    fun add(form: BookAddForm): String{
        if(localBook.id != -1L){
            form.name = localBook.name
            form.author = localBook.author
            form.isbn = localBook.isbn
            form.height = localBook.height
            form.width = localBook.width
            form.pages = localBook.pages
            localBook = Book()
        }
        return "books/add"
    }

    //追加後の処理
    @PostMapping(params=["add"])
    fun create(@Validated form: BookAddForm, bindingResult: BindingResult): String{
        if(bindingResult.hasErrors()) return "books/add"

        val name = requireNotNull(form.name)
        val author = requireNotNull(form.author)
        val isbn = requireNotNull(form.isbn)
        val height = requireNotNull(form.height)
        val width = requireNotNull(form.width)
        val pages = requireNotNull(form.pages)
        bookRepository.create(name, author, isbn, height, width, pages)
        return "redirect:/books"
    }


    //本棚に本の追加
    @GetMapping("add_by_isbn")
    fun addByISBN(form: BookAddByISBNForm): String{
        return "books/add_by_isbn"
    }

    @Autowired
    private val restTemplate: RestTemplate? = null
    //追加後の処理
    @PostMapping(params=["add_by_isbn"])
    fun createByISBN(@Validated form: BookAddByISBNForm, bindingResult: BindingResult): String{
        if(bindingResult.hasErrors()) return "books/add_by_isbn"
        val isbn = requireNotNull(form.isbn)

        val URL = "https://api.openbd.jp/v1/get?isbn=$isbn&pretty"

        val httpHeaders = HttpHeaders()
        httpHeaders.add(HttpHeaders.ACCEPT, "application/json; charset=UTF-8")
        val response = restTemplate!!.exchange(
            URL,
            HttpMethod.GET,
            HttpEntity<Any?>(httpHeaders),
            String::class.java
        )

        if(response.body!!.length == 10) return "books/not_found_isbn"

        val j = Json{
            isLenient = true
            ignoreUnknownKeys = true
            allowSpecialFloatingPointValues = true
            useArrayPolymorphism = true
        }.decodeFromString<OpenDBData>(response.body!!.toString().drop(1).dropLast(1))

        val bookModel = j.onix.descriptiveDetail!!.productFormDetail
        val (height, width) = convertModelToSize(bookModel)
        localBook = Book(1, j.summary.title!!, j.summary.author!!, isbn, false, height, width, j.onix.descriptiveDetail.extent!![0].extentValue!!.toLong())

        return "redirect:/books/add"
    }

    private fun convertModelToSize(bookModel: String?): Pair<Long, Long> {
        when(bookModel){
            "B108" -> return Pair(210, 148)
            "B109" -> return Pair(257, 182)
            "B110" -> return Pair(182, 128)
            "B111" -> return Pair(148, 105)
            "B112" -> return Pair(182, 103)
            "B119" -> return Pair(188, 127)
            "B120" -> return Pair(188, 127) //46変形
            "B121" -> return Pair(297, 210)
            "B122" -> return Pair(297, 232) //A4変形なので諸説
            "B123" -> return Pair(210, 148) //A5変形
            "B124" -> return Pair(257, 182) //B5変形
            "B125" -> return Pair(182, 128) //B6変形
            "B126" -> return Pair(257, 210)
            "B127" -> return Pair(128, 96)
            "B128" -> return Pair(220, 150)
            "B129" -> return Pair(220, 150) //kiku変形
            "B130" -> return Pair(364, 257)
            else -> return Pair(0, 0)
        }
    }

    //本棚の本の更新
    @GetMapping("{id}/edit")
    fun edit(@PathVariable("id") id: Long, form: BookUpdateForm): String{
        val book = bookRepository.findById(id) ?: throw NotFoundException()
        form.name = book.name
        form.author = book.author
        form.isbn = book.isbn
        form.read = book.read
        form.height = book.height
        form.width = book.width
        form.pages = book.pages
        return "books/edit"
    }

    @PatchMapping("{id}")
    fun update(@PathVariable("id") id: Long, @Validated form: BookUpdateForm, bindingResult: BindingResult): String{
        if(bindingResult.hasErrors()) return "books/edit"

        val book = bookRepository.findById(id) ?: throw NotFoundException()
        val newBook = book.copy(name = requireNotNull(form.name), author = requireNotNull(form.author),
            isbn = requireNotNull(form.isbn), read = form.read, height = requireNotNull(form.height), width = requireNotNull(form.width), pages = requireNotNull(form.pages))
        bookRepository.update(newBook)
        if(form.delete) bookRepository.delete(id)
        return "redirect:/books"
    }
    /*
    @GetMapping("searchtitle")
    fun searchTitle(form: BookSearchByTitleForm): String{
        println("add")
        return "books/searchtitle"
    }

    @Autowired
    private val restTemplate: RestTemplate? = null
    @PostMapping(params= ["searchByTitle"])
    fun searchByTitle(@Validated form: BookSearchByTitleForm, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) return "books/searchtitle"

        val searchTitle = requireNotNull(form.name)
        val URL = "https://www.googleapis.com/books/v1/volumes?q=$searchTitle"
        //bookRepository.create(name, author, isbn)
        val httpHeaders = HttpHeaders()
        httpHeaders.add(HttpHeaders.ACCEPT, "application/json; charset=UTF-8")
        val response = restTemplate!!.exchange(
            URL,
            HttpMethod.GET,
            HttpEntity<Any?>(httpHeaders),
            String::class.java
        )

        //val jsonText = response.toString()
        //val parentJsonObj = JSONObject(jsonText)
        val obj = Json(JsonConfiguration.Stable).parse(GBooksData.serializer(), response.toString())

        return "redirect:/books"
    }
    */

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(): String = "books/not_found"
}
//TODO SQLつかう
//TODO 認証入れる
//TODO 個人の本棚にする
//TODO 本列挙じゃなくて本棚にしよう?
//TODO カメラからバーコード読み取りできたら熱い