package com.project.edusync

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class GetData {

    fun getDataNotesFromDataBase(listTemp: MutableList<Note>, adapter: NoteAdapter) : MutableList<Note>{
         var vListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.getChildren()) {
                    val note: Note = ds.getValue(Note::class.java)!!
                    listTemp.add(note)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        return listTemp
    }
    fun getListenerNote(listTemp: MutableList<Note>, adapter: NoteAdapter) : ValueEventListener{
        if(listTemp.size > 0) listTemp.clear()
        var vListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.getChildren()) {
                    val note: Note = ds.getValue(Note::class.java)!!
                    listTemp.add(note)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }
        return vListener
    }
    fun getNotes(listTemp: MutableList<Note>, adapter: NoteAdapter)  {
        if(listTemp.size > 0) listTemp.clear()
        var vListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.getChildren()) {
                    val note: Note = ds.getValue(Note::class.java)!!
                    listTemp.add(note)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }
    }
}

