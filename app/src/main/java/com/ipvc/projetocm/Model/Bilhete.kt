package com.ipvc.projetocm.Model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "bilhete_table",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["idUtilizador"],
        onDelete = CASCADE)])

class Bilhete (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nrLugar: String,
    val data: String,
    val qr: String,
    val valor: Float,
    val estaPago: Boolean,
    val idUtilizador: Int
)