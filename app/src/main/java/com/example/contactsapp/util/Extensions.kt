package com.example.contactsapp.util

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

fun Navigation.skip(it: View, id: Int) {
    findNavController(it).navigate(id)
}

fun Navigation.skip(it: View, id: NavDirections) {
    findNavController(it).navigate(id)
}