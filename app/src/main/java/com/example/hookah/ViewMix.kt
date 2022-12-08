package com.example.hookah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class ViewMix : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_mix)
        val mixName = intent.getStringExtra("name").toString()
        val textMixName = findViewById<TextView>(R.id.textMixName)
        textMixName.text = mixName
        val tobaccos = this.getSharedPreferences("mix tobaccos", MODE_PRIVATE).getStringSet(mixName, mutableSetOf())?.joinToString("\n")
        val tobaccosArray = tobaccos?.split("\n")?.toTypedArray()
        var meanStrength = 0.0
        var meanVapor = 0.0
        if (tobaccosArray != null) {
            val tobaccosTotal = tobaccosArray.count().toFloat()
            for (tobacco in tobaccosArray) {
                val specs = this.getSharedPreferences("tobaccos", MODE_PRIVATE).getString(tobacco, "0 0").toString().split(" ").toTypedArray()
                val strength = specs[0].toFloat()
                val vapor = specs[1].toFloat()
                meanStrength += strength / tobaccosTotal
                meanVapor += vapor / tobaccosTotal
            }
        }
        val textNumberStrength = findViewById<TextView>(R.id.textNumberStrength)
        var stopStrength = 4
        if (meanStrength.toString().length < 4) {
            stopStrength = meanStrength.toString().length
        }
        textNumberStrength.text = meanStrength.toString().substring(0, stopStrength)
        val textNumberVapor = findViewById<TextView>(R.id.textNumberVapor)
        var stopVapor = 4
        if (meanVapor.toString().length < 4) {
            stopVapor = meanVapor.toString().length
        }
        textNumberVapor.text = meanVapor.toString().substring(0, stopVapor)
        val tastes = this.getSharedPreferences("mix tastes", MODE_PRIVATE).getStringSet(mixName, mutableSetOf())?.joinToString("\n")
        val tastesArray = tastes?.split("\n")?.toTypedArray()
        var meanSweetness = 0.0
        var meanSourness = 0.0
        if (tastesArray != null) {
            val tastesTotal = tastesArray.count().toFloat()
            for (taste in tastesArray) {
                val specs = this.getSharedPreferences("tastes", MODE_PRIVATE).getString(taste, "0 0").toString().split(" ").toTypedArray()
                val sweetness = specs[0].toFloat()
                val sourness = specs[1].toFloat()
                meanSweetness += sweetness / tastesTotal
                meanSourness += sourness / tastesTotal
            }
        }
        val textNumberSweetness = findViewById<TextView>(R.id.textNumberSweetness)
        var stopSweetness = 4
        if (meanSweetness.toString().length < 4) {
            stopSweetness = meanSweetness.toString().length
        }
        textNumberSweetness.text = meanSweetness.toString().substring(0, stopSweetness)
        val textNumberSourness = findViewById<TextView>(R.id.textNumberSourness)
        var stopSourness = 4
        if (meanSourness.toString().length < 4) {
            stopSourness = meanSourness.toString().length
        }
        textNumberSourness.text = meanSourness.toString().substring(0, stopSourness)
    }
    fun onClickDelete(view: View) {
        val mixName = intent.getStringExtra("name").toString()
        this.getSharedPreferences("mix names", MODE_PRIVATE).edit().remove(mixName).apply()
        this.getSharedPreferences("mix tobaccos", MODE_PRIVATE).edit().remove(mixName).apply()
        this.getSharedPreferences("mix tastes", MODE_PRIVATE).edit().remove(mixName).apply()
        Toast.makeText(this, "mix $mixName deleted", Toast.LENGTH_SHORT).show()
    }
}