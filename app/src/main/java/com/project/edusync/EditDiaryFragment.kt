package com.project.edusync

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.project.edusync.databinding.FragmentEditDiaryBinding

class EditDiaryFragment : Fragment(R.layout.fragment_edit_diary) {
    private var name: TextInputEditText? = null
    private var auditory: TextInputEditText? = null
    private var start: TextInputEditText? = null
    private var end: TextInputEditText? = null
    private var binding: FragmentEditDiaryBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditDiaryBinding.bind(view)
        name = binding?.itItemName
        auditory = binding?.itAuditory
        start = binding?.itStartTime
        end = binding?.itEndTime

        /*binding?.buttonToDiaryFragment?.setOnClickListener{
            findNavController().navigate(R.id.action_editDiaryFragment_to_diaryFragment)

        }*/
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}