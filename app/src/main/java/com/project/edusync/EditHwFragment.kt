package com.project.edusync

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.project.edusync.databinding.FragmentEditHwBinding
import com.project.edusync.databinding.FragmentEditNoteBinding


class EditHwFragment : Fragment(R.layout.fragment_edit_hw) {
    private var binding: FragmentEditHwBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditHwBinding.bind(view)
        val id = arguments?.getString(ID)
        val name = arguments?.getString(NAME)
        val data = arguments?.getString(DAY)
        val time = arguments?.getString(TIME)
        val desc = arguments?.getString(DESC)
        binding?.run {
            itNameHomework.text = Editable.Factory.getInstance().newEditable(name)
            itDedlineDay.text = Editable.Factory.getInstance().newEditable(data)
            itDedlineTime.text = Editable.Factory.getInstance().newEditable(time)
            itSInfo.text = Editable.Factory.getInstance().newEditable(desc)
            buttonToHomework.setOnClickListener {
                val notesRef = FirebaseDatabase.getInstance().getReference("Homework")
                val query = notesRef.orderByChild("id").equalTo(id)
                query.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (hwSnapshot in snapshot.children) {
                            val hwRef = hwSnapshot.ref
                            val updatedHw = hwSnapshot.getValue(Homework::class.java)
                            updatedHw?.nameHW = itNameHomework.text.toString()
                            updatedHw?.dedlDay = itDedlineDay.text.toString()
                            updatedHw?.dedlTime = itDedlineTime.text.toString()
                            updatedHw?.Task = itSInfo.text.toString()
                            hwRef.setValue(updatedHw)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        // Обработка ошибки, если необходимо
                    }
                })
            }
            backButton.setOnClickListener {
                findNavController().navigate(R.id.action_editHwFragment_to_homeworksFragment)
            }
            minusImageButton.setOnClickListener {
                val hwRef = FirebaseDatabase.getInstance().getReference("Homework")
                val query = hwRef.orderByChild("id").equalTo(id)
                query.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (hwSnapshot in snapshot.children) {
                            val hwRef = hwSnapshot.ref
                            hwRef.removeValue()
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
        private const val ID = "ID"
        private const val NAME = "NAME"
        private const val DAY = "DAY"
        private const val TIME = "TIME"
        private const val DESC = "DESC"
        fun createBundle(id: String, name: String, data: String, time: String, desc: String): Bundle {
            val bundle = Bundle()
            bundle.putString(ID, id)
            bundle.putString(NAME, name)
            bundle.putString(DAY, data)
            bundle.putString(TIME, time)
            bundle.putString(DESC, desc)
            return bundle
        }
    }
}