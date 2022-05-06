package com.example.starvingnotebook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AppViewModel : ViewModel() {

    val notes = mutableListOf<Note>(Note("model note 1"), Note("note 2"), Note("note 3"))

    fun addNote(text: String) {
        notes.add(Note(text))
    }
}