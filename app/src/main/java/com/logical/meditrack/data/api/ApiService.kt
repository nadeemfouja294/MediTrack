package com.logical.meditrack.data.api

import com.logical.meditrack.data.model.MedicineResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("your_mock_api_url")
    suspend fun getMedicines(): Response<MedicineResponse>
}