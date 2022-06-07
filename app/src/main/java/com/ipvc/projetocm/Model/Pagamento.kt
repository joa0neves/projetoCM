package com.ipvc.projetocm.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "pagamento_table",
    foreignKeys = [ForeignKey(
        entity = Bilhete::class,
        parentColumns = ["id"],
        childColumns = ["idBilhete"],
        onDelete = ForeignKey.CASCADE
    )])

class Pagamento (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val valor: Float,
    val estaPago: String,
    val idBilhete: Int
)