package com.project.edusync

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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


        binding?.run {
            var preferences = getActivity()?.getSharedPreferences("pref", MODE_PRIVATE);
            var st = preferences?.getString("newTitle"+ note?.id.toString(), "Note #" + note?.id.toString())
            var st1 = preferences?.getString("newDescription"+ note?.id.toString(), " ")
            tvTitle.text = Editable.Factory.getInstance().newEditable(st)
            etNote.text = Editable.Factory.getInstance().newEditable(st1)
            saveImageButton.setOnClickListener {
                var newTitle = tvTitle.text.toString()
                var newDescription = etNote.text.toString()
                var preferences = getActivity()?.getSharedPreferences("pref", MODE_PRIVATE)
                var editor = preferences?.edit()
                editor?.putString("newTitle" + note?.id.toString(), newTitle)
                editor?.putString("newDescription"+ note?.id.toString(), newDescription)
                var preferences1 = NotesFragment().getActivity()?.getSharedPreferences("pref", MODE_PRIVATE)
                var editor1 = preferences1?.edit()
                editor1?.putString("newDescription"+ note?.id.toString(), newDescription)
                editor1?.putString("newTitle" + note?.id.toString(), newTitle)
                editor?.apply()
                editor1?.apply()
            }
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