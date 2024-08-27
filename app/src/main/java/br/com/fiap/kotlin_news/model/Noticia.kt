package br.com.fiap.kotlin_news.model

data class Noticia (
    val author: String = "",
    val date: String = "",
    val dataType: String = "",
    val title: String = "",
    val url: String = "",
    val image: String = "",
    val publishedAt: String = "",
    val source: Source,
    val body: String = ""

)
