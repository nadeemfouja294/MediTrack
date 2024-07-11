package com.logical.meditrack.data.model

import com.logical.meditrack.domain.model.Medicine as DomainMedicine

fun Medicine.toDomainModel(): DomainMedicine {
    return DomainMedicine(name = this.name, dose = this.dose, strength = this.strength)
}
