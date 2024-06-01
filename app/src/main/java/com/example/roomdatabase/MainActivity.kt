package com.example.roomdatabase

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NotesAdapter.ClickListener {
    private lateinit var repo: Repo
    private lateinit var notesViewModel: NotesViewModel
    private lateinit var notesViewModelFactory: NotesViewModelFactory
    private lateinit var noteDatabase: NoteDatabase
    private lateinit var recyclerView: RecyclerView
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var edt1 : EditText
    private lateinit var edt2 : EditText
    private lateinit var btnSubmit : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        noteDatabase = NoteDatabase(this)
        repo = Repo(noteDatabase.getNoteDao())
        notesViewModelFactory = NotesViewModelFactory(repo)
        notesViewModel = ViewModelProvider(this,notesViewModelFactory).get(NotesViewModel::class.java)
        recyclerView = findViewById(R.id.rv1)
        floatingActionButton = findViewById(R.id.fab1)

        notesViewModel.getAllNotes().observe(this){
            notesAdapter = NotesAdapter(it, this)
            recyclerView.adapter = notesAdapter
            recyclerView.layoutManager = LinearLayoutManager(this)
        }

        floatingActionButton.setOnClickListener {
            showDialog(isFab = true)
        }


    }

    private fun showDialog(isFab : Boolean){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_layout)

        edt1  = dialog.findViewById(R.id.edt1)
        edt2 = dialog.findViewById(R.id.edt2)
        btnSubmit = dialog.findViewById(R.id.sb_Btn)
        btnSubmit.setOnClickListener {
            val note = Note(
                noteName = edt1.text.toString(),
                noteContent = edt2.text.toString()
            )
            if (isFab){
                notesViewModel.insert(note)
            }else{
                notesViewModel.update(note)

            }
            Log.i("hasbulla", "updated")

            dialog.dismiss()
        }
        dialog.show()
    }

    override fun updateNote(note: Note) {
        showDialog(isFab = false)
    }

    override fun deleteNote(note: Note) {
        notesViewModel.delete(note)
    }
}