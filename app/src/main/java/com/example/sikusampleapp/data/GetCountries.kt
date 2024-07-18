package com.example.sikusampleapp.data

import com.example.sikusampleapp.data.model.CallResponse
import com.example.sikusampleapp.data.model.Country
import com.example.sikusampleapp.data.model.ErrorResult
import com.example.sikusampleapp.data.model.SuccessResult
import javax.inject.Inject

class GetCountries @Inject constructor(private val networkService: NetworkService) {

    fun getCountries(): CallResponse<Country> =
        when (val result = networkService.createRequest(networkService.api.getCountries())) {
            is ErrorResult -> ErrorResult(result.error)
            is SuccessResult -> {
                val countries = result.data.map {
                    Country(
                        name = it.name.common,
                        capital = it.capital.firstOrNull(),
                        flag = it.flags.png
                    )
                }

                SuccessResult(countries)
            }
        }
}