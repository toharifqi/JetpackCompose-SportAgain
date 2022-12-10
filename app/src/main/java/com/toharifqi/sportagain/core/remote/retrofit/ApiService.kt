package com.toharifqi.sportagain.core.remote.retrofit

import com.toharifqi.sportagain.core.remote.response.SportsListResponse
import retrofit2.http.GET

interface ApiService {
    @GET("all_sports.php")
    suspend fun getList(): SportsListResponse
}