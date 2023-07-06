package com.project.edusync
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
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
            },
            savedName = {
                note ->
                var str = getActivity()?.getSharedPreferences("pref", MODE_PRIVATE)?.getString("newTitle"+ note?.id.toString(), note.name)
                note.name = str.toString()
            },
            savedDescription = {
                note ->
                var str1 = getActivity()?.getSharedPreferences("pref", MODE_PRIVATE)?.getString("newDescription"+ note?.id.toString(), note.description)
                note.description = str1.toString()
            })
        binding?.rvTl?.adapter = adapter
        binding?.run {
            plusImageButton.setOnClickListener {
                NoteRepository.list.add(Note(name = "Note #" + (NoteRepository.list.size + 1).toString(), description = "Enter text...", id = NoteRepository.list.size + 1))
                adapter!!.notifyItemInserted(NoteRepository.list.size - 1)
            }
            minusImageButton.setOnClickListener {
                NoteRepository.list.removeIf { note -> note.id == NoteRepository.list.size }
                adapter!!.notifyItemRemoved(NoteRepository.list.size + 1)
            }
        }
        binding?.rvTl?.layoutManager = GridLayoutManager(requireContext(), 2)
    }
}