package com.example.hookah

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class AddTobacco : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_tobacco)
    }
    fun writeName(name: String) {
        val editor = this.getSharedPreferences("tobacco names", MODE_PRIVATE).edit()
        editor.putString(name, "")
        editor.apply()
    }
    fun writeTobacco(name: String, strength: String, vapor: String) {
        val editor = this.getSharedPreferences("tobaccos", MODE_PRIVATE).edit()
        editor.putString(name, "$strength $vapor")
        editor.apply()
    }
    fun onCLickAdd(view: View) {
        val strength = findViewById<TextView>(R.id.editNumberStrength).text.toString()
        val vapor = findViewById<TextView>(R.id.editNumberVapor).text.toString()
        val name = findViewById<EditText>(R.id.editTextName).text.toString()
        if (strength.isEmpty()) {
            Toast.makeText(this, "choose strength", Toast.LENGTH_SHORT).show()
        } else if (vapor.isEmpty()) {
            Toast.makeText(this, "choose vapor", Toast.LENGTH_SHORT).show()
        } else if (name.isEmpty()) {
            Toast.makeText(this, "name can not be empty", Toast.LENGTH_SHORT).show()
        } else if (this.getSharedPreferences("tobacco names", MODE_PRIVATE).contains(name)) {
            Toast.makeText(this, "tobacco $name already exists", Toast.LENGTH_SHORT).show()
        } else {
            writeName(name)
            writeTobacco(name, strength, vapor)
            Toast.makeText(this, "tobacco $name added", Toast.LENGTH_SHORT).show()
            super.onBackPressed()
        }
    }
    fun onClickStrength(view: View) {
        val items = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle("choose strength")
            setItems(items) { _, which ->
                val strength = findViewById<TextView>(R.id.editNumberStrength)
                strength.text = items[which]
            }
            show()
        }
    }
    fun onClickVapor(view: View) {
        val items = arrayOf("0", "1", "2", "3")
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle("choose vapor")
            setItems(items) { _, which ->
                val vapor = findViewById<TextView>(R.id.editNumberVapor)
                vapor.text = items[which]
            }
            show()
        }
    }
}