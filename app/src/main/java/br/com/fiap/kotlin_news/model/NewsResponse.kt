package br.com.fiap.kotlin_news.model


data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Noticia>
)
