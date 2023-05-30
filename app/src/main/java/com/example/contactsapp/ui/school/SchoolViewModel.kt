package com.example.contactsapp.ui.school

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

class SchoolViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is School Fragment"
    }
    val text: LiveData<String> = _text
}