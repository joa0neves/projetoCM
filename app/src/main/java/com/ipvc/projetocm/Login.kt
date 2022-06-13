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
import android.widget.TextView
import android.widget.Toast
import com.ipvc.projetocm.Model.Id
import com.ipvc.projetocm.api.DefaultResponse
import com.ipvc.projetocm.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {

    private lateinit var editEmailView: EditText
    private lateinit var editPasswordView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        val sharedPreference: SharedPreferences = getSharedPreferences("FILE_1", Context.MODE_PRIVATE)

        editEmailView = findViewById(R.id.etEmail);
        editPasswordView = findViewById(R.id.etPassword);

        val button = findViewById<Button>(R.id.btPass)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editEmailView.text) && TextUtils.isEmpty(editPasswordView.text)) {
                //setResult(Activity.RESULT_CANCELED, replyIntent)
                Toast.makeText(applicationContext, "Login failed", Toast.LENGTH_LONG).show()
            } else {
                val email = editEmailView.text.toString()
                val password = editPasswordView.text.toString()

                ServiceBuilder.instance.postLogin(email, password)
                    .enqueue(object: Callback<Id> {
                        override fun onFailure(call: Call<Id>, t: Throwable) {
                            Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                            Toast.makeText(applicationContext, "Login failed", Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(call: Call<Id>, response: Response<Id>) {
                            if(response.body()!!.id!="-1"){
                                sharedPreference.edit().putString("PREF_ID",response.body()!!.id).apply()
                                goTo()
                            }
                        }
                    })
            }
        }
    }

    fun goTo(){
        val intent = Intent(this, Bilhete::class.java)
        startActivity(intent)
    }

    fun onClickRegisto(view: View) {
        val intent = Intent(this, Registo::class.java)
        startActivity(intent)
    }

    fun onClickPassword(view: View) {
        editPasswordView = findViewById(R.id.etPassword);
        if(editPasswordView.text.toString()=="Password"){
            editPasswordView.setText("")
        }
    }
    fun onClickEmail(view: View) {
        editEmailView = findViewById(R.id.etEmail);
        if(editEmailView.text.toString()=="Email"){
            editEmailView.setText("")
        }
    }
}