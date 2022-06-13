package com.ipvc.projetocm

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.ipvc.projetocm.api.Bilhete
import com.ipvc.projetocm.Model.Utilizador
import com.ipvc.projetocm.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoricoReservas : AppCompatActivity() {

    private lateinit var textDataView: TextView
    private lateinit var textTempoView: TextView
    private lateinit var textValorView: TextView

    private lateinit var id :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico_reservas)


        val sharedPreference: SharedPreferences = getSharedPreferences("FILE_1", Context.MODE_PRIVATE)
        id= sharedPreference.getString("PREF_ID", "")!!

        ServiceBuilder.instance.getBilhetesAntigos(id!!.toInt())
            .enqueue(object: Callback<List<Bilhete>> {
                override fun onResponse(call: Call<List<Bilhete>>, response: Response<List<Bilhete>>) {
                    if (response.isSuccessful) {
                        //textDataView.setText(response.body()!!.data)
                        //textTempoView.setText(response.body()!!.tempo)
                        //textValorView.setText(response.body()!!.valor)
                    }
                }
                override fun onFailure(call: Call<List<Bilhete>>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }
            })
    }
}