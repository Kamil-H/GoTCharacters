package com.kamilh.gotcharacters.repository.books

import com.kamilh.gotcharacters.data.Book
import com.kamilh.gotcharacters.repository.IceAndFireApi
import com.kamilh.gotcharacters.repository.Resource
import com.kamilh.gotcharacters.repository.RetrofitRunner
import com.kamilh.gotcharacters.repository.books.model.mapper.BookResponseToBook
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BooksRemoteRepository @Inject constructor(
    private val iceAndFireApi: IceAndFireApi,
    private val retrofitRunner: RetrofitRunner,
    private val bookResponseToBook: BookResponseToBook
) : BooksRepository {

    override suspend fun getById(id: Int): Resource<Book> {
        return retrofitRunner.executeForResponse(bookResponseToBook, iceAndFireApi.bookById(id))
    }
}
