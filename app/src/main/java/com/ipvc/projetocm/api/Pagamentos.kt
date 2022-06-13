package com.ipvc.projetocm.api

data class Pagamentos(
    val data: String,
    val tempo: String,
    val valor: String,
    val estaPago: Int
)