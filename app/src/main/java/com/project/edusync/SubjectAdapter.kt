package com.project.edusync

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.edusync.databinding.ItemSubjectBinding

class SubjectAdapter(
                   private val onItemClick:(Subject) -> Unit,
                   private val name:(Subject) -> Unit,
                   private val aud:(Subject) -> Unit,
                   private val start:(Subject) -> Unit,
                   private val end:(Subject) -> Unit


): RecyclerView.Adapter<SubjectItem>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectItem {
        return SubjectItem(
            binding = ItemSubjectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClick = onItemClick,
            name = name,
            aud = aud,
            start = start,
            end = end
        )
    }

    override fun onBindViewHolder(holder: SubjectItem, position: Int) {
        //holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return 7
    }



}
