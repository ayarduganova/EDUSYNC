package com.project.edusync

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.project.edusync.databinding.FragmentNotesBinding
import com.project.edusync.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private var binding: FragmentSettingsBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)
        binding?.run {
            buttonClearNotes.setOnClickListener {
                delete("Note")
            }
            buttonClearHomeworks.setOnClickListener {
                delete("Homework")
            }
            buttonClearAll.setOnClickListener {
                delete("Note")
                delete("Homework")
                delete("subject")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
    fun delete(key: String){
        val nRef = FirebaseDatabase.getInstance().getReference(key)
        nRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (nSnapshot in snapshot.children) {
                    val nRef = nSnapshot.ref
                    nRef.removeValue()
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }


}