package com.logical.meditrack.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.logical.meditrack.domain.model.Medicine
import com.logical.meditrack.domain.usecase.GetMedicinesUseCase
import com.logical.meditrack.domain.usecase.RefreshMedicinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMedicinesUseCase: GetMedicinesUseCase,
    private val refreshMedicinesUseCase: RefreshMedicinesUseCase
) : ViewModel() {

    private val _medicineList = MutableStateFlow<List<Medicine>>(emptyList())
    val medicineList: StateFlow<List<Medicine>> = _medicineList.asStateFlow()

    init {
        refreshMedicines()
        getMedicines()
    }

    private fun getMedicines() {
        viewModelScope.launch {
            getMedicinesUseCase().collect { medicines ->
                _medicineList.value = medicines
            }
        }
    }

    private fun refreshMedicines() {
        viewModelScope.launch {
            refreshMedicinesUseCase()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getGreeting(): String {
        val currentHour = LocalTime.now().hour
        return when (currentHour) {
            in 0..11 -> "Morning"
            in 12..17 -> "Afternoon"
            else -> "Evening"
        }
    }
}
