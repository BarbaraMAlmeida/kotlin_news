package br.com.fiap.kotlin_news.service

import br.com.fiap.kotlin_news.model.Cidade
import br.com.fiap.kotlin_news.model.Estado
import br.com.fiap.kotlin_news.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface LocalidadeService {

    @GET("estados")
    fun getEstados(): Call<List<Estado>>

    @GET("estados/{uf}/municipios")
    fun getCidadesByEstado(
        @Path("uf") uf: String
    ):  Call<List<Cidade>>

}