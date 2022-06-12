package com.ipvc.projetocm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ipvc.projetocm.api.EndPoints
import com.ipvc.projetocm.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val buttonClickLog = findViewById<Button>(R.id.login)
        buttonClickLog.setOnClickListener {
            val intent = Intent(this, Bilhete::class.java)
            startActivity(intent)
        }

        val buttonClickReg = findViewById<Button>(R.id.registar)
        buttonClickReg.setOnClickListener {
            val intent = Intent(this, Registo::class.java)
            startActivity(intent)
        }

    }
}