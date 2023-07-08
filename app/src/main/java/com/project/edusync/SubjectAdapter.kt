package com.project.edusync

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.edusync.databinding.ItemSubjectBinding

class SubjectAdapter(
    private var list:MutableList<Subject>
): RecyclerView.Adapter<SubjectItem>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectItem {
        return SubjectItem(
            binding = ItemSubjectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        )
    }

    override fun onBindViewHolder(holder: SubjectItem, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }



}
