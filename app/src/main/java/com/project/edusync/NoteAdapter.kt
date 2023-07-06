package com.project.edusync

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.project.edusync.databinding.ItemNoteBinding

class NoteAdapter(
    private var list: MutableList<Note>,
    private val glide: RequestManager,
    private val onItemClick: (Note) -> Unit,
    private val savedName: (Note) -> Unit,
    private val savedDescription: (Note) -> Unit,
) : RecyclerView.Adapter<NoteItem>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteItem = NoteItem(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        glide = glide,
        onItemClick = onItemClick,
        savedName = savedName,
        savedDescription = savedDescription
        )


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NoteItem, position: Int) {
        holder.onBind(list[position])
    }

}