package com.example.starvingnotebook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AppViewModel : ViewModel() {

    val notes = mutableListOf<Note>(Note("It's the first day", "smile"), Note("I don't feel well", "frowning"), Note("The world is wonderful", "grin"))

    fun addNote(text: String, reaction: String) {
        notes.add(Note(text, reaction))

    }
}