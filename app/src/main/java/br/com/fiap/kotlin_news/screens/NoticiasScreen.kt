package br.com.fiap.kotlin_news.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.kotlin_news.components.CardNoticia
import br.com.fiap.kotlin_news.components.ListEmpty
import br.com.fiap.kotlin_news.components.TopMenu
import br.com.fiap.kotlin_news.model.NewsResponse
import br.com.fiap.kotlin_news.model.Noticia
import br.com.fiap.kotlin_news.service.RetrofitFactory
import br.com.fiap.kotlin_news.ui.theme.BackgroundDark
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


fun atualizarTitulosSource(newsResponse: NewsResponse): NewsResponse {
    val novosArtigos = newsResponse.articles.results.map { article ->
        if (article.source.title.contains("RDNEWS - Portal de not�cias de MT")) {
        val novoSource = article.source.copy(title = "RDNEWS - Portal de notícias de MT")
        article.copy(source = novoSource)
        } else {
            article
        }
    }

    return newsResponse.copy(articles = newsResponse.articles.copy(results = novosArtigos.toMutableList()))
}

@Composable
fun NoticiasScreen(navController: NavController, localizacaoPreferencia: String) {

    var localizacao by remember {
        mutableStateOf("${localizacaoPreferencia.replace("_", " ")}")
    }

    var listaNoticiasState by remember {
        mutableStateOf(listOf<Noticia>())
    }

    var isLoading by remember {
        mutableStateOf(true)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(BackgroundDark)) {

        Column() {
            TopMenu(localizacao) { novaLocalizacao ->
                localizacao = novaLocalizacao
            }

            Spacer(modifier = Modifier.height(10.dp))
            // Usando LaunchedEffect para carregar dados automaticamente ao abrir a tela
            LaunchedEffect(localizacao) {
                val path = "https://en.wikipedia.org/wiki/${localizacao.replace(" ", "_")}";
                val call = RetrofitFactory()
                    .getNoticiaService()
                    .getNoticiaByCidade(path)

                call.enqueue(object : Callback<NewsResponse> {
                    override fun onResponse(
                        call: Call<NewsResponse>,
                        response: Response<NewsResponse>
                    ) {
                        if (response.isSuccessful) {
                            val body = response.body()
                            val artigosAtualizados = body?.let { atualizarTitulosSource(it) }

                            listaNoticiasState = artigosAtualizados?.articles?.results?: emptyList()
                            isLoading = false

                            //LOG
                            Log.i("fiap", "onResponse ${response.body()}")
                        } else {
                            Log.e("fiap", "Erro na resposta: ${response.errorBody()?.string()}")
                        }
                    }

                    override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                        Log.e("fiap", "Falha na requisição: ${t.message}")
                        isLoading = false
                    }
                })
            }





            if(isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    CircularProgressIndicator(color = androidx.compose.ui.graphics.Color.White)
                }
            }
            else {
                if(listaNoticiasState.size <= 0) {
                    ListEmpty(navController)
                }
                else {
                    LazyColumn {
                        items(listaNoticiasState) {
                            CardNoticia(it, navController, localizacao)
                        }
                    }
                }
            }
        }
    }
}
