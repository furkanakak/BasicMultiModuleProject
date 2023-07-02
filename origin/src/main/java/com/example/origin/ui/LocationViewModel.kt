package com.example.origin.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.entity.location.OriginResponse
import com.example.core.data.repository.Repository
import com.example.core.di.networking.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(private val repository: Repository) : ViewModel(){
    private val _origins = MutableStateFlow<Resource<OriginResponse>>(Resource.Loading())
    val origins: StateFlow<Resource<OriginResponse>> = _origins

    fun getOrigins() {
        viewModelScope.launch {
            try {
                _origins.value = Resource.Loading()
                val result = repository.getOrigins()
                _origins.value = result
            } catch (e: Exception) {
                _origins.value = Resource.Error(e)
            }
        }
    }

}