package br.com.fiap.kotlin_news.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.kotlin_news.model.Noticia
import br.com.fiap.kotlin_news.ui.theme.BackgroundDark
import br.com.fiap.kotlin_news.ui.theme.GreenPrimary
import br.com.fiap.kotlin_news.ui.theme.White
import coil.compose.rememberAsyncImagePainter

@Composable
fun CardNoticia(noticia: Noticia, navController: NavController) {
    Log.i("noticia", "${noticia}")


    Spacer(modifier = Modifier.height(10.dp))
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(250.dp)
        .drawBehind {
            val borderSize = 2.dp.toPx()
            drawLine(
                color = Color(0xFF5B5B5B),
                start = Offset(x = 0f, size.height),
                end = Offset(size.width, size.height),
                strokeWidth = borderSize
            )
        }
        .height(80.dp),
        colors = CardDefaults.cardColors(
            containerColor = BackgroundDark
        ),
        shape = RectangleShape ) {

        Column (modifier = Modifier
            .fillMaxSize()
            .padding(end = 12.dp, start = 12.dp)) {
            Image(
                painter = rememberAsyncImagePainter(model = noticia.urlToImage),
                contentDescription = "Imagem da notícia",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp) // Ajuste a altura conforme necessário
                    .clip(RoundedCornerShape(12.dp)) // Aplica bordas arredondadas de 12.dp
                    .background(Color.Gray), // Adiciona um fundo para a imagem
                contentScale = ContentScale.Crop // Ajusta a escala da imagem para preencher o espaço
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "${noticia.title}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = White,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Text(text = "fonte: ${noticia.source.name}",
                    color = Color.White)
                Row () {
                    OutlinedButton(
                        onClick = {
                            navController.navigate("detalhe-noticia/${noticia}")
                        },
                        modifier = Modifier.height(40.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GreenPrimary,
                            contentColor = White,
                        ),
                        border = BorderStroke(0.dp, Color.Transparent),

                        ) {
                        Text(text = "Ver detalhes",
                            fontSize = 14.sp,
                        )
                    }
                }
            }

        }
    }

}
//
//@Preview
//@Composable
//private fun CardNoticiaPreview() {
//    CardNoticia(noticia = Noticia)
//}