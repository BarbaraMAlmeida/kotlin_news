package br.com.fiap.kotlin_news.service

import br.com.fiap.kotlin_news.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
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


    @GET("everything")
    fun getNoticiaByCidade(
        @Query("q") cidade: String,
        @Query("apiKey") apiKey: String = "dd7ca759de9c459b882183d4c01d7050",
        @Query("sortBy") sortBy: String = "publishAt"
    ):  Call<NewsResponse>
}