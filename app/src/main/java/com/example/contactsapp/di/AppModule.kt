package com.example.contactsapp.di

import android.app.Application
import androidx.room.Room
import com.example.contactsapp.data.repository.PersonDaoRepository
import com.example.contactsapp.room.MyDatabase
import com.example.contactsapp.room.PersonsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMyDatabase(application: Application): MyDatabase {
        return Room.databaseBuilder(
            application,
            MyDatabase::class.java,
            "my_database"
        ).build()
    }

    @Provides
    fun providePersonDao(database: MyDatabase): PersonsDao {
        return database.getPersonsDao()
    }

    @Provides
    @Singleton
    fun providePersonDaoRepository(personsDao: PersonsDao): PersonDaoRepository {
        return PersonDaoRepository(personsDao)
    }
}