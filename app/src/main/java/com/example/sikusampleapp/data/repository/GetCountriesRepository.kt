package com.example.sikusampleapp.data.repository

import com.example.sikusampleapp.data.GetCountries
import com.example.sikusampleapp.data.model.CallResponse
import com.example.sikusampleapp.data.model.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCountriesRepository @Inject constructor(
    private val getCountriesService: GetCountries
) {

    suspend fun getCountries(): CallResponse<Country> = withContext(Dispatchers.IO) {
        getCountriesService.getCountries()
    }
}