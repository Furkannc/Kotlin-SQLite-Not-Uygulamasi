package com.example.noteapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_row.view.*




class RecyclerAdapter(val idList:ArrayList<Int>,val titleList: ArrayList<String>, val contentList:ArrayList<String>) : RecyclerView.Adapter<RecyclerAdapter.NoteList>() {
    class NoteList(ViewHolder : View) : RecyclerView.ViewHolder(ViewHolder) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteList {

        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row,parent,false)
        return NoteList(itemView)
    }

    override fun onBindViewHolder(holder: NoteList, position: Int) {
        holder.itemView.rcyclerViewTextView.text= titleList.get(position)

        holder.itemView.setOnClickListener {

            val intent=Intent(holder.itemView.context,notePage::class.java)

            intent.putExtra("id",idList.get(position))
            intent.putExtra("title",titleList.get(position))
            intent.putExtra("content",contentList.get(position))

            holder.itemView.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return  titleList.size
    }

}