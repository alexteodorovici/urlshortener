package com.alexteodorovici.urlshortener.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexteodorovici.urlshortener.data.model.UrlEntity
import com.alexteodorovici.urlshortener.data.repository.UrlRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: UrlRepository
): ViewModel() {

    private val _urls = MutableStateFlow<List<UrlEntity>>(emptyList())
    val urls: StateFlow<List<UrlEntity>> = _urls

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun shortenUrl(originalUrl: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if(originalUrl.isBlank()){
                _error.value = "URL canot be empty"
                return@launch
            }

            if(!originalUrl.startsWith("http")){
                _error.value = "Invalid URL format"
                return@launch
            }

            val shortCode = originalUrl.hashCode().toUInt().toString(36).take(6)

            try {
                repository.insertUrl(originalUrl, shortCode)

                _urls.value = repository.getAllUrls().first()
                _error.value = null
            } catch( e: Exception) {
                _error.value = "Error retrieving URL: ${e.message}"
            }
        }
    }

    fun getOriginalUrl(shortCode: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.getUrlByShortCode(shortCode)
                if(result != null) {
                    _error.value = "Original URL: ${result.originalUrl}"
                } else {
                    _error.value = "Short code not found"
                }
            } catch( e: Exception) {
                _error.value = "Error retrieving URL: ${e.message}"
            }
        }
    }

    fun loadMappings() {
        viewModelScope.launch(Dispatchers.IO) {
            _urls.value = repository.getAllUrls().first()
        }
    }

}