package com.kamilh.gotcharacters.repository

import com.kamilh.gotcharacters.repository.Resource.Data
import kotlinx.coroutines.Deferred
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitRunner @Inject constructor() {
    suspend fun <T, E> executeForResponse(mapper: Mapper<T, E>, request: Deferred<Response<T>>): Resource<E> {
        return try {
            val response = request.await()
            if (response.isSuccessful) {
                Data(mapper.map(response.body()!!))
            } else {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            Resource.Error(RepositoryError.handle(e))
        }
    }

    suspend fun executeForResponse(request: Deferred<Response<Unit>>): Resource<Unit> {
        val unitMapper = object : Mapper<Unit, Unit> {
            override fun map(from: Unit) = Unit
        }
        return executeForResponse(unitMapper, request)
    }
}
