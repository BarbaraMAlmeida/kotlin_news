package br.com.fiap.kotlin_news.model

data class Noticia (
    val author: List<Authors>,
    val date: String = "",
    val dataType: String = "",
    val title: String = "",
    val url: String = "",
    val image: String = "",
    val publishedAt: String = "",
    val source: Source,
    val body: String = ""
)


data class Authors (
    val uri: String? = "",
    val name: String? = "",
    val type: String? = "",
)