package com.project.edusync

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.project.edusync.databinding.FragmentEditDiaryBinding

class EditDiaryFragment : Fragment(R.layout.fragment_edit_diary) {
    private var db: DatabaseReference? = null
    private val SUBJECT_KEY = "subject"
    private var binding: FragmentEditDiaryBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditDiaryBinding.bind(view)
        db = FirebaseDatabase.getInstance().getReference(SUBJECT_KEY)
        var dayName = arguments?.getString(DAY)
        binding?.run {
            buttonSave.setOnClickListener{
                var day = dayName
                var name = itItemName.text.toString()
                var auditory = itAuditory.text.toString()
                var start = itStartTime.text.toString()
                var end = itEndTime.text.toString()
                var subject = Subject(day.toString(), name, auditory, start, end)

                db!!.push().setValue(subject)
            }
            buttonBack.setOnClickListener{
                findNavController().navigate(R.id.action_editDiaryFragment_to_scheduleForDayFragment)
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
    companion object{
        private const val DAY = "day"
        fun createBundle(day: String):Bundle{
            val bundle = Bundle()
            bundle.putString(DAY, day)
            return bundle
        }
    }
}