package com.ipvc.projetocm

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ipvc.projetocm.api.DefaultResponse
import com.ipvc.projetocm.api.ServiceBuilder
import com.ipvc.projetocm.notifcacoes.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

const val PARAM_HORA_ENTRADA = "hora_entrada";
const val PARAM_TEMPO_QUE_PENSA_FICAR = "tempo_desejado";
const val PARAM_CUSTO_TOTAL_BILHETE = "preco_total_bilhete";
const val PARAM_KEY = "key";

class Bilhete : AppCompatActivity() {

    private lateinit var editTempoView: EditText

    private lateinit var key:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bilhete)
        supportActionBar?.hide()

        createNotificationChannel()

        val sharedPreference: SharedPreferences = getSharedPreferences("FILE_1", Context.MODE_PRIVATE)

        editTempoView = findViewById(R.id.etTempoQuePensaFicar);

        val key = generateKey(20)

        val button = findViewById<Button>(R.id.btConfirmaBilhete)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editTempoView.text)) {
                //setResult(Activity.RESULT_CANCELED, replyIntent)
                Toast.makeText(this, "Insira o tempo que estima ficar no parque", Toast.LENGTH_SHORT).show()
            } else {
                val dataMal: Date = Calendar.getInstance().time
                val data = dataMal.toString()
                val tempo = Integer.parseInt(editTempoView.text.toString());
                val valor = (2 + tempo) * 0.25
                val idUser = sharedPreference.getString("PREF_ID", "");

                ServiceBuilder.instance.postBilhete(data, tempo, valor, idUser?.toInt(), key)
                    .enqueue(object: Callback<DefaultResponse> {
                        override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                            Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                            Toast.makeText(applicationContext, "Bilhete Criado", Toast.LENGTH_LONG).show()
                            scheduleNotification()
                        }
                    })

                val intent = Intent(this, DetalhesBilhete::class.java).apply {
                    putExtra(PARAM_HORA_ENTRADA, data)
                    putExtra(PARAM_TEMPO_QUE_PENSA_FICAR, tempo.toString())
                    putExtra(PARAM_CUSTO_TOTAL_BILHETE, valor.toString())
                    putExtra(PARAM_KEY, key)
                }
                startActivity(intent)
            }
        }
    }

    fun generateKey(length: Int) : String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[]{}/?:;|><"
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }

    private fun createNotificationChannel()
    {
        val name = "Notif Channel"
        val desc = "A Description of the Channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(channelID, name, importance)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        channel.description = desc
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
        Log.d("teste","create")
    }

    private fun scheduleNotification()
    {
        val intent = Intent(applicationContext, Notificacao::class.java)
        val title = "AVISO"
        val message = "A sua reserva ACABOU! Tem 15 minutos para remover o seu veiculo"
        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)

        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        Log.d("teste","pending")

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                time,
                pendingIntent
            )
        }
        showAlert(time, title, message)
        Log.d("teste","show")
    }

    private fun showAlert(time: Long, title: String, message: String)
    {
        val date = Date(time)
        val dateFormat = android.text.format.DateFormat.getLongDateFormat(applicationContext)
        val timeFormat = android.text.format.DateFormat.getTimeFormat(applicationContext)

        AlertDialog.Builder(this)
            .setTitle("Notification Scheduled")
            .setMessage(
                "Title: " + title +
                        "\nMessage: " + message +
                        "\nAt: " + dateFormat.format(date) + " " + timeFormat.format(date))
            .setPositiveButton("Okay"){_,_ ->}
            .show()
    }

    private fun getTime(): Long
    {
        editTempoView = findViewById(R.id.etTempoQuePensaFicar);
        var year = Calendar.getInstance().get(Calendar.YEAR)
        var month = Calendar.getInstance().get(Calendar.MONTH)
        var day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        var hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        var minute = Calendar.getInstance().get(Calendar.MINUTE)
        val tempo = editTempoView.text.toString().toInt()

        val calendar = Calendar.getInstance()
        calendar.set(year, month, day, hour, minute)
        calendar.add(Calendar.MINUTE,tempo)
        Log.d("teste",calendar.toString())
        return calendar.timeInMillis
    }



}