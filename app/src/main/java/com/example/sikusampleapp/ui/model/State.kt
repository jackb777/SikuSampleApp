package com.example.sikusampleapp.ui.model

import com.example.sikusampleapp.data.model.Country

sealed interface State

data class Success(val data: List<Country>) : State

data class Error(val errorMessage: String) : State

data object Loading: State