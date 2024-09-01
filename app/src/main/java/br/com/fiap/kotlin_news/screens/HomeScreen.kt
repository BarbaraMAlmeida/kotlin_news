package br.com.fiap.kotlin_news.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.kotlin_news.ui.theme.White
import br.com.fiap.kotlin_news.R
import br.com.fiap.kotlin_news.ui.theme.GreenLight
import br.com.fiap.kotlin_news.ui.theme.GreenPrimary
import br.com.fiap.kotlin_news.components.DropdownHome
import br.com.fiap.kotlin_news.model.Cidade
import br.com.fiap.kotlin_news.model.Estado
import br.com.fiap.kotlin_news.service.IbgeFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun HomeScreen(navController: NavController) {

    var localizacao by remember {
        mutableStateOf("")
    }

    var listaEstados by remember {
        mutableStateOf(listOf<Estado>())
    }

    var listaCidades by remember {
        mutableStateOf(listOf<Cidade>())
    }

    var estadoSelecionado by remember { mutableStateOf<Estado?>(null) }
    var cidadeSelecionada by remember { mutableStateOf<Cidade?>(null) }


    LaunchedEffect(Unit) {
        val callEstados = IbgeFactory()
            .getLocalidadeService()
            .getEstados()

        callEstados.enqueue(object : Callback<List<Estado>> {
            override fun onResponse(
                call: Call<List<Estado>>,
                response: Response<List<Estado>>
            ) {
                if (response.isSuccessful) {
                    listaEstados = response.body() ?: emptyList()
                    Log.i("fiap", "Estados carregados: ${listaEstados.size}")
                } else {
                    Log.e("fiap", "Erro na resposta: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<Estado>>, t: Throwable) {
                Log.e("fiap", "Falha na requisição: ${t.message}")
            }
        })
    }

    LaunchedEffect(estadoSelecionado) {
        estadoSelecionado?.let { estado ->
            val callCidades = IbgeFactory()
                .getLocalidadeService()
                .getCidadesByEstado(estado.sigla)

            callCidades.enqueue(object : Callback<List<Cidade>> {
                override fun onResponse(
                    call: Call<List<Cidade>>,
                    response: Response<List<Cidade>>
                ) {
                    if (response.isSuccessful) {
                        listaCidades = response.body() ?: emptyList()
                        Log.i("fiap", "Cidades carregadas: ${listaCidades.size}")
                    } else {
                        Log.e("fiap", "Erro na resposta: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<List<Cidade>>, t: Throwable) {
                    Log.e("fiap", "Falha na requisição: ${t.message}")
                }
            })
        }
    }

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
                color = White,
                fontWeight = FontWeight.Bold,
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

                    DropdownHome(
                        label = "Selecione um estado",
                        optionsEstado = listaEstados,
                        optionsCidade = emptyList(),
                        onEstadoSelecionado = { estadoSelecionado = it },
                        onCidadeSelecionada = { cidadeSelecionada = it }
                    )
                    if (estadoSelecionado != null) {
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

                        DropdownHome(
                            label = "Selecione uma cidade",
                            optionsEstado = emptyList(),
                            optionsCidade = listaCidades,
                            onEstadoSelecionado = {},
                            onCidadeSelecionada = { cidadeSelecionada = it }
                        )
                    }


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
                            if(cidadeSelecionada != null) {
                                val formattedLocation = cidadeSelecionada?.nome?.replace(" ", "_");
                                navController.navigate("noticias/${formattedLocation}")
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
                            fontWeight = FontWeight.SemiBold,
                        )
                    }

                }
            }
        }
    }

}
