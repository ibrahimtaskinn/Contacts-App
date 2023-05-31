package com.example.contactsapp.ui.family

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
class FriendsViewModel @Inject constructor(val repository: PersonDaoRepository ) : ViewModel(),
    PersonInteractions {

    val navigateToDetailsEvent = MutableLiveData<Persons?>()


    override fun deletePerson(person: Persons) {
        viewModelScope.launch {
            repository.deletePerson(person)
            loadPersons()
        }
    }

    override fun searchPerson(search: String) {
        viewModelScope.launch {
            repository.searchGroupPerson(listOf("Friends"), search)
        }
    }

    override fun loadPersons(){
        viewModelScope.launch {
            repository.getPersonsByGroup("Friends")
        }
    }

    override fun navigateToDetails(person: Persons) {
        navigateToDetailsEvent.value = person
    }

    fun resetNavigateToDetailsEvent() {
        navigateToDetailsEvent.value = null
    }
}
