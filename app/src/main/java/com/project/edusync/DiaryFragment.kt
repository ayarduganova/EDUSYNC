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
    /*private var adapter1:SubjectAdapter? = null
    private var name: TextInputEditText? = null
    private var aud: TextInputEditText? = null
    private var start: TextInputEditText? = null
    private var end: TextInputEditText? = null*/

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
        //name
        adapter = DiaryAdapter(
            list = DiaryRepo.list,
            onItemClick = {diary -> findNavController().navigate(R.id.action_diaryFragment_to_scheduleForDayFragment) }
        )
        binding?.rvDiary?.adapter = adapter

        /*adapter1 = SubjectAdapter(
            //list = DiaryRepo.list,
            onItemClick = {
                subject ->  findNavController().navigate(R.id.action_diaryFragment_to_editDiaryFragment)
            },
            name = {subject ->  subject.name},
            aud = {subject -> subject.auditory },
            start = {subject ->  subject.start},
            end = {subject -> subject.end }
        )*/

    }

}