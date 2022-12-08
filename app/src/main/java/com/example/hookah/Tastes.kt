package com.example.hookah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class Tastes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tastes)
        val names = (this.getSharedPreferences("taste names", MODE_PRIVATE).all as Map<*, *>).toString()
        val namesArray = names.substring(1, names.length - 1).replace("=".toRegex(), "").replace(", ".toRegex(), "\n").split("\n").toTypedArray()
        val arrayAdapter = ArrayAdapter(this, R.layout.custom_list_view, namesArray)
        val listView = findViewById<ListView>(R.id.listNames)
        listView.adapter = arrayAdapter
        listView.setOnItemClickListener { _, _, position, _ ->
            val item = listView.getItemAtPosition(position).toString()
            val intent = Intent(this, ViewTaste::class.java)
            intent.putExtra("name", item)
            startActivity(intent)
        }
    }
    fun onCLickAddTaste(view: View) {
        if ((this.getSharedPreferences("taste names", MODE_PRIVATE).all as Map<*, *>).count() == 8) {
            Toast.makeText(this, "maximum count of tastes reached", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(this, AddTaste::class.java)
            startActivity(intent)
        }
    }
}