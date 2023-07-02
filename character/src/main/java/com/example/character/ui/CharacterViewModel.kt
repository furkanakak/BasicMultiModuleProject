package com.example.character.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.entity.character.RickAndMortyResponse
import com.example.core.data.repository.Repository
import com.example.core.di.networking.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _characters = MutableStateFlow<Resource<RickAndMortyResponse>>(Resource.Loading())
    val characters: StateFlow<Resource<RickAndMortyResponse>> = _characters
    fun getCharacters(page: Int) {
        viewModelScope.launch {
            try {
                _characters.value = Resource.Loading()
                val result = repository.getCharacters(page)
                _characters.value = result
            } catch (e: Exception) {
                _characters.value = Resource.Error(e)
            }
        }
    }



}