package com.ipvc.projetocm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Pagamento : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagamento)
        supportActionBar?.hide()
    }

    fun pagarCartaoMultibanco(view: View) {
        val intent = Intent(this,CartaoMultibanco::class.java).apply {  }
        startActivity(intent)
    }
    fun pagarMbWay(view: View) {
        val intent = Intent(this,MbWay::class.java).apply {  }
        startActivity(intent)
    }
}