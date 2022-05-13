package com.example.starvingnotebook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.slider.Slider

class NoteViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

}

class NotesAdapter(): RecyclerView.Adapter<NoteViewHolder>() {

    var data = listOf<Note>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.notes_note, parent, false) as ConstraintLayout

        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = data.get(position)
        holder.view.findViewById<TextView>(R.id.note_text).text = note.text
        if (note.reactionName.equals("grin")) {
            holder.view.findViewById<View>(R.id.reactionImage).background = R.drawable.face_smiling
        }

        if (note.urlSong.isEmpty()) {
            holder.view.findViewById<Slider>(R.id.soundView).visibility = View.GONE
            holder.view.findViewById<Button>(R.id.buttonSlider).visibility = View.GONE
        }
        if (note.urlVideo.isEmpty()) {
            holder.view.findViewById<View>(R.id.videoView).visibility = View.GONE
        }
        if (note.urlImage.isEmpty()) {
            holder.view.findViewById<View>(R.id.imageView).visibility = View.GONE
        }
        if (note.text.isEmpty()) {
            holder.view.findViewById<TextView>(R.id.note_text).visibility = View.GONE
        }

    }

    override fun getItemCount(): Int = data.size


}