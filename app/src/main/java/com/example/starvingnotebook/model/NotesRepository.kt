package com.example.starvingnotebook.model

import android.app.Application
import androidx.lifecycle.LiveData
import kotlin.concurrent.thread

class NotesRepository(application: Application) {
    private val database = NoteDatabase.getDatabase(application)
    private val notesDao: NotesDao = database.noteDao()

    private val notes: LiveData<List<Note>> = notesDao.getAllNotes()

    fun getAllNotes() = notes

    fun insert(note: Note) {
        notesDao.insert(note)
    }

    fun populateDatabase() {
        notesDao.insert(Note(text = "It's the first day", reactionName = "smile"))
        notesDao.insert(Note(text = "I don't feel well", reactionName = "frowning"))
        notesDao.insert(Note(text = "The world is wonderful", reactionName = "grin"))
    }
}