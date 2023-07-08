package com.project.edusync

import android.location.GnssAntennaInfo
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.project.edusync.databinding.ItemNoteBinding

class NoteAdapter(
    private var list: MutableList<Note>,
    private val onItemClick: (Note) -> Unit,
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
        onItemClick = onItemClick
        )


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NoteItem, position: Int) {
        holder.onBind(list[position])
    }

}