package br.com.fiap.kotlin_news.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.kotlin_news.ui.theme.White
import br.com.fiap.kotlin_news.R
import br.com.fiap.kotlin_news.ui.theme.Black
import br.com.fiap.kotlin_news.ui.theme.GrayLight
import br.com.fiap.kotlin_news.ui.theme.GreenLight
import br.com.fiap.kotlin_news.ui.theme.GreenPrimary
import br.com.fiap.kotlin_news.ui.theme.GreenSecondary
import br.com.fiap.kotlin_news.components.DropdownHome

@Composable
fun HomeScreen(navController: NavController) {

    var localizacao by remember {
        mutableStateOf("")
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenLight)
    ) {

        Image(
            painter = painterResource(id = R.drawable.ball),
            contentDescription = "Imagem de fundo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(bottom = 0.dp,)
                .fillMaxWidth()
                .offset(y = (250.dp))
                .align(Alignment.BottomCenter)
//                .height(620.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF225F53).copy(alpha = 0.6f)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo_capa),
                contentDescription = "Busca News Logotipo",
                modifier = Modifier
                    .size(200.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Seu buscador de noticias",
                style = subtitleStyle,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .offset(y = (-40.dp))
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Informe seu estado",
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        ),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

//                    OutlinedTextField(
//                        value = localizacao,
//                        onValueChange = { localizacao = it },
//                        textStyle = TextStyle(
//                            color = Color.Gray,
//                            fontSize = 15.sp,
//                            fontFamily = FontFamily.SansSerif
//                        ),
//                        colors = OutlinedTextFieldDefaults.colors(
//                            focusedBorderColor = GreenPrimary,
//                            unfocusedBorderColor = GreenPrimary,
//                            cursorColor = GreenPrimary,
//                            focusedContainerColor = Color.White,
//                            unfocusedContainerColor =  Color.White,
//                        ),
//                        placeholder = {
//                            Text(text = "Informe seu estado",
//                                color = Color(0xFF9F9F9F))
//                        },
//                        modifier = Modifier
//                            .height(50.dp)
//                            .width(270.dp),
//                        shape = RoundedCornerShape(10.dp),
//                        keyboardOptions = KeyboardOptions(
//                            imeAction = ImeAction.Done
//                        )
//                    )

                    DropdownHome("Informe seu estado")



                    Spacer(modifier = Modifier.height(30.dp))


                    Text(
                        text = "Informe sua cidade de preferência",
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        ),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    DropdownHome("Informe sua cidade")

                    OutlinedTextField(
                        value = localizacao,
                        onValueChange = { localizacao = it },
                        textStyle = TextStyle(
                            color = Color.Gray,
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = GreenPrimary,
                            unfocusedBorderColor = GreenPrimary,
                            cursorColor = GreenPrimary,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor =  Color.White,
                        ),
                        placeholder = {
                            Text(text = "Informe sua cidade",
                                color = Color(0xFF9F9F9F))
                        },
                        modifier = Modifier
                            .height(50.dp)
                            .width(270.dp),
                        shape = RoundedCornerShape(10.dp),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        )
                    )


                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        text = "Clique em “IR” e veja as principais\n" +
                                "notícias da sua região",
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        ),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            if(localizacao != "") {
                                navController.navigate("noticias/${localizacao}")
                            }
                        },
                        colors = ButtonDefaults.buttonColors(GreenPrimary),
                        modifier = Modifier
                            .size(width = 100.dp, height = 50.dp)
                            .padding(horizontal = 8.dp, vertical = 4.dp),

                        shape = RoundedCornerShape(7.dp)

                    ) {
                        Text(
                            text = "IR!",
                            color = White,
                            fontSize = 16.sp,
                            style = buttonTextStyle,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }

                }
            }
        }
    }

}
