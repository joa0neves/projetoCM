package com.ipvc.projetocm

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.ipvc.projetocm.api.DefaultResponse
import com.ipvc.projetocm.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewParque : AppCompatActivity() {

    private lateinit var estrelasView: RatingBar
    private lateinit var commentView: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_parque)
        supportActionBar?.hide()

        val sharedPreference: SharedPreferences = getSharedPreferences("FILE_1", Context.MODE_PRIVATE)

        val button = findViewById<Button>(R.id.btSubmit)
        button.setOnClickListener {
            //val replyIntent = Intent()
            estrelasView = findViewById(R.id.estrelas);
            commentView = findViewById(R.id.commentText);

            if (estrelasView.rating.toDouble() == 0.0){
                Toast.makeText(applicationContext, "Preencha as estrelas", Toast.LENGTH_LONG).show()
                //setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val estrelas = estrelasView.rating.toDouble()
                var descr: String? = null
                if(!(TextUtils.isEmpty(commentView.text))){
                    descr = commentView.toString()
                }

                val idUser = sharedPreference.getString("PREF_ID", "");

                ServiceBuilder.instance.postReview(estrelas, descr, idUser?.toInt())
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

}