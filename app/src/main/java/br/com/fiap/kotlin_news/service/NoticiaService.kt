package br.com.fiap.kotlin_news.service

import br.com.fiap.kotlin_news.model.NewsResponse
import br.com.fiap.kotlin_news.model.Results
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NoticiaService {

    //url base
    //https://newsapi.org/v2/top-headlines
    //val base_url: String = "https://newsapi.org/v2/top-headlines?apiKey=YOUR_API_KEY&";


    //https://newsapi.org/v2/everything?q=S%C3%A3o%20Paulo&apiKey=YOUR_API_KEY
    //GET https://newsapi.org/v2/top-headlines?country=us&apiKey=API_KEY

//    @GET("q={cidade}")
//    fun getNoticiaByCidade(
//        @Query("cidade") cidade: String,
//    ): Call<List<Noticia>>


    @Headers("Content-Type: application/json; charset=utf-8")
    @GET("getArticles")
    fun getNoticiaByCidade(
        @Query("sourceLocationUri") cidade: String
    ):  Call<NewsResponse>
}