package com.ipvc.projetocm

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ipvc.projetocm.api.Bilhetes
import com.ipvc.projetocm.adapter.BilheteAdapter
import com.ipvc.projetocm.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoricoReservas : AppCompatActivity() {

    private lateinit var id :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico_reservas)
        supportActionBar?.hide()

        val sharedPreference: SharedPreferences = getSharedPreferences("FILE_1", Context.MODE_PRIVATE)
        id = sharedPreference.getString("PREF_ID", "")!!
        
        ServiceBuilder.instance.getBilhetesAntigos(id!!.toInt())
            .enqueue(object: Callback<List<Bilhetes>> {
                override fun onResponse(call: Call<List<Bilhetes>>, response: Response<List<Bilhetes>>) {
                    if (response.isSuccessful) {
                        val text: TextView = findViewById(R.id.semBilheteView)
                        text.isVisible = false

                        val recView = findViewById<RecyclerView>(R.id.recyclerview)
                        recView.layoutManager = LinearLayoutManager(this@HistoricoReservas)
                        recView.adapter = BilheteAdapter(response.body()!!)

                    }
                }
                override fun onFailure(call: Call<List<Bilhetes>>, t: Throwable) {
                    //Log.d("response", t.message.toString())
                    //Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }
            })

        //abrir NewPersonActivity quando tocar no botão
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@HistoricoReservas, Bilhete::class.java)
            startActivity(intent)
        }
    }

    fun goToPerfil(view: View) {
        val intent = Intent(this, PerfilUser::class.java)
        startActivity(intent)
    }
}