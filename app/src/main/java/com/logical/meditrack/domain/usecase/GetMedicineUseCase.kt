package com.logical.meditrack.domain.usecase

import com.logical.meditrack.domain.model.Medicine
import com.logical.meditrack.domain.repository.MedicineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMedicinesUseCase @Inject constructor(
    private val repository: MedicineRepository
) {
    operator fun invoke(): Flow<List<Medicine>> {
        return repository.getMedicines()
    }
}
