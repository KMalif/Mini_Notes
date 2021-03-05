package com.example.mininotes.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mininotes.Model.Notes
import com.example.mininotes.R


class MainAdapter(private val notes : MutableList<Notes>,private val listener : MainAdapter.onAdapterClick):RecyclerView.Adapter<MainAdapter.notesViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false))
    }

    override fun getItemCount(): Int {
       return notes.size
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        holder.bindModel(notes[position])
        holder.itemView.setOnClickListener {
            listener.onClikc(notes[position])
        }
    }

    fun setNotes(data : List<Notes>){
        notes.clear()
        notes.addAll(data)
        notifyDataSetChanged()
    }


    inner class notesViewHolder(i:View):RecyclerView.ViewHolder(i){
        val tvID : TextView = i.findViewById(R.id.tv_id)
        val tvTitle : TextView = i.findViewById(R.id.tv_title)
        val tvDesc : TextView = i.findViewById(R.id.tv_description)

        fun bindModel(n : Notes){
            tvID.text = n.getId().toString()
            tvTitle.text = n.getTitle()
            tvDesc.text = n.getDescription()
        }
    }

    interface onAdapterClick{
        fun onClikc(note : Notes)
    }
}