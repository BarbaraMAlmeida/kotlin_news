package br.com.fiap.kotlin_news.model

data class Noticia (
    val author: String = "",
    val title: String = "",
    val description: String = "",
    val url: String = "",
    val urlToImage: String = "",
    val publishedAt: String = "",
    val source: Source,
    val content: String = ""

)
