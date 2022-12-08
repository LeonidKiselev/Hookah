package com.example.hookah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MixName : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mix_name)
    }
    fun writeName(name: String) {
        val editor = this.getSharedPreferences("mix names", MODE_PRIVATE).edit()
        editor.putString(name, "")
        editor.apply()
    }
    fun onCLickCustom(view: View) {
        val name = findViewById<EditText>(R.id.editTextName).text.toString()
        if (name.isEmpty()) {
            Toast.makeText(this, "name can not be empty", Toast.LENGTH_SHORT).show()
        } else if (this.getSharedPreferences("mix names", MODE_PRIVATE).contains(name)) {
            Toast.makeText(this, "mix $name already exists", Toast.LENGTH_SHORT).show()
        } else {
            writeName(name)
            val intent = Intent(this, AddMix::class.java)
            intent.putExtra("mixName", name)
            startActivity(intent)
        }
    }
}