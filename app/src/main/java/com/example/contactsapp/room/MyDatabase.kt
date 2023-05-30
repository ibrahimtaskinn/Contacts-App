package com.example.contactsapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contactsapp.data.entity.Persons

@Database(entities = [Persons::class], version = 2)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getPersonsDao(): PersonsDao
}