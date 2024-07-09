package com.roomdbexample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.roomdbexample.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    /*
    * var obj = News()
    * Receivers // While activity destroying we  need to un register the receiver
    * */
    lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddDetails.setOnClickListener() {
            startActivity(Intent(this, AddNotesActivity::class.java))
            this.finish()
        }

        binding.btnRetriveDetails.setOnClickListener() {
            startActivity(Intent(this, DisplayAllNotesActivity::class.java))
            this.finish()
        }

    }
}