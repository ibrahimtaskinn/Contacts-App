package com.example.contactsapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsapp.data.entity.Persons
import com.example.contactsapp.data.repository.PersonDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: PersonDaoRepository ) : ViewModel() {

    val personsList = repository.getPersons()

    fun deletePerson(person: Persons) {
        viewModelScope.launch {
            repository.deletePerson(person)
            loadperson()
        }
    }

    fun searchPerson(search: String) {
        viewModelScope.launch {
            repository.searchPerson(search)
        }
    }

    fun loadperson(){
        repository.getAllPersons()
    }
}