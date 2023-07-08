package com.project.edusync

import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import com.project.edusync.databinding.FragmentEditScheduleBinding

class EditScheduleFragment : Fragment(R.layout.fragment_edit_schedule){
    private var binding: FragmentEditScheduleBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditScheduleBinding.bind(view)
        val id = arguments?.getString(ID)
        val day = arguments?.getString(DAY)
        val name = arguments?.getString(NAME)
        val auditory = arguments?.getString(AUDITORY)
        val start = arguments?.getString(START)
        val end = arguments?.getString(END)
        binding?.run {
            itItemName.text = Editable.Factory.getInstance().newEditable(name)
            itAuditory.text = Editable.Factory.getInstance().newEditable(auditory)
            itStartTime.text = Editable.Factory.getInstance().newEditable(start)
            itEndTime.text = Editable.Factory.getInstance().newEditable(end)
            saveImageButton.setOnClickListener {
                val subRef = FirebaseDatabase.getInstance().getReference("subject")
                val query = subRef.orderByChild("id").equalTo(id)
                query.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (subSnapshot in snapshot.children) {
                            val subjectRef = subSnapshot.ref
                            val updatedSubject = subSnapshot.getValue(Subject::class.java)
                            updatedSubject?.name = itItemName.text.toString()
                            updatedSubject?.auditory = itAuditory.text.toString()
                            updatedSubject?.start = itStartTime.text.toString()
                            updatedSubject?.end = itEndTime.text.toString()

                            subjectRef.setValue(updatedSubject)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {}
                })
               // findNavController().navigate(R.id.action_editScheduleFragment_to_scheduleForDayFragment)
            }

            minusImageButton.setOnClickListener {
                val subRef = FirebaseDatabase.getInstance().getReference("subject")
                val query = subRef.orderByChild("id").equalTo(id)
                query.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (subjectSnapshot in snapshot.children) {
                            val subjectRef = subjectSnapshot.ref
                            subjectRef.removeValue()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {}
                })

            }
            backImageButton.setOnClickListener {
                findNavController().navigate(R.id.action_editScheduleFragment_to_scheduleForDayFragment,
                    day?.let { it1 -> ScheduleForDayFragment.createBundle(it1) })
            }

        }


    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private const val ID = "id"
        private const val DAY =  "day"
        private const val NAME = "name"
        private const val AUDITORY = "auditory"
        private const val START = "start"
        private const val END = "end"
        fun createBundle(id: String, day: String, name: String, auditory: String, start: String, end: String ): Bundle {
            val bundle = Bundle()
            bundle.putString(ID, id)
            bundle.putString(DAY, day)
            bundle.putString(NAME, name)
            bundle.putString(AUDITORY, auditory)
            bundle.putString(START, start)
            bundle.putString(END, end)

            return bundle
        }
    }

}