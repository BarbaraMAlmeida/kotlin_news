package br.com.fiap.kotlin_news.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavController
import br.com.fiap.kotlin_news.R

@Composable
fun ListEmpty (navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .height(100.dp)
        .padding(end = 16.dp, start = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Não foram encontrados resultados para essa cidade.",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            style = TextStyle(
                lineHeight = 24.sp,
            )
        )

        Button(
            modifier = Modifier.width(120.dp),
            onClick = {
                navController.navigate("home")
            },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF008060))
        )
        {
            Row(modifier = Modifier
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "icone de favoritar",
                    modifier = Modifier
                        .size(26.dp)
                )
                Text(text = "Voltar",
                    fontSize = 16.sp)
            }
        }

    }
}