package com.ipvc.projetocm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

const val PARAM_HORA_ENTRADA = "hora_entrada";
const val PARAM_TEMPO_QUE_PENSA_FICAR = "tempo_desejado";
const val PARAM_CUSTO_TOTAL_BILHETE = "preco_total_bilhete";
const val PARAM_NOME_BILHETE = "nome_bilhete";

class Bilhete : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bilhete)
    }

    fun MandarDetalhesReserva(view: View) {
        val editText1 = findViewById<EditText>(R.id.etHoraDeEntrada)
        val editText2 = findViewById<EditText>(R.id.etTempoQuePensaFicar)
        val editText3 = findViewById<EditText>(R.id.etNomeUtilizador)

        val custoBilhete = 2
        val iva = 0.25

        if (editText1.text.toString().isEmpty() && editText2.text.toString().isEmpty()) {
            Toast.makeText(this, "Insira os dados do bilhete que lhe faltam preencher", Toast.LENGTH_SHORT).show()
        }else if (editText1.text.toString().isEmpty() && editText2.text.toString().isNotEmpty()){
            Toast.makeText(this, "Insira a sua hora de entrada no parque", Toast.LENGTH_SHORT).show()
        }else if (editText2.text.toString().isEmpty() && editText1.text.toString().isNotEmpty()){
            Toast.makeText(this, "Insira o tempo que estima ficar no parque", Toast.LENGTH_SHORT).show()
        }else{
            val editText2TempoEstimado = Integer.parseInt(editText2.text.toString())
            val totalBilhete = (custoBilhete + editText2TempoEstimado) * iva

            val intent = Intent(this, DetalhesBilhete::class.java).apply {
                putExtra(PARAM_HORA_ENTRADA, editText1.text.toString())
                putExtra(PARAM_TEMPO_QUE_PENSA_FICAR, editText2.text.toString())
                putExtra(PARAM_CUSTO_TOTAL_BILHETE, totalBilhete.toFloat().toString())
                putExtra(PARAM_NOME_BILHETE, editText3.text.toString())
            }

            startActivity(intent)
        }
    }
}