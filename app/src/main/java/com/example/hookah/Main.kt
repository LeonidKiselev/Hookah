package com.example.hookah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
    }
    fun onClickStart(view: View) {
        val intent = Intent(this, Start::class.java)
        startActivity(intent)
    }
    fun onClickClear(view: View) {
        this.getSharedPreferences("tobacco names", MODE_PRIVATE).edit().clear().apply()
        this.getSharedPreferences("tobaccos", MODE_PRIVATE).edit().clear().apply()
        this.getSharedPreferences("taste names", MODE_PRIVATE).edit().clear().apply()
        this.getSharedPreferences("tastes", MODE_PRIVATE).edit().clear().apply()
        this.getSharedPreferences("mix names", MODE_PRIVATE).edit().clear().apply()
        this.getSharedPreferences("now mix", MODE_PRIVATE).edit().clear().apply()
        this.getSharedPreferences("mix tobaccos", MODE_PRIVATE).edit().clear().apply()
        this.getSharedPreferences("mix tastes", MODE_PRIVATE).edit().clear().apply()
        Toast.makeText(this, "data cleared", Toast.LENGTH_SHORT).show()
    }
}