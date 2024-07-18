package com.example.sikusampleapp.data

import com.example.sikusampleapp.data.model.InternalCountries
import retrofit2.Call
import retrofit2.http.GET

interface NetworkApi {

    @GET("all?fields=name,flags,capital")
    fun getCountries(): Call<List<InternalCountries>>
}