package com.project.edusync
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.project.edusync.databinding.FragmentNotesBinding


class NotesFragment : Fragment(R.layout.fragment_notes) {
    private var binding: FragmentNotesBinding? = null
    private var adapter: NoteAdapter? = null
    private var listView: RecyclerView? = null
    private var listData: MutableList<Note>? = null

    private var myDB: DatabaseReference? = null
    private val NOTE_KEY = "Note"
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
        listData = mutableListOf(Note("0", "gfhj", "fghj"))
        adapter = NoteAdapter(list = listData!!,
            onItemClick = { note ->
                findNavController().navigate(R.id.action_notesFragment_to_editNoteFragment,
                    EditNoteFragment.createBundle(note.name, note.description, note.id))}
                )
        binding?.rvTl?.adapter = adapter
        myDB = FirebaseDatabase.getInstance().getReference(NOTE_KEY)
        if(listData!!.size > 0) listData!!.clear()
        var vListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.getChildren()) {
                    var note: Note = ds.getValue(Note::class.java)!!
                    assert(note != null)
                    listData!!.add(note)
                }
                adapter!!.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }
        myDB!!.addValueEventListener(vListener)
        binding?.run {
            plusImageButton.setOnClickListener {
                findNavController().navigate(R.id.action_notesFragment_to_newNoteFragment)
            }
        }
        binding?.rvTl?.layoutManager = GridLayoutManager(requireContext(), 2)

    }
}