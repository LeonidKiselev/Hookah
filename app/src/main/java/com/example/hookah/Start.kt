package com.example.hookah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Start : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start)
    }
    fun onClickTobacco(view: View) {
        val intent = Intent(this, Tobaccos::class.java)
        startActivity(intent)
    }
    fun onClickTaste(view: View) {
        val intent = Intent(this, Tastes::class.java)
        startActivity(intent)
    }
    fun onClickMix(view: View) {
        val intent = Intent(this, Mixes::class.java)
        startActivity(intent)
    }
}