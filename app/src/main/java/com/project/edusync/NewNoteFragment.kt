package com.project.edusync

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.project.edusync.databinding.FragmentEditNoteBinding
import com.project.edusync.databinding.FragmentNewNoteBinding


class NewNoteFragment : Fragment(R.layout.fragment_new_note) {
    private var myDB: DatabaseReference? = null
    private val NOTE_KEY = "Note"
    private var binding: FragmentNewNoteBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewNoteBinding.bind(view)
        myDB = FirebaseDatabase.getInstance().getReference(NOTE_KEY)
        binding?.run {
            buttonToNote.setOnClickListener {
                var id = myDB!!.key
                var name = itNoteName.text.toString()
                var description = itNoteInfo.text.toString()
                var note = Note(id = id.toString(), name = name, description = description)
                myDB!!.push().setValue(note)
            }
            backButton.setOnClickListener {
                findNavController().navigate(R.id.action_newNoteFragment_to_notesFragment)
            }
        }
    }

}