package com.example.roomdatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class NotesAdapter(
    private val listOfNotes : List<Note>,
    private val clickListener : ClickListener
) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val image : ImageView = view.findViewById(R.id.img1)
        val noteHead: TextView = view.findViewById(R.id.txt1)
        val noteDesc : TextView = view.findViewById(R.id.txt2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_data,parent,false)
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfNotes.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentItem = listOfNotes[position]
        holder.noteHead.text = currentItem.noteName
        holder.noteDesc.text = currentItem.noteContent

        holder.itemView.setOnClickListener{
            clickListener.updateNote(currentItem)

        }
        holder.image.setOnClickListener{
            clickListener.deleteNote(currentItem)

        }

    }
    interface ClickListener {
        fun updateNote(note: Note)

        fun deleteNote(note: Note)
    }
}

