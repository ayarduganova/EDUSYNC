package com.project.edusync

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.project.edusync.databinding.ItemNoteBinding

class NoteItem(
    private val binding: ItemNoteBinding,
    private val onItemClick: (Note) -> Unit,
) : ViewHolder(binding.root) {

    fun onBind(note: Note) {
        binding.run {
            tvTitle.text = note.name
            etNote.text = note.description
            root.setOnClickListener {
                onItemClick(note)
            }
        }
    }
}