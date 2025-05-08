package com.ilham.event.util

import android.util.Log
import kotlinx.coroutines.flow.Flow

suspend fun <T> Flow<Response<T>>.collectAndHandle(
    onError: (Throwable?) -> Unit = {
        Log.e("collectAndHandle", "CollectAndHandle:")
    },
    onLoading: () -> Unit = {},
    stateReduce: (T) -> Unit
){

    collect{ response ->
        when(response){
            is Response.Error -> {
                onError(response.error)
            }
            is Response.Loading -> {
                onLoading()
            }
            is Response.Success -> {
                stateReduce(response.data)
            }
        }
    }

}