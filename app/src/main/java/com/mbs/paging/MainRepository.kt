package com.mbs.paging

import javax.inject.Inject

class MainRepository @Inject constructor(private val service: Service) {
    suspend fun request() = service.request()
}