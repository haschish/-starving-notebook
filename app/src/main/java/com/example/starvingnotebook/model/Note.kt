package com.example.starvingnotebook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes")
data class Note (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val _id: Int = 0,

    @ColumnInfo(name = "text")
    val text: String,

    @ColumnInfo(name = "reaction_name")
    val reactionName: String = "",

    @ColumnInfo(name = "date_created")
    val date: Long = 0,

    @ColumnInfo(name = "url_song")
    val urlSong: String = "",

    @ColumnInfo(name = "url_video")
    val urlVideo: String = "",

    @ColumnInfo(name = "url_image")
    val urlImage: String = "") {



}