package com.ipvc.projetocm.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "reviewParque_table",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["idUser"],
        onDelete = CASCADE)])

class ReviewParque (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val estrelas: Double,
    val desc: String,
    val idUser: Int
)