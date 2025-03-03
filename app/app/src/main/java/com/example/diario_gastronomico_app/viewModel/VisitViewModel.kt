package com.example.diario_gastronomico_app.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diario_gastronomico_app.domain.Visit
import com.example.diario_gastronomico_app.repositories.VisitRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VisitViewModel: ViewModel() {

    private val repository = VisitRepository()

    private val _visits = MutableStateFlow<List<Visit>>(emptyList())
    val visits: StateFlow<List<Visit>> = _visits

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        fetchVisits()
    }

    private fun fetchVisits() {
        viewModelScope.launch {
            _isLoading.value = true
            _visits.value = repository.getVisits()
            _isLoading.value = false
        }
    }
}