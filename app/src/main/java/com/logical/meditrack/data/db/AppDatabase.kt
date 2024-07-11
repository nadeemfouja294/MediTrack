package com.logical.meditrack.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.logical.meditrack.data.model.Medicine

@Database(entities = [Medicine::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun medicineDao(): MedicineDao
}
