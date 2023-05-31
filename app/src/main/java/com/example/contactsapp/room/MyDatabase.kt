package com.example.contactsapp.room

import com.example.contactsapp.util.Converters
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.contactsapp.data.entity.Persons

@Database(entities = [Persons::class], version = 3)
@TypeConverters(Converters::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getPersonsDao(): PersonsDao
}