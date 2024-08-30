package br.com.fiap.kotlin_news.service

import br.com.fiap.kotlin_news.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NoticiaService {

    @Headers("Content-Type: application/json; charset=utf-8")
    @GET("getArticles")
    fun getNoticiaByCidade(
        @Query("sourceLocationUri") cidade: String
    ):  Call<NewsResponse>
}