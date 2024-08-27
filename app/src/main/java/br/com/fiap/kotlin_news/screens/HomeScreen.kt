package br.com.fiap.kotlin_news.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.kotlin_news.ui.theme.White
import br.com.fiap.kotlin_news.R

@Composable
fun HomeScreen(navController: NavController) {

    var localizacao by remember {
        mutableStateOf("Belo Horizonte")
    }

    val defaultFontFamily = FontFamily.Serif
    val titleStyle = TextStyle(
        fontSize = 48.sp,
        color = White,
        fontFamily = defaultFontFamily,
        letterSpacing = (-0.16).sp,
        textAlign = TextAlign.Center
    )

    val newsStyle = TextStyle(
        fontSize = 48.sp,
        color = Color(0xFF008060),
        fontFamily = defaultFontFamily,
        letterSpacing = (-0.16).sp,
        textAlign = TextAlign.Center
    )

    val subtitleStyle = TextStyle(
        fontSize = 20.sp,
        color = Color.White,
        textAlign = TextAlign.Center
    )

    val buttonTextStyle = TextStyle(
        fontSize = 12.sp,
        color = Color.White
    )

    val infoTextStyle = TextStyle(
        fontSize = 16.sp,
        color = Color.White,
        textAlign = TextAlign.Center
    )


//    Box(modifier = Modifier.fillMaxSize()) {
//        Text(text = "Opa pessoal!",
//            modifier = Modifier.padding(top = 34.dp))
//
//        Button(onClick = {
//            navController.navigate("noticias/${localizacao}")
//        }) {
//            Text(text = "IR!!")
//        }
//    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.mipmap.imgg),
            contentDescription = "Imagem de fundo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .height(620.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF5BB9A7).copy(alpha = 0.6f)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = "BUSCA",
                style = titleStyle,
                modifier = Modifier
                    .padding(start = 1.dp, top = 45.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 1.dp, top = 0.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "NEWS",
                    style = newsStyle
                )

                Spacer(modifier = Modifier.width(8.dp))

                Image(
                    painter = painterResource(id = R.mipmap.immg),
                    contentDescription = "Imagem ao lado do NEWS",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(40.dp)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Seu buscador de noticias",
                style = subtitleStyle,
                modifier = Modifier
                    .padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = 0.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Coloque sua região de preferência",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Box(
                        modifier = Modifier
                            .size(width = 274.dp, height = 40.dp)
                            .background(Color(0xFFD9D9D9), shape = RoundedCornerShape(20.dp))
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        BasicTextField(
                            value = localizacao,
                            onValueChange = { localizacao = it },
                            textStyle = TextStyle(
                                color = Color.Gray,
                                fontSize = 15.sp,
                                fontFamily = FontFamily.Serif
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .size(width = 62.dp, height = 38.dp)
                            .background(Color(0xFF008060), shape = RoundedCornerShape(10.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .clickable { navController.navigate("noticias/${localizacao}") },
                        contentAlignment = Alignment.Center

                    ) {
                        Text(
                            text = "IR!",
                            style = buttonTextStyle
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Clique em “IR” e veja as principais\nnotícias da sua região",
                        style = infoTextStyle,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }

}
