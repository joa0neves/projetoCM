package com.ipvc.projetocm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MbWay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mb_way)
    }

    fun ConfirmarPagMbWay(view: View) {
        val etcontactoDoMbWayAPagar = findViewById<EditText>(R.id.etcontactoDoMbWayAPagar)

        if (etcontactoDoMbWayAPagar.text.isEmpty()){
            Toast.makeText(this, "Insira o número do contacto do MwBay que vai pagar", Toast.LENGTH_SHORT).show()
        }
    }
}