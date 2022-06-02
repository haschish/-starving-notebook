package com.example.starvingnotebook.model

import android.app.Application
import android.icu.text.SimpleDateFormat
import androidx.lifecycle.LiveData
import java.util.*
import kotlin.concurrent.thread

class NotesRepository(application: Application) {
    private val database = NoteDatabase.getDatabase(application)

    private val notes: LiveData<List<Note>> = database.noteDao().getAllNotes()
    private val days = database.daysDao().getAll()

    fun getAllNotes() = notes

    fun getAllDays() = days

    fun insert(note: Note) {
        database.noteDao().insert(note)
    }

    fun insertDay(day: Day) {
        database.daysDao().insert(day)
    }

    fun populateDatabase() {
        with(database.noteDao()) {
            insert(Note(text = "It's the first day", reactionName = "smile"))
            insert(Note(text = "I don't feel well", reactionName = "frowning"))
            insert(Note(text = "The world is wonderful", reactionName = "grin"))
        }


        with(database.daysDao()) {
            insert(Day(getWeekOfYear(getCalendar("2022-05-02")), "2022-05-02", false, false))
            insert(Day(getWeekOfYear(getCalendar("2022-05-11")), "2022-05-11", true, false))
            insert(Day(getWeekOfYear(getCalendar("2022-05-19")), "2022-05-19", true, false))
            insert(Day(getWeekOfYear(getCalendar("2022-05-24")), "2022-05-24", true, false))
            insert(Day(getWeekOfYear(getCalendar("2022-06-03")), "2022-06-03", false))
        }
    }

    fun clear() {
        database.noteDao().deleteAll()
        database.daysDao().deleteAll()
    }

    fun getLastDay() = database.daysDao().getLastDay()

    private fun getWeekOfYear(date: Calendar = Calendar.getInstance()): Int {
        val year = date.get(Calendar.YEAR)
        return year * 100 + date.get(Calendar.WEEK_OF_YEAR)
    }

    private fun getCalendar(date: String): Calendar {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val calendar = Calendar.getInstance()
        calendar.time = sdf.parse(date)
        return calendar
    }
}