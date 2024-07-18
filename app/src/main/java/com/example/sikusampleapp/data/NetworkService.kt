package com.example.sikusampleapp.data

import com.example.sikusampleapp.data.model.CallResponse
import com.example.sikusampleapp.data.model.ErrorResult
import com.example.sikusampleapp.data.model.InternalCountries
import com.example.sikusampleapp.data.model.SuccessResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.Retrofit.*
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class NetworkService @Inject constructor() {

    private val retrofit: Retrofit = Builder()
        .baseUrl("https://restcountries.com/v3.1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: NetworkApi = retrofit.create(NetworkApi::class.java)

    fun createRequest(call: Call<List<InternalCountries>>): CallResponse<InternalCountries> = try {
        val response = call.execute()
        if (response.isSuccessful && response.body() != null) {
            SuccessResult(response.body()!!)
        } else {
            ErrorResult("empty body")
        }
    } catch (exception: Exception) {
        ErrorResult(error = exception.message ?: "something went wrong")
    }
}