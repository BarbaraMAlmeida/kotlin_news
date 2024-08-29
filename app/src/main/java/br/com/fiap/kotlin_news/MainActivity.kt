package br.com.fiap.kotlin_news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.fiap.kotlin_news.model.Noticia
import br.com.fiap.kotlin_news.screens.DetalheNoticiaScreen
import br.com.fiap.kotlin_news.screens.HomeScreen
import br.com.fiap.kotlin_news.screens.NoticiasScreen
import br.com.fiap.kotlin_news.ui.theme.KotlinnewsTheme
import com.google.gson.Gson
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinnewsTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {

                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "home") {
                        composable(route = "home") {
                            HomeScreen(navController)

                        }
                        composable(route = "noticias/{localizacao}") {
                            val localizacao_preferencia = it.arguments?.getString("localizacao")
                            NoticiasScreen(navController, localizacao_preferencia!!)

                        }
                        composable(
                            route = "detalhe-noticia/{noticia}",
                            arguments = listOf(navArgument("noticia") { type = NavType.StringType })
                        ) {
                            val encodedJson = it.arguments?.getString("noticia")
                            val decodedJson = URLDecoder.decode(encodedJson, StandardCharsets.UTF_8.toString())
                            val noticia = Gson().fromJson(decodedJson, Noticia::class.java)
                            DetalheNoticiaScreen(navController, noticia)
                        }

                    }
                }
            }
        }
    }
}
