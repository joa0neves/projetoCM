package com.ipvc.projetocm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class CartaoMultibanco : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cartao_multibanco)
    }

    fun ConfirmarPagCartaoMult(view: View) {
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
    }
}