package com.project.edusync

import android.content.Context
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.project.edusync.databinding.ItemNoteBinding

class NoteItem(
    private val binding: ItemNoteBinding,
    private val glide: RequestManager,
    private val onItemClick: (Note) -> Unit,
) : ViewHolder(binding.root) {

    private val options: RequestOptions = RequestOptions
        .diskCacheStrategyOf(DiskCacheStrategy.NONE)

    private val context: Context
        get() = itemView.context

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