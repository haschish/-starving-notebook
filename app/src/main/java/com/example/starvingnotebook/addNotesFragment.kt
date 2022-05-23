package com.example.starvingnotebook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI


class generateNotesFragment : Fragment() {
    private lateinit var editText: EditText
    private lateinit var reactionGroup: RadioGroup
    private lateinit var viewModel: AppViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(AppViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_add_notes, container, false)
        editText = view.findViewById<EditText>(R.id.addNoteText)
        reactionGroup = view.findViewById<RadioGroup>(R.id.radioGroup)

        view.findViewById<Button>(R.id.addNoteButton).setOnClickListener { addNote() }

        return view
    }

    private fun addNote() {
        val text = editText.text
        val reaction = when (reactionGroup.checkedRadioButtonId) {
            R.id.radioButtonGrin -> "grin"
            R.id.radioButtonSmile -> "smile"
            R.id.radioButtonNeutral -> "neutral"
            R.id.radioButtonFrowning -> "frowning"
            R.id.radioButtonCrying -> "crying"
            else -> ""
        }

        viewModel.addNote(text.toString(), reaction)

        NavigationUI.navigateUp(findNavController(), null)
    }
}