package com.project.edusync


import androidx.recyclerview.widget.RecyclerView
import com.project.edusync.databinding.ItemSubjectBinding

class SubjectItem (
    private val binding: ItemSubjectBinding,
    ): RecyclerView.ViewHolder(binding.root){

        fun onBind(subject: Subject){
            binding.run {
                tvDay.text = subject.day
                tvName.text = subject.name
                tvAuditory.text = subject.auditory
                tvStartTime.text = subject.start
                tvEndTime.text = subject.end
                //root.setOnClickListener{

                //}
            }
        }
}