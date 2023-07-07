package com.project.edusync

import androidx.recyclerview.widget.RecyclerView
import com.project.edusync.databinding.ItemDiaryBinding

class DiaryItem(
    private val binding: ItemDiaryBinding,
    private val onItemClick: (Diary) -> Unit
): RecyclerView.ViewHolder(binding.root){

    fun onBind(diary: Diary){
        binding.run {
            tvDay.text = diary.day
            root.setOnClickListener{
                onItemClick(diary)
            }
        }
    }
}

