package com.kamilh.gotcharacters.repository.books.model

data class BooksResponse(
    val url: String,
    val name: String,
    val isbn: String,
    val authors: List<String>,
    val numberOfPages: Int,
    val publiser: String,
    val country: String,
    val mediaType: String,
    val released: String,
    val characters: List<String>,
    val povCharacters: List<String>
)
