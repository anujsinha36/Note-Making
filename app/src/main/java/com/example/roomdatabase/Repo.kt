package com.example.roomdatabase

class Repo(private val dao: Dao) {
    
    fun insert(note:Note){
        dao.insert(note)
    }
    fun update(note:Note){
        dao.update(note)
    }
    fun delete(note:Note){
        dao.delete(note)
    }
    fun getAllNotes() = dao.getAllNotes()



}