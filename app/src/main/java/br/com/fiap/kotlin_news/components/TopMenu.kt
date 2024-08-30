package br.com.fiap.kotlin_news.components

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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.kotlin_news.R
import br.com.fiap.kotlin_news.ui.theme.GreenPrimary
import br.com.fiap.kotlin_news.ui.theme.White

@Composable
fun TopMenu(localizacao: String = "", onLocalizacaoChange: (String) -> Unit) {

    var isInputVisible by remember {
        mutableStateOf(false)
    }

    var newLocalizacao  by remember {
        mutableStateOf(localizacao)
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .background(GreenPrimary)) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 30.dp, bottom = 16.dp, start = 8.dp,
                    end = 8.dp
                )
                .height(80.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = "Globo",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)

                )

                Column(modifier = Modifier.padding(start = 8.dp)) {
                    Text(
                        text = "Principais notícias de: ",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = newLocalizacao,
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { isInputVisible = true }
                    )
                }
            }

            Image(
                painter = painterResource(id = R.drawable.logo_branca_foreground),
                contentDescription = "Descrição da imagem",
                modifier = Modifier.size(90.dp), // Ajuste o tamanho da imagem
                contentScale = ContentScale.Crop // Ajuste como a imagem deve ser escalada
            )

        }

        if (isInputVisible) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.7f)) // Fundo para destacar o input
            ) {
                OutlinedTextField(
                    value = newLocalizacao,
                    onValueChange = { newLocalizacao = it },
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                        .fillMaxWidth(),
                    placeholder = { Text(text = "Digite a nova localização") },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = White,
                        focusedContainerColor = White,
                        cursorColor = GreenPrimary,
                        focusedBorderColor = GreenPrimary,
                        unfocusedBorderColor = GreenPrimary
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            isInputVisible = false
                            onLocalizacaoChange(newLocalizacao)
                        }
                    )
                )
            }
        }

    }
}
