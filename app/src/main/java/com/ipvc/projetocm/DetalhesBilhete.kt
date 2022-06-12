package com.ipvc.projetocm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetalhesBilhete : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_bilhete)

        val parametroHoraEntrada = intent.getStringExtra(PARAM_HORA_ENTRADA)
        val textView = findViewById<TextView>(R.id.tvReservaHoraInicio)
        textView.setText(parametroHoraEntrada)

        val parametroTempoQuePensaFicar = intent.getStringExtra(PARAM_TEMPO_QUE_PENSA_FICAR)
        val textView2 = findViewById<TextView>(R.id.tvReservaTempoDesejado)
        textView2.setText(parametroTempoQuePensaFicar)

        val parametroTotalBilhete = intent.getStringExtra(PARAM_CUSTO_TOTAL_BILHETE)
        val tvReservaTotalaPagar = findViewById<TextView>(R.id.tvReservaTotalaPagar)
        tvReservaTotalaPagar.setText(parametroTotalBilhete)

        val parametroNomeBilhete = intent.getStringExtra(PARAM_NOME_BILHETE)
        val tvNomeBilhete = findViewById<TextView>(R.id.tvReservaNome)
        tvNomeBilhete.setText(parametroNomeBilhete)
    }
}