package com.logical.meditrack.domain.usecase

import com.logical.meditrack.domain.repository.MedicineRepository
import javax.inject.Inject

class RefreshMedicinesUseCase @Inject constructor(
    private val repository: MedicineRepository
) {
    suspend operator fun invoke() {
        repository.refreshMedicines()
    }
}
