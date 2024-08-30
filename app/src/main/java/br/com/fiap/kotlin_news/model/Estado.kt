package br.com.fiap.kotlin_news.model

data class Estado(
    val id: Int,
    val sigla: String,
    val nome: String,
    val regiao: Regiao
)

data class Regiao(
    val id: Int,
    val sigla: String,
    val nome: String
)