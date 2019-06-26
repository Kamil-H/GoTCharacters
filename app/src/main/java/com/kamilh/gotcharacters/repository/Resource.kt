package com.kamilh.gotcharacters.repository

sealed class Resource<out T> {
    class Data<out T>(val result: T) : Resource<T>()
    class Error(val repositoryError: RepositoryError) : Resource<Nothing>()
}

inline infix fun <T, U> Resource<T>.then(f: (T) -> Resource<U>) = when (this) {
    is Resource.Data -> f(result)
    is Resource.Error -> this
}
