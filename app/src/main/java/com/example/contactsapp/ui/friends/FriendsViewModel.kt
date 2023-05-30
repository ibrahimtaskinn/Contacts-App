package com.example.contactsapp.ui.friends

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


class FriendsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Friends Fragment"
    }
    val text: LiveData<String> = _text
}