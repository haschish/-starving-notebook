package com.example.starvingnotebook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "days")
data class Day (

    @PrimaryKey()
    @ColumnInfo(name = "_id")
    val _id: Int,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "isDone")
    val isDone: Boolean,

    @ColumnInfo(name = "isNext")
    val isNext: Boolean = true
)