package com.kamilh.gotcharacters.repository

import com.kamilh.gotcharacters.repository.books.model.BooksResponse
import com.kamilh.gotcharacters.repository.characters.model.CharacterResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IceAndFireApi {
    @GET("characters")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Response<List<CharacterResponse>>

    @GET("characters")
    suspend fun filterByName(
        @Query("name") name: String
    ): Response<List<CharacterResponse>>

    @GET("books/{id}")
    suspend fun bookById(
        @Path("id") id: Int
    ): Response<BooksResponse>
}
