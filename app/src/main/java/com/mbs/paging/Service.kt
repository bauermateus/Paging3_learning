package com.mbs.paging

import retrofit2.Call
import retrofit2.http.GET

interface Service {
    @GET("search?limit=200&breed_ids=pers&api_key=743b4f4b-0ef6-4fed-bdd6-5108667fc3f5")
    suspend fun request(): Call<List<ResponseModelItem>>
}