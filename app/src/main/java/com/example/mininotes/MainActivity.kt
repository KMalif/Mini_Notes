package com.example.mininotes

import android.content.Intent
import android.icu.lang.UCharacter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mininotes.Adapter.MainAdapter
import com.example.mininotes.Model.Notes
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var noteAdapter: MainAdapter
    lateinit var realm : Realm
    val lm = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toAddNotes()
        recyle()
        getAllUser()
    }
    fun toAddNotes(){
        fab_add.setOnClickListener{
            startActivity(Intent(this, add_notes::class.java))
            finish()
        }
    }
    private fun getAllUser(){
        realm.where(Notes::class.java).findAll().let {
            noteAdapter.setNotes(it)
        }
    }
    fun recyle(){
        rv_main.layoutManager = lm
        noteAdapter = MainAdapter(mutableListOf(), object : MainAdapter.onAdapterClick{
            override fun onClikc(note: Notes) {
                startActivity(Intent(this@MainActivity, add_notes::class.java)
                        .putExtra("id", note.getId())
                        .putExtra("title", note.getTitle())
                        .putExtra("desc", note.getDescription())
                )
            }
        })
        rv_main.adapter = noteAdapter
        Realm.init(applicationContext)
        realm = Realm.getDefaultInstance()
        getAllUser()
    }

}