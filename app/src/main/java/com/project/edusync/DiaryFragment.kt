package com.project.edusync

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.project.edusync.databinding.FragmentDiaryBinding

class DiaryFragment : Fragment(R.layout.fragment_diary) {
    private var binding: FragmentDiaryBinding? = null
    private var adapter: DiaryAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDiaryBinding.bind(view)
        initAdapter()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
    private fun initAdapter(){

        adapter = DiaryAdapter(
            list = DiaryRepo.list,
            onItemClick = {diary -> findNavController().navigate(R.id.action_diaryFragment_to_scheduleForDayFragment, ScheduleForDayFragment.createBundle(diary.day)) }
        )
        binding?.rvDiary?.adapter = adapter
    }
}
