package com.ipvc.projetocm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ipvc.projetocm.R
import com.ipvc.projetocm.api.Bilhete

class BilheteAdapter(val bilhetes: List<Bilhete>): RecyclerView.Adapter<BilhetesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BilhetesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.recyclerview_item,
            parent, false)
        return BilhetesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bilhetes.size
    }

    override fun onBindViewHolder(holder: BilhetesViewHolder, position: Int) {
        return holder.bind(bilhetes[position])
    }
}

class BilhetesViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    private val data: TextView = itemView.findViewById(R.id.dataView)
    private val tempo:TextView = itemView.findViewById(R.id.tempoView)
    private val valor:TextView = itemView.findViewById(R.id.valorView)

    fun bind(Bilhete: Bilhete) {
        data.text = "Data: " +  Bilhete.data
        tempo.text = "Tempo: " + Bilhete.tempo
        valor.text = "Preço: " + Bilhete.valor
    }

}