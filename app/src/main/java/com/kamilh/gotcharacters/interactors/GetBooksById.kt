package com.kamilh.gotcharacters.interactors

import com.kamilh.gotcharacters.data.Book
import com.kamilh.gotcharacters.di.ActivityScope
import com.kamilh.gotcharacters.repository.Resource
import com.kamilh.gotcharacters.repository.books.BooksRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

@ActivityScope
class GetBooksById @Inject constructor(
    private val booksRepository: BooksRepository
) : Interactor<GetBooksById.Params, Resource<List<Book>>> {

    override suspend fun invoke(params: Params): Resource<List<Book>> {
        return if (params.ids.isEmpty()) {
            Resource.Data(listOf())
        } else {
            val resources = params.ids.map { coroutineScope { async { booksRepository.getById(it) } } }.map { it.await() }
            val books = resources.mapNotNull { it as? Resource.Data }.map { it.result }
            if (books.isNotEmpty()) {
                return Resource.Data(books)
            }
            val errors = resources.mapNotNull { it as? Resource.Error }
            if (errors.isNotEmpty()) {
                return errors[0]
            }
            return Resource.Data(listOf())
        }
    }

    data class Params(
        val ids: List<Int>
    )
}
