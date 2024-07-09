package com.roomdbexample

import android.content.Intent
import android.media.audiofx.AudioEffect.Descriptor
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.roomdbexample.databinding.ActivityAddNotesBinding

class AddNotesActivity : AppCompatActivity() {
    lateinit var dataBaseHelper: DataBaseHelper
    lateinit var binding: ActivityAddNotesBinding
    lateinit var title: String
    lateinit var description: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        binding.btnAddDetails.setOnClickListener() {
            validations()

        }
    }

    private fun initViews() {
        dataBaseHelper = DataBaseHelper(this)
    }

    fun validations() {
        if (!TextUtils.isEmpty(binding.etNoteTitle.text.toString())) {
            title = binding.etNoteTitle.text.toString()
            binding.tvTitleTextField.isErrorEnabled = false

        } else {
            binding.tvTitleTextField.error = "Please Enter Title"
            binding.tvTitleTextField.isErrorEnabled = true
            return
        }
        if (!TextUtils.isEmpty(binding.etNoteDescription.text.toString())) {
            description = binding.etNoteDescription.text.toString()
            binding.tvDescriptionTextField.isErrorEnabled = false

        } else {
            binding.tvDescriptionTextField.error = "Please Enter Description"
            binding.tvDescriptionTextField.isErrorEnabled = true
            return
        }
        dataBaseHelper.insertData(title,description)

        startActivity(Intent(this, DisplayAllNotesActivity::class.java))
        this.finish()

    }
}