package com.example.contactsapp.ui.family

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


class FamilyViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Family Fragment"
    }
    val text: LiveData<String> = _text
}