package com.roomdbexample.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roomdbexample.DashboardActivity
import com.roomdbexample.DataBaseHelper
import com.roomdbexample.EditNoteDetailsActivity
import com.roomdbexample.NotesPojo
import com.roomdbexample.databinding.AdapterDisplayAllNotesBinding


class DisplayAllNotesAdapter(
    private var notesData: ArrayList<NotesPojo>,context : Context
) :
    RecyclerView.Adapter<DisplayAllNotesAdapter.MyNotesViewHolder>() {

    lateinit var binding: AdapterDisplayAllNotesBinding
    private lateinit var dataBaseHelper: DataBaseHelper


    class MyNotesViewHolder(private var itemBinding: AdapterDisplayAllNotesBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindData(notesPojo: NotesPojo, position: Int, dataBaseHelper: DataBaseHelper) {
            with(itemBinding) {
                tvTitle.text = notesPojo.noteTitle
                tvDescription.text = notesPojo.noteDescription

                btnEdit.setOnClickListener() {
                    val intent = Intent(it.context, EditNoteDetailsActivity::class.java)
                    intent.putExtra("notesItem", notesPojo)
                    it.context.startActivity(intent)
                    (it.context as Activity).finish()
                }

                btnDelete.setOnClickListener() {
                    dataBaseHelper.delete(notesPojo.id)
                    val intent = Intent(it.context, DashboardActivity::class.java)
                    it.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyNotesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = AdapterDisplayAllNotesBinding.inflate(layoutInflater, parent, false)
        return MyNotesViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return notesData.size
    }

    override fun onBindViewHolder(holder: MyNotesViewHolder, position: Int) {
        val data = notesData[position]
        dataBaseHelper= DataBaseHelper(holder.itemView.context)
        holder.bindData(notesData[position], position,dataBaseHelper)
    }
}