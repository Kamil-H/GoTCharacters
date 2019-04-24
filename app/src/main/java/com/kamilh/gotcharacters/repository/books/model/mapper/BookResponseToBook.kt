package com.kamilh.gotcharacters.repository.books.model.mapper

import com.kamilh.gotcharacters.data.Book
import com.kamilh.gotcharacters.extensions.toDateTime
import com.kamilh.gotcharacters.extensions.toOffsetDateTime
import com.kamilh.gotcharacters.repository.Mapper
import com.kamilh.gotcharacters.repository.books.model.BooksResponse
import javax.inject.Inject

class BookResponseToBook @Inject constructor() : Mapper<BooksResponse, Book> {

    override fun map(from: BooksResponse) = Book(
        name = from.name,
        released = from.released.toDateTime()
    )
}
