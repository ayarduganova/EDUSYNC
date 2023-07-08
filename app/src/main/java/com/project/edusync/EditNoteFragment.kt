package com.project.edusync

import android.content.Context.MODE_PRIVATE
import android.os.Build.ID
import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.project.edusync.databinding.FragmentEditNoteBinding


class EditNoteFragment : Fragment(R.layout.fragment_edit_note) {
    private var binding: FragmentEditNoteBinding? = null
    private var myDB: DatabaseReference? = null
    private var list: MutableList<Note>? = null
    private var adapter: NoteAdapter?  = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditNoteBinding.bind(view)
        val name = arguments?.getString(NAME)
        val desc = arguments?.getString(DESC)
        val id = arguments?.getString(ID)
        binding?.run {
            tvTitle.text = Editable.Factory.getInstance().newEditable(name)
            etNote.text = Editable.Factory.getInstance().newEditable(desc)
            saveImageButton.setOnClickListener {
                val notesRef = FirebaseDatabase.getInstance().getReference("Note")
                val query = notesRef.orderByChild("id").equalTo(id)
                query.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (noteSnapshot in snapshot.children) {
                            val noteRef = noteSnapshot.ref
                            val updatedNote = noteSnapshot.getValue(Note::class.java)
                            updatedNote?.name = tvTitle.text.toString()
                            updatedNote?.description = etNote.text.toString()
                            noteRef.setValue(updatedNote)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        // Обработка ошибки, если необходимо
                    }
                })
            }
            backImageButton.setOnClickListener {
                findNavController().navigate(R.id.action_editNoteFragment_to_notesFragment)
            }
            minusImageButton.setOnClickListener {
                val notesRef = FirebaseDatabase.getInstance().getReference("Note")
                val query = notesRef.orderByChild("id").equalTo(id)
                query.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (noteSnapshot in snapshot.children) {
                            val noteRef = noteSnapshot.ref
                            noteRef.removeValue()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private const val NAME = "NAME"
        private const val DESC = "DESC"
        private const val ID = "ID"
        fun createBundle(name: String, desc: String, id: String): Bundle {
            val bundle = Bundle()
            bundle.putString(NAME, name)
            bundle.putString(DESC, desc)
            bundle.putString(ID, id)
            return bundle
        }
    }
}