package com.ipvc.projetocm

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.ipvc.projetocm.Model.Id
import com.ipvc.projetocm.api.Pagamentos
import com.ipvc.projetocm.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.WriteAbortedException

class DetalhesBilhete : AppCompatActivity() {

    private lateinit var qrImage:ImageView
    private lateinit var key:String

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreference: SharedPreferences = getSharedPreferences("FILE_1", Context.MODE_PRIVATE)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_bilhete)
        supportActionBar?.hide()

        val parametroHoraEntrada = intent.getStringExtra(PARAM_HORA_ENTRADA)
        val textView = findViewById<TextView>(R.id.tvReservaHoraInicio)
        textView.setText(parametroHoraEntrada)

        val parametroTempoQuePensaFicar = intent.getStringExtra(PARAM_TEMPO_QUE_PENSA_FICAR)
        val textView2 = findViewById<TextView>(R.id.tvReservaTempoDesejado)
        textView2.setText(parametroTempoQuePensaFicar)

        val parametroTotalBilhete = intent.getStringExtra(PARAM_CUSTO_TOTAL_BILHETE)
        val tvReservaTotalaPagar = findViewById<TextView>(R.id.tvReservaTotalaPagar)
        tvReservaTotalaPagar.setText(parametroTotalBilhete)

        key = intent.getStringExtra(PARAM_KEY).toString()

        sharedPreference.edit().putString("PREF_KEY", key).apply()

        qrImage=findViewById(R.id.imageqr)

        val writer = QRCodeWriter()

        try {
            val bitMatrix= writer.encode(key, BarcodeFormat.QR_CODE,512,512)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bmp = Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565)
            for (x in 0 until height){
                for (y in 0 until height){
                    bmp.setPixel(x,y, if (bitMatrix[x,y]) Color.BLACK else Color.WHITE)
                }
            }
            qrImage.setImageBitmap(bmp)
        }catch (e: WriteAbortedException){
            e.printStackTrace()
        }

        val buttonHistorico = findViewById<ImageView>(R.id.imageView4)
        buttonHistorico.isVisible = false

        val button = findViewById<Button>(R.id.btReservaPagar)
        button.setOnClickListener {
            val intent = Intent(this, Pagamento::class.java).apply {}
            startActivity(intent)
        }

        val idUser = sharedPreference.getString("PREF_ID", "");

        ServiceBuilder.instance.getIdBilhete(idUser?.toInt())
        .enqueue(object: Callback<Id> {
            override fun onFailure(call: Call<Id>, t: Throwable) {

            }
            override fun onResponse(call: Call<Id>, response: Response<Id>) {
                if(response.body()!!.id!="-1"){
                    sharedPreference.edit().putString("PREF_BILHETE_ID",response.body()!!.id).apply()
                }
            }
        })

        ServiceBuilder.instance.getEstadoPagamento(key)
            .enqueue(object: Callback<Pagamentos> {
                override fun onFailure(call: Call<Pagamentos>, t: Throwable) {
                    //Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }
                override fun onResponse(call: Call<Pagamentos>, response: Response<Pagamentos>) {
                    if(response.body()!!.estaPago != 0){
                        button.isVisible = false
                    }
                }
            })

    }

    override fun onResume() {
        super.onResume()

        val textView = findViewById<TextView>(R.id.tvReservaHoraInicio)
        val textView2 = findViewById<TextView>(R.id.tvReservaTempoDesejado)
        val tvReservaTotalaPagar = findViewById<TextView>(R.id.tvReservaTotalaPagar)

        val key = intent.getStringExtra(PARAM_KEY2);

        val buttonHistorico = findViewById<ImageView>(R.id.imageView4)

        val button = findViewById<Button>(R.id.btReservaPagar)
        ServiceBuilder.instance.getEstadoPagamento(key)
            .enqueue(object: Callback<Pagamentos> {
                override fun onFailure(call: Call<Pagamentos>, t: Throwable) {
                    //Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }
                override fun onResponse(call: Call<Pagamentos>, response: Response<Pagamentos>) {
                    if(response.body()!!.estaPago!=0){
                        button.isVisible = false
                        buttonHistorico.isVisible = true
                    }
                    textView.setText(response.body()!!.data)
                    textView2.setText(response.body()!!.tempo)
                    tvReservaTotalaPagar.setText(response.body()!!.valor)
                }
            })
    }

    fun historicoReservas(view: View) {
        val intent = Intent(this, HistoricoReservas::class.java)
        startActivity(intent)
    }
}