package com.example.starvingnotebook

import android.app.Application
import androidx.lifecycle.*
import com.example.starvingnotebook.model.Day
import com.example.starvingnotebook.model.NoteDatabase
import com.example.starvingnotebook.model.NotesRepository
import com.example.starvingnotebook.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppViewModel(application: Application) : AndroidViewModel(application) {
//    private val database = NoteDatabase.getDatabase(application)
    private val repository = NotesRepository(application)

    val allNotes = repository.getAllNotes()

    val allDays = repository.getAllDays()

    val lastDay = repository.getLastDay()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clear()
            repository.populateDatabase()
        }
    }

    fun insert (note : Note) = {
        repository.insert(note)
    }

    val notes = mutableListOf<Note>(
        Note(text = "It's the first day", reactionName = "smile"),
        Note(text = "I don't feel well", reactionName = "frowning"),
        Note(text = "The world is wonderful", reactionName = "grin")
    )

    fun addNote(text: String, reaction: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(Note(text = text, reactionName = reaction))
        }
    }

    fun addDay(day: Day) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertDay(day)
        }
    }


}