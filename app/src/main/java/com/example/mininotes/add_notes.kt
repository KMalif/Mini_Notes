package com.example.mininotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mininotes.Model.Notes
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_add_notes.*

class add_notes : AppCompatActivity() {
    private lateinit var realm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)

        realm = Realm.getDefaultInstance()
        btn_add.setOnClickListener {

            addNotesToDB()
        }
    }

    private fun addNotesToDB() {
        try {
            realm.beginTransaction()
            val currentIdNumber: Number? = realm.where(Notes::class.java).max("id")
            val nextID : Int

            nextID = if(currentIdNumber == null){
                1
            }else{
                currentIdNumber.toInt()+1
            }

            val notes = Notes()
            notes.setTitle(et_title.text.toString())
            notes.setDescription(et_description.text.toString())
            notes.setId(nextID)

            //copy and commit
            realm.copyToRealmOrUpdate(notes)
            realm.commitTransaction()
            Toast.makeText(this, "Notes Added", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()

        } catch (e:Exception){
            Toast.makeText(this, "Failed $e", Toast.LENGTH_SHORT).show()
        }
    }
}