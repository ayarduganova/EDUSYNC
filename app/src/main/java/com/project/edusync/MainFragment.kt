package com.project.edusync

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.project.edusync.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {
    private var binding: FragmentMainBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        binding?.run {
            button.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_notesFragment)
            }
            button2.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_diaryFragment)
            }
            button3.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_homeworksFragment)
            }
            button4.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_settingsFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}