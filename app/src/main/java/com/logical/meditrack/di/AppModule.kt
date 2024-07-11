package com.logical.meditrack.di

import android.content.Context
import androidx.room.Room
import com.logical.meditrack.data.api.ApiService
import com.logical.meditrack.data.db.AppDatabase
import com.logical.meditrack.data.db.MedicineDao
import com.logical.meditrack.data.repository.MedicineRepositoryImpl
import com.logical.meditrack.domain.repository.MedicineRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://mocky.io/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "medicines.db"
        ).build()
    }

    @Provides
    fun provideMedicineDao(appDatabase: AppDatabase): MedicineDao {
        return appDatabase.medicineDao()
    }

    @Provides
    @Singleton
    fun provideMedicineRepository(
        apiService: ApiService,
        medicineDao: MedicineDao
    ): MedicineRepository {
        return MedicineRepositoryImpl(apiService, medicineDao)
    }
}
