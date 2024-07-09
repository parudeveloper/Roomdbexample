package com.roomdbexample

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.roomdbexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var dataBaseHelper: DataBaseHelper
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        binding.btnAddDetails.setOnClickListener() {
           //validations()
        }
    }

    private fun initViews() {
        dataBaseHelper = DataBaseHelper(this)

    }
   /* fun validations(){
        if(!TextUtils.isEmpty(binding.etName.text.toString())) {
            dataBaseHelper.insertData(binding.etName.text.toString())
            Toast.makeText(this, binding.etName.text.toString(),Toast.LENGTH_LONG).show()
            binding.filledTextField.setErrorEnabled(false);
        } else {
            binding.filledTextField.setError("Input required");
            binding.filledTextField.setErrorEnabled(true);
        }

    }*/
}