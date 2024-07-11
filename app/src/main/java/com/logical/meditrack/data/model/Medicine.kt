package com.logical.meditrack.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Medicine(
    @PrimaryKey val name: String,
    val dose: String,
    val strength: String
)

