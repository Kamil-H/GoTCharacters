package com.kamilh.gotcharacters.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T : Any, L : LiveData<T>> Fragment.observeNotNull(liveData: L, body: (T) -> Unit) {
    liveData.observe(this.viewLifecycleOwner, Observer {
        it?.let(body)
    })
}

fun <T : Any, L : LiveData<T>> AppCompatActivity.observeNotNull(liveData: L, body: (T) -> Unit) {
    liveData.observe(this, Observer {
        it?.let(body)
    })
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}
