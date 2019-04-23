package com.kamilh.gotcharacters.repository

import com.kamilh.gotcharacters.repository.characters.model.CharacterResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IceAndFireApi {
    @GET("characters")
    fun getCharactersAsync(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Deferred<Response<List<CharacterResponse>>>

    @GET("characters")
    fun filterByNameAsync(
        @Query("name") name: String
    ): Deferred<Response<List<CharacterResponse>>>
}
