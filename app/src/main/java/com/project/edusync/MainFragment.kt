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
                findNavController().navigate(R.id.action_global_notesFragment2)
            }
            button2.setOnClickListener {
                findNavController().navigate(R.id.action_global_diaryFragment2)
            }
            button3.setOnClickListener {
                findNavController().navigate(R.id.action_global_homeworksFragment2)
            }
            button4.setOnClickListener {
                findNavController().navigate(R.id.action_global_settingsFragment22)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}