package com.mobifyall.restaurantshiftschedular.models

sealed class States {
    class Loading(val message: String? = null) : States()
    class Success(val data: Any?) : States() {
        inline fun <reified T> responseTo() = data as T
    }

    object Complete : States()
    class Error(val httpError: String?) : States()
}

inline fun <T, R> States.mapSuccess(mapper: (T) -> R) = when (this) {
    is States.Success -> mapper(data as T)
    else -> this
}