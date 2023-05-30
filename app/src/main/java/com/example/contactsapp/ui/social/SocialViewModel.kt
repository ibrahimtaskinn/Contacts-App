package com.example.contactsapp.ui.social

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

class SocialViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Social Fragment"
    }
    val text: LiveData<String> = _text
}