package com.example.starvingnotebook.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DaysDao {

    @Query("SELECT * FROM days")
    fun getAll(): LiveData<List<Day>>

    @Insert()
    fun insert(day: Day)

    @Update()
    fun update(day: Day)

    @Query("SELECT * FROM days ORDER BY _id DESC LIMIT 1")
    fun getLastDay(): LiveData<Day>

    @Query("DELETE FROM days")
    fun deleteAll()
}