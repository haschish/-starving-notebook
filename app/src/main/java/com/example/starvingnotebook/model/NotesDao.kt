package com.example.starvingnotebook.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes")
    fun getAllNotes(): List<Note>

    @Insert()
    fun insert(note: Note)
}