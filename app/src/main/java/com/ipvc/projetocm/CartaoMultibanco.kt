package com.ipvc.projetocm

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ipvc.projetocm.api.DefaultResponse
import com.ipvc.projetocm.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CartaoMultibanco : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cartao_multibanco)
        supportActionBar?.hide()

        val sharedPreference: SharedPreferences = getSharedPreferences("FILE_1", Context.MODE_PRIVATE)
        val idBilhete = sharedPreference.getString("PREF_BILHETE_ID", "");

        val button = findViewById<Button>(R.id.buttonCartaoMultibanco)
        button.setOnClickListener {
            val replyIntent = Intent()

            val etNomeDoCartao = findViewById<EditText>(R.id.etNomeDoCartao)
            val etNumeroDoCartao = findViewById<EditText>(R.id.etNumeroDoCartao)
            val etValidadeDoCartao = findViewById<EditText>(R.id.etValidadeDoCartao)
            val etCVV2CVC2 = findViewById<EditText>(R.id.etCVV2CVC2)

            if (etNomeDoCartao.text.isEmpty() && etNumeroDoCartao.text.isEmpty() && etValidadeDoCartao.text.isEmpty() && etCVV2CVC2.text.isEmpty()){
                Toast.makeText(this, "Insira os dados do cartão multibanco", Toast.LENGTH_SHORT).show()
            }else if (etNomeDoCartao.text.isNotEmpty() && etNumeroDoCartao.text.isEmpty() && etValidadeDoCartao.text.isEmpty() && etCVV2CVC2.text.isEmpty()){
                Toast.makeText(this, "Insira os dados do cartão multibanco que lhe faltam preencher", Toast.LENGTH_SHORT).show()
            }else if (etNomeDoCartao.text.isNotEmpty() && etNumeroDoCartao.text.isNotEmpty() && etValidadeDoCartao.text.isEmpty() && etCVV2CVC2.text.isEmpty()){
                Toast.makeText(this, "Insira os dados do cartão multibanco que lhe faltam preencher", Toast.LENGTH_SHORT).show()
            }else if (etNomeDoCartao.text.isNotEmpty() && etNumeroDoCartao.text.isNotEmpty() && etValidadeDoCartao.text.isNotEmpty() && etCVV2CVC2.text.isEmpty()){
                Toast.makeText(this, "Insira o CVV2/CVC2 do cartão multibanco", Toast.LENGTH_SHORT).show()
            }else if (etNomeDoCartao.text.isNotEmpty() && etNumeroDoCartao.text.isNotEmpty() && etValidadeDoCartao.text.isEmpty() && etCVV2CVC2.text.isNotEmpty()){
                Toast.makeText(this, "Insira a validade do cartão do cartão multibanco", Toast.LENGTH_SHORT).show()
            }else if (etNomeDoCartao.text.isNotEmpty() && etNumeroDoCartao.text.isEmpty() && etValidadeDoCartao.text.isNotEmpty() && etCVV2CVC2.text.isNotEmpty()){
                Toast.makeText(this, "Insira o número do cartão do cartão multibanco", Toast.LENGTH_SHORT).show()
            }else if (etNomeDoCartao.text.isEmpty() && etNumeroDoCartao.text.isNotEmpty() && etValidadeDoCartao.text.isNotEmpty() && etCVV2CVC2.text.isNotEmpty()){
                Toast.makeText(this, "Insira o nome do utilizador do cartão do cartão multibanco", Toast.LENGTH_SHORT).show()
            }
            else {
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
            val intent = Intent(this, DetalhesBilhete::class.java).apply{
                putExtra(PARAM_KEY2, sharedPreference.getString("PREF_KEY", ""))
            }
            startActivity(intent)
        }
    }
}