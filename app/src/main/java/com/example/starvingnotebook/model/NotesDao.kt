package com.example.starvingnotebook.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes")
    fun getAllNotes(): LiveData<List<Note>>

    @Insert()
    fun insert(note: Note)

    @Query("DELETE FROM notes")
    fun deleteAll()
}