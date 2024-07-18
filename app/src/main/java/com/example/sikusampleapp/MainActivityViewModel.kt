package com.example.sikusampleapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sikusampleapp.data.model.ErrorResult
import com.example.sikusampleapp.data.model.SuccessResult
import com.example.sikusampleapp.data.repository.GetCountriesRepository
import com.example.sikusampleapp.ui.model.Error
import com.example.sikusampleapp.ui.model.Loading
import com.example.sikusampleapp.ui.model.State
import com.example.sikusampleapp.ui.model.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: GetCountriesRepository
) : ViewModel() {

    val state: StateFlow<State>
        get() = _state

    private val _state: MutableStateFlow<State> = MutableStateFlow(Loading)

    fun getCountries() {
        viewModelScope.launch {
            _state.emit(Loading)
            val event = when (val result = repository.getCountries()) {
                is ErrorResult -> Error(result.error)
                is SuccessResult -> Success(result.data)
            }

            _state.emit(event)
        }
    }
}