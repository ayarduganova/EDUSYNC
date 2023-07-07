package com.project.edusync

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.project.edusync.databinding.FragmentScheduleForDayBinding

class ScheduleForDayFragment: Fragment(R.layout.fragment_schedule_for_day) {
    private var binding: FragmentScheduleForDayBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.plusButton?.setOnClickListener { 
            findNavController().navigate(R.id.action_scheduleForDayFragment_to_editDiaryFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}