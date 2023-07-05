package com.project.edusync

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.project.edusync.databinding.FragmentEditDiaryBinding
import com.project.edusync.databinding.FragmentEditNoteBinding


class EditNoteFragment : Fragment(R.layout.fragment_edit_note) {
    private var binding: FragmentEditNoteBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditNoteBinding.bind(view)
        val id = arguments?.getInt(ID)
        var note: Note? = null
        val list = NoteRepository.list
        list.forEach{
            if(it.id == id){
                note = it
            }
        }
        val glide = Glide.with(this)
        binding?.run {
            tvTitle.text = Editable.Factory.getInstance().newEditable("Note #" + note?.id.toString())
            etNote.text = Editable.Factory.getInstance().newEditable("Enter text...")
            backImageButton.setOnClickListener {
                findNavController().navigate(R.id.action_editNoteFragment_to_notesFragment)
            }
        }
    }
    companion object {
        private const val ID = "ID"
        fun createBundle(id: Int): Bundle {
            val bundle = Bundle()
            bundle.putInt(ID, id)
            return bundle
        }
    }
}