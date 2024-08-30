package br.com.fiap.kotlin_news.service

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IbgeFactory {

    private val URL = "https://servicodados.ibge.gov.br/api/v1/localidades/"

    // Configura o cliente HTTP com um Interceptor
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original: Request = chain.request()

            // Adiciona o parâmetro de consulta "apiKey" à URL
            val originalUrl = original.url
            val newUrl = originalUrl.newBuilder()
                .build()

            // Cria uma nova requisição com o cabeçalho e a URL modificada
            val requestWithHeaderAndQuery = original.newBuilder()
                .url(newUrl)
                .addHeader("Content-Type", "application/json; charset=iso-8859-1")
                .build()

            // Prossegue com a requisição
            val response: Response = chain.proceed(requestWithHeaderAndQuery)

            // Inspeciona o cabeçalho da resposta para o charset
            val contentTypeHeader = response.header("Content-Type")
            println("Content-Type Header: $contentTypeHeader")

            // Captura os bytes crus da resposta
            val responseBody = response.body
            val responseBytes = responseBody?.bytes()

            // Força a decodificação para UTF-8, assumindo que o conteúdo não está em UTF-8
            val utf8String = responseBytes?.let {
                try {
                    String(it, Charsets.UTF_8) // Tenta UTF-8 primeiro
                } catch (e: Exception) {
                    // Em caso de erro, tenta ISO-8859-1
                    String(it, Charsets.ISO_8859_1)
                }
            }

            // Cria uma nova resposta com o corpo decodificado
            val newResponseBody = ResponseBody.create(responseBody?.contentType(), utf8String ?: "")

            response.newBuilder()
                .body(newResponseBody)
                .build()
        }
        .build()

    // Configura o Retrofit com o cliente HTTP
    private val retrofitFactory = Retrofit.Builder()
        .baseUrl(URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Retorna o serviço de localidades
    fun getLocalidadeService(): LocalidadeService {
        return retrofitFactory.create(LocalidadeService::class.java)
    }

}