package com.example.mininotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mininotes.Model.Notes
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_add_notes.*

class add_notes : AppCompatActivity() {
    private lateinit var realm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)
        setupRealm()
        addToDB()
        getDataIntent()
        UpdateData()
        DeleteData()
        }

       private fun addToDB(){
            btn_add.setOnClickListener {
                realm.beginTransaction()
                val currentId  = realm.where(Notes::class.java).max("id")
                val nextId = if (currentId == null) 1 else currentId.toInt()+1
                val notes = realm.createObject(Notes::class.java)
                notes.setId(nextId)
                notes.setTitle(et_title.text.toString())
                notes.setDescription(et_description.text.toString())
                startActivity(Intent(this, MainActivity::class.java))
                finish()
                realm.commitTransaction()
            }
        }

        private fun UpdateData(){
            btn_update.setOnClickListener {
                realm.beginTransaction()
                realm.where(Notes::class.java).equalTo("id", intent.getIntExtra("id", 1) ).findFirst().let {
                    it!!.setTitle(et_title.text.toString())
                    it!!.setDescription(et_description.text.toString())
                }
                realm.commitTransaction()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        private fun DeleteData(){
            btn_delete.setOnClickListener {
                realm.beginTransaction()
                realm.where(Notes::class.java).equalTo("id", intent.getIntExtra("id", 1)).findFirst().let {
                    it!!.deleteFromRealm()
                }
                realm.commitTransaction()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
        private fun setupRealm(){
            realm = Realm.getDefaultInstance()
        }

        private fun getDataIntent(){
            et_title.setText(intent.getStringExtra("title"))
            et_description.setText(intent.getStringExtra("desc"))
        }

    }

