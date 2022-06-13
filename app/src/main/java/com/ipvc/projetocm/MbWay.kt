package com.ipvc.projetocm

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ipvc.projetocm.api.DefaultResponse
import com.ipvc.projetocm.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MbWay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mb_way)

        val sharedPreference: SharedPreferences = getSharedPreferences("FILE_1", Context.MODE_PRIVATE)
        val idBilhete = sharedPreference.getString("PREF_BILHETE_ID", "");

        val button = findViewById<Button>(R.id.buttonCartaoMultibanco)
        button.setOnClickListener {
            val replyIntent = Intent()

            val etcontactoDoMbWayAPagar = findViewById<EditText>(R.id.etcontactoDoMbWayAPagar)

            if (etcontactoDoMbWayAPagar.text.isEmpty()){
                Toast.makeText(this, "Insira o número do contacto do MwBay que vai pagar", Toast.LENGTH_SHORT).show()
            }
            else {
                //ServiceBuilder.instance.postBilhete(data, tempo, valor, idUser?.toInt())
                ServiceBuilder.instance.updatePagamento(idBilhete?.toInt())
                    .enqueue(object: Callback<DefaultResponse> {
                        override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                            Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                            Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
                        }
                    })
            }
            finish()
        }
    }

    /*
    fun ConfirmarPagMbWay(view: View) {
        val etcontactoDoMbWayAPagar = findViewById<EditText>(R.id.etcontactoDoMbWayAPagar)

        if (etcontactoDoMbWayAPagar.text.isEmpty()){
            Toast.makeText(this, "Insira o número do contacto do MwBay que vai pagar", Toast.LENGTH_SHORT).show()
        }
    }*/
}