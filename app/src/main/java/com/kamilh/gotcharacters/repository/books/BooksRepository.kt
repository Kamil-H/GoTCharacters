package com.kamilh.gotcharacters.repository.books

import com.kamilh.gotcharacters.data.Book
import com.kamilh.gotcharacters.repository.Resource

interface BooksRepository {

    suspend fun getById(id: Int): Resource<Book>
}
