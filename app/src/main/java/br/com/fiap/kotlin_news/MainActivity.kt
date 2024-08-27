package br.com.fiap.kotlin_news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.kotlin_news.screens.DetalheNoticiaScreen
import br.com.fiap.kotlin_news.screens.HomeScreen
import br.com.fiap.kotlin_news.screens.NoticiasScreen
import br.com.fiap.kotlin_news.ui.theme.KotlinnewsTheme

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
                        composable(route = "detalhe-noticia/{noticia}") {
                            val id = it.arguments?.getString("id")
                            DetalheNoticiaScreen(navController, id!!)
                        }

                    }
                }
            }
        }
    }
}
