package com.ipvc.projetocm

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ipvc.projetocm.api.DefaultResponse
import com.ipvc.projetocm.api.EndPoints
import com.ipvc.projetocm.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Registo : AppCompatActivity() {

    private lateinit var editNomeView: EditText
    private lateinit var editEmailView: EditText
    private lateinit var editPasswordView: EditText
    private lateinit var editContactoView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registo)
        supportActionBar?.hide()

        editNomeView = findViewById(R.id.etNome);
        editEmailView = findViewById(R.id.etEmail);
        editPasswordView = findViewById(R.id.etPassword);
        editContactoView = findViewById(R.id.etContacto);

        val button = findViewById<Button>(R.id.btRegisto)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editNomeView.text) && TextUtils.isEmpty(editEmailView.text) && TextUtils.isEmpty(editPasswordView.text) && TextUtils.isEmpty(editContactoView.text)) {
                //setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val nome = editNomeView.text.toString()
                val email = editEmailView.text.toString()
                val password = editPasswordView.text.toString()
                val contacto = editContactoView.text.toString()

                ServiceBuilder.instance.postUser(nome, email, password, contacto)
                    .enqueue(object: Callback<DefaultResponse>{
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

    fun onClickLogin(view: View) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}

