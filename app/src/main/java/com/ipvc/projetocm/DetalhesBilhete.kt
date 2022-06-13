package com.ipvc.projetocm

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.ipvc.projetocm.Model.Id
import com.ipvc.projetocm.api.DefaultResponse
import com.ipvc.projetocm.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetalhesBilhete : AppCompatActivity() {

    //val sharedPreference: SharedPreferences = getSharedPreferences("FILE_1", Context.MODE_PRIVATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_bilhete)
        supportActionBar?.hide()

        val parametroHoraEntrada = intent.getStringExtra(PARAM_HORA_ENTRADA)
        val textView = findViewById<TextView>(R.id.tvReservaHoraInicio)
        textView.setText(parametroHoraEntrada)

        val parametroTempoQuePensaFicar = intent.getStringExtra(PARAM_TEMPO_QUE_PENSA_FICAR)
        val textView2 = findViewById<TextView>(R.id.tvReservaTempoDesejado)
        textView2.setText(parametroTempoQuePensaFicar)

        val parametroTotalBilhete = intent.getStringExtra(PARAM_CUSTO_TOTAL_BILHETE)
        val tvReservaTotalaPagar = findViewById<TextView>(R.id.tvReservaTotalaPagar)
        tvReservaTotalaPagar.setText(parametroTotalBilhete)
        /*
        val idUser = sharedPreference.getString("PREF_ID", "");

        ServiceBuilder.instance.getIdBilhete(idUser?.toInt())
        .enqueue(object: Callback<Id> {
            override fun onFailure(call: Call<Id>, t: Throwable) {

            }
            override fun onResponse(call: Call<Id>, response: Response<Id>) {
                if(response.body()!!.id!="-1"){
                    sharedPreference.edit().putString("PREF_BILHETE_ID",response.body()!!.id).apply()
                }
            }
        })
        */
        //val parametroNomeBilhete = intent.getStringExtra(PARAM_NOME_BILHETE)
        //val tvNomeBilhete = findViewById<TextView>(R.id.tvReservaNome)
        //tvNomeBilhete.setText(parametroNomeBilhete)
    }
}