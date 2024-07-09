package com.roomdbexample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.roomdbexample.adapter.DisplayAllNotesAdapter
import com.roomdbexample.databinding.ActivityDisplayAllNotesBinding

class DisplayAllNotesActivity : AppCompatActivity() {
    lateinit var notesData : ArrayList<NotesPojo>
    private lateinit var binding: ActivityDisplayAllNotesBinding
    lateinit var dataBaseHelper: DataBaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayAllNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        setAdapter()


    }
    private fun initViews() {
        dataBaseHelper = DataBaseHelper(this)
        binding.btnAddNewNote.setOnClickListener(){
            startActivity(Intent(this, AddNotesActivity::class.java))
            this.finish()
        }
    }

    private fun setAdapter() {
        notesData = ArrayList()
        notesData.addAll(dataBaseHelper.readData())
        binding.rvNotesRecyclearView.adapter = DisplayAllNotesAdapter(notesData,this)
    }



}