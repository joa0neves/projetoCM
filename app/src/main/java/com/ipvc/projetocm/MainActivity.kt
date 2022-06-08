package com.ipvc.projetocm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonClickLog = findViewById<Button>(R.id.login)
        buttonClickLog.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        val buttonClickReg = findViewById<Button>(R.id.registar)
        buttonClickReg.setOnClickListener {
            val intent = Intent(this, Registo::class.java)
            startActivity(intent)
        }
    }
}