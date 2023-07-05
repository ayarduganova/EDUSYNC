package com.project.edusync

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.project.edusync.databinding.FragmentNotesBinding


class NotesFragment : Fragment(R.layout.fragment_notes) {
    private var binding: FragmentNotesBinding? = null
    private var adapter: NoteAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotesBinding.bind(view)
        initAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
    private fun initAdapter(){
        adapter = NoteAdapter(list = NoteRepository.list,
            glide = Glide.with(this),
            onItemClick = { note ->
                findNavController().navigate(R.id.action_notesFragment_to_editNoteFragment,
                    EditNoteFragment.createBundle(note.id))
            })
        binding?.rvTl?.adapter = adapter
        binding?.rvTl?.layoutManager = GridLayoutManager(requireContext(), 2)
    }
}