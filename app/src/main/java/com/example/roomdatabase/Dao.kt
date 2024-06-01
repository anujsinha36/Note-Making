package com.example.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(note: Note)
    @Delete
    fun delete(note: Note)

    @Query("Select * from notesTable")
    fun getAllNotes(): LiveData<List<Note>>


}