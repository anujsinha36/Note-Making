package com.example.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity( tableName = "notesTable")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val noteName: String,
    val noteContent: String
)
