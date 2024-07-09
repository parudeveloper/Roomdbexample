package com.roomdbexample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.roomdbexample.databinding.ActivityEditNoteDetailsBinding


class EditNoteDetailsActivity : AppCompatActivity() {
    private var id = 0L
    private lateinit var title: String
    private lateinit var description: String
    private lateinit var dataBaseHelper: DataBaseHelper
    private lateinit var binding: ActivityEditNoteDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditNoteDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataBaseHelper = DataBaseHelper(this)
        initViews()
    }


    private fun initViews() {
        val item = intent.getParcelableExtra<NotesPojo>("notesItem")
        item?.let {
            id = item.id
            binding.etNoteTitle.setText(item.noteTitle)
            binding.etNoteDescription.setText(item.noteDescription)
        }

        Toast.makeText(this, "This is Id Retrieving ID $id", Toast.LENGTH_LONG).show()

       // title = binding.etNoteTitle.text.toString()
       // description = binding.etNoteDescription.text.toString()


        binding.btnUpdateDetails.setOnClickListener() {
            dataBaseHelper.update(id, binding.etNoteTitle.text.toString(),binding.etNoteDescription.text.toString())
            Log.i("EditNoteDetailsActivity",dataBaseHelper.readData().toString())
            startActivity(Intent(this, DisplayAllNotesActivity::class.java))
            finish()


        }
    }
}