package com.example.mininotes.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mininotes.Model.Notes
import com.example.mininotes.R
import io.realm.RealmResults

class MainAdapter(private val context: Context?, private val notesList
    : RealmResults<Notes>):RecyclerView.Adapter<MainAdapter.notesViewHolder>() {

    inner class notesViewHolder(item : View):RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rv, parent, false))
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {

    }

}