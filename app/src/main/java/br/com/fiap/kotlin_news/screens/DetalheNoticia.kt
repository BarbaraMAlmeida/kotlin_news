package br.com.fiap.kotlin_news.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.kotlin_news.model.Noticia
import br.com.fiap.kotlin_news.ui.theme.Black
import br.com.fiap.kotlin_news.ui.theme.White
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.sp
import br.com.fiap.kotlin_news.R
import br.com.fiap.kotlin_news.components.TopMenu
import coil.compose.rememberAsyncImagePainter


@Composable
fun DetalheNoticiaScreen(navController: NavController, noticia: Noticia) {

    Log.i("noticia detalhe", "${noticia}")

    val scrollState = rememberScrollState()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(White)
        .verticalScroll(scrollState)) {
        Column() {
            TopMenu(localizacao = "Belo Horizonte"){}

            Spacer(modifier = Modifier.height(10.dp))

            Column (modifier = Modifier.fillMaxSize()) {
//                Spacer(modifier = Modifier.height(40.dp))
////                Text(text = "oi ${noticia.title}",
////                    color = Black)

                Spacer(modifier = Modifier.height(0.dp))

                // Título da notícia
                Text(
                    text = "${noticia.title}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                // Subtítulo
                Text(
                    text = "Fonte: ${noticia.source.title}",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 5.dp)
                )

                // Data e hora
                Text(
                    text = "Data de publicação: ${noticia.date}",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 5.dp)
                )

                // Imagem da superlua 1
                Image(
                    painter = rememberAsyncImagePainter(model = noticia.image),
                    contentDescription = "Superlua 1",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )

                //Texto da imagem 1

                Text(
                    text = "${noticia.body}",
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

//                // Imagem da superlua 2
//                Image(
//                    painter = painterResource(id = R.drawable.ball),
//                    contentDescription = "Superlua 2",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(200.dp)
//                )

//                // Descrição da imagem 2
//                Text(
//                    text = "Superlua Azul vista no bairro Cajazeiras na cidade de Fortaleza-CE.",
//                    fontSize = 10.sp,
//                    color = Color.Gray,
//                    modifier = Modifier.padding(vertical = 8.dp)
//                )

                // Espaço para os botões
                Spacer(modifier = Modifier.height(20.dp))

                // Botões de ação
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        modifier = Modifier.width(150.dp),
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF008060))
                    )
                    {
                        Row(modifier = Modifier
                            .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            Icon(
                                painter = painterResource(id = R.drawable.favorite),
                                contentDescription = "local",
                                modifier = Modifier
                                    .size(26.dp)
                            )
                            Text(text = "Favoritos")
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        modifier = Modifier.width(160.dp),
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF008060))
                    ) {

                        Row(modifier = Modifier
                            .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            Icon(
                                painter = painterResource(id = R.drawable.share),
                                contentDescription = "local",
                                modifier = Modifier
                                    .size(26.dp)
                            )
                            Text(text = "Compartilhar")
                        }


                    }

                }
            }
        }
    }

}

