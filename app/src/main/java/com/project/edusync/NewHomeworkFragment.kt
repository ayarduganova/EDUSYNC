package com.project.edusync

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.project.edusync.databinding.FragmentNewHomeworkBinding
import com.project.edusync.databinding.FragmentNewNoteBinding
import java.util.UUID

class NewHomeworkFragment : Fragment(R.layout.fragment_new_homework) {
    private var myDB: DatabaseReference? = null
    private val NOTE_KEY = "Homework"
    private var binding: FragmentNewHomeworkBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewHomeworkBinding.bind(view)
        myDB = FirebaseDatabase.getInstance().getReference(NOTE_KEY)
        binding?.run {
            buttonToHomework.setOnClickListener {
                var id =  generateUniqueID()
                var name = itNameHomework.text.toString()
                var dedlDay = itDedlineDay.text.toString()
                var dedlTime = itDedlineTime.text.toString()
                var task = itSInfo.text.toString()
                var homework = Homework(id, name, dedlDay, dedlTime, task)
                myDB!!.push().setValue(homework)
            }
            buttonBack.setOnClickListener {
                findNavController().navigate(R.id.action_newHomeworkFragment_to_homeworksFragment)
            }
        }
    }
    fun generateUniqueID(): String {
        return UUID.randomUUID().toString()
    }

}