package com.example.contactsapp.room

import androidx.room.*
import com.example.contactsapp.data.entity.Persons


@Dao
interface PersonsDao {
    @Query("SELECT * FROM Persons")
    suspend fun getAllPerson() : List<Persons>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPerson(person: Persons)

    @Update
    suspend fun updatePerson(person: Persons)

    @Delete
    suspend fun deletePerson(person: Persons)

    @Query("SELECT * FROM Persons WHERE person_name LIKE :search")
    suspend fun searchPerson(search: String): List<Persons>

    @Query("SELECT * FROM Persons WHERE person_group IN (:groups) AND person_name LIKE :searchQuery")
    suspend fun searchPersonByGroup(groups: List<String>, searchQuery: String): List<Persons>
    @Query("SELECT * FROM Persons WHERE person_group = :group")
    suspend fun getPersonsByGroup(group: String): List<Persons>
}