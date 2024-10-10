package com.fyp.app.viewmodel

abstract class BaseViewModel<T>(private val viewModelClass: Class<T>) {
    @Volatile
    private var instance: T? = null

    fun getInstance(): T {
        return instance ?: synchronized(this) {
            instance ?: createInstance().also { instance = it }
        }
    }

    private fun createInstance(): T {
        return viewModelClass.getDeclaredConstructor().newInstance()
    }

    fun resetInstance() {
        instance = null
    }
}