package br.com.fiap.kotlin_news.model


data class NewsResponse(
    val articles: Results
)


data class Results(
    val results: List<Noticia>
)
