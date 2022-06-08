package com.ipvc.projetocm

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText

class Registo : AppCompatActivity() {

    private lateinit var editNomeView: EditText
    private lateinit var editEmailView: EditText
    private lateinit var editPasswordView: EditText
    private lateinit var editContactoView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registo)

        editNomeView = findViewById(R.id.etNome);
        editEmailView = findViewById(R.id.etEmail);
        editPasswordView = findViewById(R.id.etPassword);
        editContactoView = findViewById(R.id.etContacto);

        val button = findViewById<Button>(R.id.btRegisto)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editNomeView.text) && TextUtils.isEmpty(editEmailView.text) && TextUtils.isEmpty(editPasswordView.text) && TextUtils.isEmpty(editContactoView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                /*
                val nome = editNomeView.text.toString()
                val email = editEmailView.text.toString()
                val password = editPasswordView.text.toString()
                val contacto = editContactoView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, nome)
                replyIntent.putExtra(EXTRA_REPLY, email)
                replyIntent.putExtra(EXTRA_REPLY, password)
                replyIntent.putExtra(EXTRA_REPLY, contacto)
                setResult(Activity.RESULT_OK, replyIntent)*/
                //enviar para a atividade MainActivity e concluir registo na bd
            }
            finish()
        }


    }



    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }

    fun onClickLogin(view: View) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}