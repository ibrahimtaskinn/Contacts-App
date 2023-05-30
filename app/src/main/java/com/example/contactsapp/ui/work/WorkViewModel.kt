package com.example.contactsapp.ui.work

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


class WorkViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Work Fragment"
    }
    val text: LiveData<String> = _text
}