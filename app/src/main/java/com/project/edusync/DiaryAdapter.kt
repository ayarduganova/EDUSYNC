package com.project.edusync

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.edusync.databinding.ItemDiaryBinding

class DiaryAdapter(private val list: List<Diary>,
                   private val onItemClick:(Diary) -> Unit

): RecyclerView.Adapter<DiaryItem>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryItem {
        return DiaryItem(
            binding = ItemDiaryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClick = onItemClick
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DiaryItem, position: Int) {
        val curDay = list[position]
        holder.onBind(list[position])
    }

}
