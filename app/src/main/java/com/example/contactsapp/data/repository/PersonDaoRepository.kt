package com.example.contactsapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.contactsapp.data.entity.Persons
import com.example.contactsapp.room.PersonsDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonDaoRepository (private val personsDao: PersonsDao) {
    val personsList = MutableLiveData<List<Persons>>()

    init {
        getAllPersons()
    }

    fun getPersons() : MutableLiveData<List<Persons>> {
        return personsList
    }

    fun savePerson(person: Persons) {
        CoroutineScope(Dispatchers.IO).launch {
            personsDao.addPerson(person)
        }
    }

    fun updatePerson(person: Persons) {
        CoroutineScope(Dispatchers.IO).launch {
            personsDao.updatePerson(person)
        }
    }

    fun deletePerson(person: Persons) {
        CoroutineScope(Dispatchers.IO).launch {
            personsDao.deletePerson(person)
        }
    }

    fun searchPerson(searchQuery: String){
        CoroutineScope(Dispatchers.IO).launch {
            personsList.postValue(personsDao.searchPerson("%$searchQuery%"))
        }
    }

    fun getAllPersons(){
        CoroutineScope(Dispatchers.IO).launch {
            personsList.postValue(personsDao.getAllPerson())
        }
    }
}