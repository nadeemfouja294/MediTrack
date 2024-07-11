package com.logical.meditrack.data.model

data class MedicineResponse(
    val problems: List<Problem>
)

data class Problem(
    val diabetes: List<Diabetes>
)

data class Diabetes(
    val medications: List<Medication>
)

data class Medication(
    val medicationsClasses: List<MedicationsClass>
)

data class MedicationsClass(
    val className: List<ClassName>,
    val className2: List<ClassName>
)

data class ClassName(
    val associatedDrug: List<Medicine>,
    val associatedDrug2: List<Medicine>
)
