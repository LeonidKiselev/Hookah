package com.example.hookah

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class AddTaste : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_taste)
    }
    fun writeName(name: String) {
        val editor = this.getSharedPreferences("taste names", MODE_PRIVATE).edit()
        editor.putString(name, "")
        editor.apply()
    }
    fun writeTaste(name: String, sweetness: String, sourness: String) {
        val editor = this.getSharedPreferences("tastes", MODE_PRIVATE).edit()
        editor.putString(name, "$sweetness $sourness")
        editor.apply()
    }
    fun onCLickAdd(view: View) {
        val sweetness = findViewById<TextView>(R.id.editNumberSweetness).text.toString()
        val sourness = findViewById<TextView>(R.id.editNumberSourness).text.toString()
        val name = findViewById<EditText>(R.id.editTextName).text.toString()
        if (sweetness.isEmpty()) {
            Toast.makeText(this, "choose sweetness", Toast.LENGTH_SHORT).show()
        } else if (sourness.isEmpty()) {
            Toast.makeText(this, "choose sourness", Toast.LENGTH_SHORT).show()
        } else if (name.isEmpty()) {
            Toast.makeText(this, "name can not be empty", Toast.LENGTH_SHORT).show()
        } else if (this.getSharedPreferences("taste names", MODE_PRIVATE).contains(name)) {
            Toast.makeText(this, "taste $name already exists", Toast.LENGTH_SHORT).show()
        } else {
            writeName(name)
            writeTaste(name, sweetness, sourness)
            Toast.makeText(this, "taste $name added", Toast.LENGTH_SHORT).show()
            super.onBackPressed()
        }
    }
    fun onClickSweetness(view: View) {
        val items = arrayOf("0", "1", "2", "3", "4", "5")
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle("choose sweetness")
            setItems(items) { _, which ->
                val sweetness = findViewById<TextView>(R.id.editNumberSweetness)
                sweetness.text = items[which]
            }
            show()
        }
    }
    fun onClickSourness(view: View) {
        val items = arrayOf("0", "1", "2", "3", "4", "5")
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle("choose sourness")
            setItems(items) { _, which ->
                val sourness = findViewById<TextView>(R.id.editNumberSourness)
                sourness.text = items[which]
            }
            show()
        }
    }
}