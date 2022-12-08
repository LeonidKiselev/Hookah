package com.example.hookah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class ViewTaste : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_taste)
        val tasteName = intent.getStringExtra("name").toString()
        val textTasteName = findViewById<TextView>(R.id.textTasteName)
        textTasteName.text = tasteName
        val tastes = this.getSharedPreferences("tastes", MODE_PRIVATE).getString(tasteName, "").toString().split(" ").toTypedArray()
        val meanSweetness = tastes[0]
        val meanSourness = tastes[1]
        val textNumberSweetness = findViewById<TextView>(R.id.textNumberSweetness)
        textNumberSweetness.text = meanSweetness
        val textNumberSourness = findViewById<TextView>(R.id.textNumberSourness)
        textNumberSourness.text = meanSourness
    }
    fun onClickDelete(view: View) {
        val tasteName = intent.getStringExtra("name").toString()
        this.getSharedPreferences("tastes", MODE_PRIVATE).edit().remove(tasteName).apply()
        this.getSharedPreferences("taste names", MODE_PRIVATE).edit().remove(tasteName).apply()
        Toast.makeText(this, "taste $tasteName deleted", Toast.LENGTH_SHORT).show()
    }
}