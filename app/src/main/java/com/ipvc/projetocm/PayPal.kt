package com.ipvc.projetocm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class PayPal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_pal)
    }

    fun ConfirmarPagPayPal(view: View) {
        val etEmailPayPal = findViewById<EditText>(R.id.etEmailPayPal)
        val etPassPayPal = findViewById<EditText>(R.id.etPassPayPal)

        if (etEmailPayPal.text.isEmpty() && etPassPayPal.text.isEmpty()){
            Toast.makeText(this, "Insira os dados do seu PayPal", Toast.LENGTH_SHORT).show()
        }else if(etEmailPayPal.text.isNotEmpty() && etPassPayPal.text.isEmpty()){
            Toast.makeText(this, "Insira a palavra-passe do seu PayPal", Toast.LENGTH_SHORT).show()
        }else if(etEmailPayPal.text.isEmpty() && etPassPayPal.text.isNotEmpty()){
            Toast.makeText(this, "Insira o email do seu PayPal", Toast.LENGTH_SHORT).show()
        }
    }
}