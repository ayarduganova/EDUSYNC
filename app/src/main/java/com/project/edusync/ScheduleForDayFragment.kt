package com.project.edusync

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.project.edusync.databinding.FragmentScheduleForDayBinding

class ScheduleForDayFragment : Fragment(R.layout.fragment_schedule_for_day) {
    private var binding: FragmentScheduleForDayBinding? = null
    private var adapter: SubjectAdapter? = null
    private var listData: MutableList<Subject>? = null
    private var db: DatabaseReference? = null
    private val SUBJECT_KEY = "subject"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentScheduleForDayBinding.bind(view)
        var day = arguments?.getString(DAY)
        listData?.forEach {
            if(it.day == day){
                day = it.day
            }
        }
        day?.let { init(it) }

        binding?.plusButton?.setOnClickListener {
            findNavController().navigate(R.id.action_scheduleForDayFragment_to_editDiaryFragment, EditDiaryFragment.createBundle(
                day.toString()
            ))
        }
        binding?.backButton?.setOnClickListener{
            findNavController().navigate(R.id.action_scheduleForDayFragment_to_diaryFragment)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
    private fun init(day: String) {
        listData = mutableListOf()
        db = FirebaseDatabase.getInstance().getReference(SUBJECT_KEY)
        adapter = SubjectAdapter(listData!!)
        binding?.rvSchedule?.adapter = adapter

        if (listData!!.size > 0) {
            listData!!.clear()
        }

        var vListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.getChildren()) {
                    val sub: Subject = ds.getValue(Subject::class.java)!!
                    if(sub.day == day) {
                        listData!!.add(sub)
                    }
                }

                adapter!!.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
            }
        }
        db!!.addValueEventListener(vListener)
        binding?.rvSchedule?.layoutManager = LinearLayoutManager(requireContext())
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