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
        val id = arguments?.getString(ID)
        var note : Note? = null
        val starCountRef = FirebaseDatabase.getInstance().getReference("Note")
        starCountRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children) {
                    if(ds.getValue(Note::class.java)?.id == id) note = ds.getValue(Note::class.java)
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
        binding?.run {
            tvTitle.text = Editable.Factory.getInstance().newEditable(note?.name)
                //Editable.Factory.getInstance().newEditable(note?.name)
            etNote.text = Editable.Factory.getInstance().newEditable(note?.description)
                //Editable.Factory.getInstance().newEditable(note?.description)
            backImageButton.setOnClickListener {
                findNavController().navigate(R.id.action_editNoteFragment_to_notesFragment)
            }

        }


    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private const val ID = "ID"
        fun createBundle(id: String): Bundle {
            val bundle = Bundle()
            bundle.putString(ID, id)
            return bundle
        }
    }
}