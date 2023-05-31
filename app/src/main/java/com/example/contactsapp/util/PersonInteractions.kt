package com.example.contactsapp.util

import com.example.contactsapp.data.entity.Persons

interface PersonInteractions {
    fun deletePerson(person: Persons)
    fun searchPerson(search: String)
    fun loadPersons()
    fun navigateToDetails(person: Persons)
}