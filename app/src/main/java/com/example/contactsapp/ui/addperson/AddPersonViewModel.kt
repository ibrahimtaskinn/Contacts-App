package com.example.contactsapp.ui.addperson

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsapp.data.entity.Persons
import com.example.contactsapp.data.repository.PersonDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.MutableLiveData

@HiltViewModel
class AddPersonViewModel @Inject constructor(private val repository: PersonDaoRepository) : ViewModel() {

    val navigateToHome = MutableLiveData<Boolean>()

    fun savePerson(person_name: String, person_number: String, person_group: String) {
        val person = Persons(0, person_name, person_number, person_group)
        viewModelScope.launch {
            repository.savePerson(person)
            navigateToHome.postValue(true)
        }
    }

    fun doneNavigating() {
        navigateToHome.postValue(false)
    }
}