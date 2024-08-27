package br.com.fiap.kotlin_news.service

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val API_KEY = "dd7ca759de9c459b882183d4c01d7050"
    private val URL = "https://newsapi.org/v2/" // URL base terminando com '/'

    // Opcional: Usar um Interceptor para adicionar a API Key automaticamente
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original: Request = chain.request()
            val originalUrl = original.url
            val url = originalUrl.newBuilder()
                .addQueryParameter("apiKey", API_KEY)
                .build()
            val requestBuilder = original.newBuilder().url(url)
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        .build()

    private val retrofitFactory = Retrofit.Builder()
        .baseUrl(URL)
        .client(client) // Adiciona o cliente com o Interceptor
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getNoticiaService(): NoticiaService {
        return retrofitFactory.create(NoticiaService::class.java)
    }
}