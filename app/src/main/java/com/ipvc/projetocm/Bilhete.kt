package com.ipvc.projetocm

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ipvc.projetocm.api.DefaultResponse
import com.ipvc.projetocm.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

const val PARAM_HORA_ENTRADA = "hora_entrada";
const val PARAM_TEMPO_QUE_PENSA_FICAR = "tempo_desejado";
const val PARAM_CUSTO_TOTAL_BILHETE = "preco_total_bilhete";

class Bilhete : AppCompatActivity() {

    private lateinit var editTempoView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bilhete)

        val sharedPreference: SharedPreferences = getSharedPreferences("FILE_1", Context.MODE_PRIVATE)

        editTempoView = findViewById(R.id.etTempoQuePensaFicar);

        val button = findViewById<Button>(R.id.btConfirmaBilhete)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editTempoView.text)) {
                //setResult(Activity.RESULT_CANCELED, replyIntent)
                Toast.makeText(this, "Insira o tempo que estima ficar no parque", Toast.LENGTH_SHORT).show()
            } else {
                val dataMal: Date = Calendar.getInstance().time
                val data = dataMal.toString()
                val tempo = Integer.parseInt(editTempoView.text.toString());
                val valor = (2 + tempo) * 0.25
                val idUser = sharedPreference.getString("PREF_ID", "");

                ServiceBuilder.instance.postBilhete(data, tempo, valor, idUser?.toInt())
                    .enqueue(object: Callback<DefaultResponse> {
                        override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                            Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                            Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
                        }
                    })

                val intent = Intent(this, DetalhesBilhete::class.java).apply {
                    putExtra(PARAM_HORA_ENTRADA, data)
                    putExtra(PARAM_TEMPO_QUE_PENSA_FICAR, tempo.toString())
                    putExtra(PARAM_CUSTO_TOTAL_BILHETE, valor.toString())
                }
                startActivity(intent)
            }
        }
    }

    fun goToPerfil(view: View) {
        val intent = Intent(this, HistoricoReservas::class.java)
        startActivity(intent)
    }
}