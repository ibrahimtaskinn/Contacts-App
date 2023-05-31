package com.example.contactsapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsapp.data.entity.Persons
import com.example.contactsapp.data.repository.PersonDaoRepository
import com.example.contactsapp.util.PersonInteractions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: PersonDaoRepository ) : ViewModel(),
    PersonInteractions {

    val personsList = repository.getPersons()
    val navigateToDetailsEvent = MutableLiveData<Persons?>()

    override fun deletePerson(person: Persons) {
        viewModelScope.launch {
            repository.deletePerson(person)
            loadPersons()
        }
    }

    override fun searchPerson(search: String) {
        viewModelScope.launch {
            repository.searchPerson(search)
        }
    }

    override fun loadPersons(){
        repository.getAllPersons()
    }

    override fun navigateToDetails(person: Persons) {
        navigateToDetailsEvent.value = person
    }

    fun resetNavigateToDetailsEvent() {
        navigateToDetailsEvent.value = null
    }
}