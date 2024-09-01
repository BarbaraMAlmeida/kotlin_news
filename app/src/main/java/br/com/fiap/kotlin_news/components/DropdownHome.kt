package br.com.fiap.kotlin_news.components
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.kotlin_news.model.Cidade
import br.com.fiap.kotlin_news.model.Estado
import br.com.fiap.kotlin_news.ui.theme.Black
import br.com.fiap.kotlin_news.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownHome(
    label: String,
    optionsEstado: List<Estado>?,
    optionsCidade: List<Cidade>?,
    onEstadoSelecionado: (Estado) -> Unit,
    onCidadeSelecionada: (Cidade) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Selecione uma opção") }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier
            .background(Color.Transparent)
            .border(2.dp, Color.Transparent, RoundedCornerShape(8.dp))
    ) {
        TextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { Text(label,
                color = Black) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                unfocusedContainerColor = White,
                focusedContainerColor = White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .menuAnchor()
                .border(2.dp, Color.Transparent, RoundedCornerShape(8.dp))

        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier

                .border(0.dp, Color.Transparent, RoundedCornerShape(8.dp))
        ) {
            optionsEstado?.forEach { estado ->
                DropdownMenuItem(
                    text = { Text(estado.nome) },
                    onClick = {
                        selectedOption = estado.nome
                        expanded = false
                        onEstadoSelecionado(estado)
                    },
                    modifier = Modifier
                        .background(White)
                        .border(1.dp, Color.Transparent, RoundedCornerShape(8.dp))
                )
            }

            optionsCidade?.forEach { cidade ->
                DropdownMenuItem(
                    text = { Text(cidade.nome) },
                    onClick = {
                        selectedOption = cidade.nome
                        expanded = false
                        onCidadeSelecionada(cidade)
                    },
                    modifier = Modifier
                        .background(White)
                        .border(1.dp, Color.Transparent, RoundedCornerShape(8.dp))
                )
            }
        }
    }
}


//@Preview
//@Composable
//private fun Dropdown() {
//    DropdownHome()
//}