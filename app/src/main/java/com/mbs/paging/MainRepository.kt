package com.mbs.paging

import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(private val service: Service) {
    suspend fun request() = flow {
        emit(service.request())
    }
}