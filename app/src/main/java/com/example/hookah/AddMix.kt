package com.example.hookah

import android.content.Intent
import android.content.SearchRecentSuggestionsProvider
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import java.util.*

class AddMix : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_mix)
    }
    fun writeTobaccos(mixName: String, tobaccos: Set<String>) {
        val editor = this.getSharedPreferences("mix tobaccos", MODE_PRIVATE).edit()
        editor.putStringSet(mixName, tobaccos)
        editor.apply()
    }
    fun writeTastes(mixName: String, tastes: Set<String>) {
        val editor = this.getSharedPreferences("mix tastes", MODE_PRIVATE).edit()
        editor.putStringSet(mixName, tastes)
        editor.apply()
    }
    fun onClickAddTobaccos(view: View) {
        val mixName = intent.getStringExtra("mixName")
        val names = (this.getSharedPreferences("tobacco names", MODE_PRIVATE).all as Map<*, *>).toString()
        val namesArray = names.substring(1, names.length - 1).replace("=".toRegex(), "").replace(", ".toRegex(), "\n").split("\n").toTypedArray()
        val selectedArray = arrayListOf<Int>()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("choose tobaccos to add")
        builder.setMultiChoiceItems(namesArray, null) { _, which, isSelected ->
            if (isSelected) {
                selectedArray.add(which)
            } else if (selectedArray.contains(which)) {
                selectedArray.remove(which)
            }
        }
        builder.setPositiveButton("ok") { _, _ ->
            val selectedNames = mutableSetOf<String>()
            for (item in selectedArray) {
                selectedNames.add(namesArray[item])
            }
            if (mixName != null) {
                writeTobaccos(mixName, selectedNames)
            }
        }
        builder.show()
    }
    fun onClickAddTastes(view: View) {
        val mixName = intent.getStringExtra("mixName")
        val names = (this.getSharedPreferences("taste names", MODE_PRIVATE).all as Map<*, *>).toString()
        val namesArray = names.substring(1, names.length - 1).replace("=".toRegex(), "").replace(", ".toRegex(), "\n").split("\n").toTypedArray()
        val selectedArray = arrayListOf<Int>()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("choose tastes to add")
        builder.setMultiChoiceItems(namesArray, null) { _, which, isSelected ->
            if (isSelected) {
                selectedArray.add(which)
            } else if (selectedArray.contains(which)) {
                selectedArray.remove(which)
            }
        }
        builder.setPositiveButton("ok") { _, _ ->
            val selectedNames = mutableSetOf<String>()
            for (item in selectedArray) {
                selectedNames.add(namesArray[item])
            }
            if (mixName != null) {
                writeTastes(mixName, selectedNames)
            }
        }
        builder.show()
    }
    fun onClickAddMix(view: View) {
        val mixName = intent.getStringExtra("mixName")
        val intent = Intent(this, MixFinal::class.java)
        intent.putExtra("mixName", mixName)
        startActivity(intent)
    }
}