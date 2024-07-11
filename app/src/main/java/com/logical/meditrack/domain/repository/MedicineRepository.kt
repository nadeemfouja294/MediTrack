package com.logical.meditrack.domain.repository

import com.logical.meditrack.domain.model.Medicine
import kotlinx.coroutines.flow.Flow

interface MedicineRepository {

     fun getMedicines(): Flow<List<Medicine>>
    suspend fun refreshMedicines()
}