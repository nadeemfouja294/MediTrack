package com.logical.meditrack.data.repository

import com.logical.meditrack.data.api.ApiService
import com.logical.meditrack.data.db.MedicineDao
import com.logical.meditrack.data.model.toDomainModel
import com.logical.meditrack.domain.repository.MedicineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MedicineRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val medicineDao: MedicineDao
) : MedicineRepository {

    override fun getMedicines(): Flow<List<com.logical.meditrack.domain.model.Medicine>> {
        return medicineDao.getAllMedicines().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override suspend fun refreshMedicines() {
        val response = apiService.getMedicines()
        if (response.isSuccessful) {
            response.body()?.let { body ->
                val medicines = body.problems.flatMap { it.diabetes.flatMap { it.medications.flatMap { it.medicationsClasses.flatMap { it.className.flatMap { it.associatedDrug + it.associatedDrug2 } } } } }
                medicineDao.insertAll(medicines)
            }
        }
    }
}
