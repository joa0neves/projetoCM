package com.ipvc.projetocm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ipvc.projetocm.api.ServiceBuilder

class PerfilUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_user)
        supportActionBar?.hide()
        //valor da sharedpreference
        //ServiceBuilder.instance.getIndividualUser()
    }

    fun alterarPassword(view: View) {}
}