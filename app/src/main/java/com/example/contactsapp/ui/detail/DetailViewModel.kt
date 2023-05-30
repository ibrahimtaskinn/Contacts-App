package com.example.contactsapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsapp.data.entity.Persons
import com.example.contactsapp.data.repository.PersonDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(var repository: PersonDaoRepository ) : ViewModel() {

    val navigateToHome = MutableLiveData<Boolean>()
    fun updatePerson(person_id: Int, person_name: String, person_number: String) {
        val person = Persons(person_id, person_name, person_number)
        viewModelScope.launch {
            repository.updatePerson(person)
            navigateToHome.postValue(true)
        }
    }

    fun doneNavigating() {
        navigateToHome.postValue(false)
    }
}