package com.pam.internalstorage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.io.File

class MainActivity : AppCompatActivity() {

    private val fileName = "notes.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editNote = findViewById<EditText>(R.id.editTextNote)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnLoad = findViewById<Button>(R.id.btnLoad)
        val txtResult = findViewById<TextView>(R.id.txtResult)

        btnSave.setOnClickListener {
            val noteText = editNote.text.toString()

            openFileOutput(fileName, MODE_PRIVATE).use { output ->
                output.write(noteText.toByteArray())
            }

            txtResult.text = "Catatan berhasil disimpan!"
        }

        btnLoad.setOnClickListener {
            val file = File(filesDir, fileName)

            if (file.exists()) {
                val savedText = file.readText()
                txtResult.text = "Isi File:\n\n$savedText"
            } else {
                txtResult.text = "File belum ada."
            }
        }

//        btnLoad.setOnClickListener {
//            try {
//                val savedText = openFileInput(fileName).bufferedReader().use { it.readText() }
//
//                txtResult.text = "Isi File:\n\n$savedText"
//
//            } catch (e: Exception) {
//                txtResult.text = "File belum ada atau tidak bisa dibaca."
//            }
//        }
    }
}