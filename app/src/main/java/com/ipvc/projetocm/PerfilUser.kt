package com.ipvc.projetocm

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.ipvc.projetocm.Model.Id
import com.ipvc.projetocm.Model.Utilizador
import com.ipvc.projetocm.api.DefaultResponse
import com.ipvc.projetocm.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PerfilUser : AppCompatActivity() {

    private lateinit var editNomeView: EditText
    private lateinit var editEmailView: EditText
    private lateinit var editContactoView: EditText
    private lateinit var textNomeView: TextView
    private lateinit var id :String

    private lateinit var nomeInicial:String
    private lateinit var emailInicial:String
    private lateinit var contactoInicial:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_user)
        supportActionBar?.hide()

        nomeInicial=""
        emailInicial=""
        contactoInicial=""

        textNomeView = findViewById(R.id.tvNome)
        editNomeView = findViewById(R.id.etNome)
        editEmailView = findViewById(R.id.etEmail)
        editContactoView = findViewById(R.id.etContacto)


        val sharedPreference: SharedPreferences = getSharedPreferences("FILE_1", Context.MODE_PRIVATE)

        id= sharedPreference.getString("PREF_ID", "")!!
        ServiceBuilder.instance.getIndividualUser(id!!.toInt())
            .enqueue(object: Callback<Utilizador> {
                override fun onFailure(call: Call<Utilizador>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    Log.d("teste", t.message.toString())
                }

                override fun onResponse(call: Call<Utilizador>, response: Response<Utilizador>) {
                    textNomeView.setText(response.body()!!.nome)
                    nomeInicial=response.body()!!.nome
                    editNomeView.setText(response.body()!!.nome)
                    editEmailView.setText(response.body()!!.email)
                    emailInicial = response.body()!!.email
                    editContactoView.setText(response.body()!!.contacto)
                    contactoInicial=response.body()!!.contacto
                }
            })
    }

    fun alterarPassword(view: View) {}

    fun atualizar(view: View){
        if(editNomeView.text.toString()!=nomeInicial || editEmailView.text.toString()!=emailInicial || editContactoView.text.toString()!=contactoInicial){
            ServiceBuilder.instance.updateUser(id!!.toInt(),editNomeView.text.toString(),editEmailView.text.toString(),editContactoView.text.toString())
                .enqueue(object: Callback<DefaultResponse> {
                    override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                        Log.d("teste", t.message.toString())
                    }

                    override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                        Toast.makeText(applicationContext, "Atualizacao com sucesso", Toast.LENGTH_LONG).show()
                    }
                })
            }else{
            Toast.makeText(applicationContext, "É necessario fazer alteracoes", Toast.LENGTH_LONG).show()
        }
    }
}