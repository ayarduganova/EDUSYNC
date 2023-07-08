package com.project.edusync

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.project.edusync.databinding.FragmentHomeworksBinding
import com.project.edusync.databinding.FragmentNotesBinding


class HomeworksFragment : Fragment(R.layout.fragment_homeworks) {

    private var binding: FragmentHomeworksBinding? = null
    private var adapter: HomeworkAdapter? = null
    private var listData: MutableList<Homework>? = null
    private var myDB: DatabaseReference? = null
    private val HW_KEY = "Homework"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeworksBinding.bind(view)
        initAdapter()
    }
    private fun initAdapter(){
        listData = mutableListOf()
        adapter = HomeworkAdapter(list = listData!!,
            onItemClick = { hw ->
                findNavController().navigate(R.id.action_homeworksFragment_to_editHwFragment,
                    EditHwFragment.createBundle(hw.id, hw.nameHW, hw.dedlDay, hw.dedlTime, hw.Task))}
        )
        binding?.rvTl?.adapter = adapter
        myDB = FirebaseDatabase.getInstance().getReference(HW_KEY)
        if(listData!!.size > 0) listData!!.clear()
        var vListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.getChildren()) {
                    var hw: Homework = ds.getValue(Homework::class.java)!!
                    assert(hw != null)
                    listData!!.add(hw)
                }
                adapter!!.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }
        myDB!!.addValueEventListener(vListener)
        binding?.run {
            plusImageButton.setOnClickListener {
                findNavController().navigate(R.id.action_homeworksFragment_to_newHomeworkFragment)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}