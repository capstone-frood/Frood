package com.frood.app.presentation.util.extension

import androidx.lifecycle.MutableLiveData

fun Boolean.toInt() = if (this) 1 else 0

fun <T> ArrayList<T>.freshInsert(data: List<T>) {
    clear()
    addAll(data)
}

// LiveData
fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }