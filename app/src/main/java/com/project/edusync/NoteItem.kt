package com.project.edusync

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.location.GnssAntennaInfo.Listener
import android.preference.PreferenceManager
import android.text.Editable
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.project.edusync.databinding.ItemNoteBinding
import kotlin.coroutines.coroutineContext

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