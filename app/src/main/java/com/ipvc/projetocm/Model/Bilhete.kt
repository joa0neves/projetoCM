package com.ipvc.projetocm.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

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
    val data: Long,
    val hora: Long,
    val qr: String,
    val idUtilizador: Int
)