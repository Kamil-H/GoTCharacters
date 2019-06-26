package com.kamilh.gotcharacters.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations

fun <T, U> LiveData<T>.map(mapFunction: (T) -> U): LiveData<U> {
    return Transformations.map(this, mapFunction)
}

fun <T> LiveData<T>.onNext(action: (T) -> Unit): LiveData<T> {
    return MediatorLiveData<T>().apply {
        addSource<T>(this@onNext) {
            action(it)
            setValue(it)
        }
    }
}

inline fun <T> LiveData<T>.filter(crossinline predicate : (T?) -> Boolean): LiveData<T> {
    return MediatorLiveData<T>().apply {
        addSource(this@filter) {
            if (predicate(it)) {
                value = it
            }
        }
    }
}
