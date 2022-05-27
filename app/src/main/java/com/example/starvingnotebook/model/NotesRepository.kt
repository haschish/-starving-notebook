package com.example.starvingnotebook.model

import kotlin.concurrent.thread

class NotesRepository(private val notesDao: NotesDao) {

    val notes: List<Note> = notesDao.getAllNotes()

    fun getAllNotes() = notesDao.getAllNotes()

    fun insert(note: Note) {
        val thread = thread {
            notesDao.insert(note)
        }
        thread.start()
    }
}