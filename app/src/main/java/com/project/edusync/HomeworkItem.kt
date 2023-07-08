package com.project.edusync

import androidx.recyclerview.widget.RecyclerView
import com.project.edusync.databinding.ItemHwBinding
import com.project.edusync.databinding.ItemNoteBinding

class HomeworkItem(
    private val binding: ItemHwBinding,
    private val onItemClick: (Homework) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(homework: Homework) {
        binding.run {
            tvTitle.text = homework.nameHW
            etDesc.text = homework.dedlDay + " " + homework.dedlTime
            etHw.text = homework.Task
            root.setOnClickListener {
                onItemClick(homework)
            }
        }
    }
}