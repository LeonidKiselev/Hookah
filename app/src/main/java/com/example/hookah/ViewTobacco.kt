package com.example.hookah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class ViewTobacco : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_tobacco)
        val tobaccoName = intent.getStringExtra("name").toString()
        val textTobaccoName = findViewById<TextView>(R.id.textTobaccoName)
        textTobaccoName.text = tobaccoName
        val tobaccos = this.getSharedPreferences("tobaccos", MODE_PRIVATE).getString(tobaccoName, "").toString().split(" ").toTypedArray()
        val meanStrength = tobaccos[0]
        val meanVapor = tobaccos[1]
        val textNumberStrength = findViewById<TextView>(R.id.textNumberStrength)
        textNumberStrength.text = meanStrength
        val textNumberVapor = findViewById<TextView>(R.id.textNumberVapor)
        textNumberVapor.text = meanVapor
    }
    fun onClickDelete(view: View) {
        val tobaccoName = intent.getStringExtra("name").toString()
        this.getSharedPreferences("tobaccos", MODE_PRIVATE).edit().remove(tobaccoName).apply()
        this.getSharedPreferences("tobacco names", MODE_PRIVATE).edit().remove(tobaccoName).apply()
        Toast.makeText(this, "tobacco $tobaccoName deleted", Toast.LENGTH_SHORT).show()
    }
}