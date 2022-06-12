package com.ipvc.projetocm

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ipvc.projetocm.api.ServiceBuilder

class PerfilUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_user)
        supportActionBar?.hide()

        val sharedPreference: SharedPreferences = getSharedPreferences("FILE_1", Context.MODE_PRIVATE)

        val idUser = sharedPreference.getString("PREF_ID", "");
        ServiceBuilder.instance.getIndividualUser(idUser?.toInt())
    }

    fun alterarPassword(view: View) {}
}