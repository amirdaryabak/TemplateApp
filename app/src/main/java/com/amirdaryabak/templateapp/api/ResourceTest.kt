package com.amirdaryabak.templateapp.api

data class ResourceTest<out T>(val status: Statuses, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): ResourceTest<T> {
            return ResourceTest(Statuses.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): ResourceTest<T> {
            return ResourceTest(Statuses.ERROR, data, msg)
        }

        fun <T> loading(data: T?): ResourceTest<T> {
            return ResourceTest(Statuses.LOADING, data, null)
        }
    }
}

enum class Statuses {
    SUCCESS,
    ERROR,
    LOADING
}