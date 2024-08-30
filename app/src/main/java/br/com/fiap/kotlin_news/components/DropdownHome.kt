package br.com.fiap.kotlin_news.components
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.kotlin_news.model.Cidade
import br.com.fiap.kotlin_news.model.Estado

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
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier
                .menuAnchor()
                .border(0.dp, Color(0X00CCFFF5))
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            optionsEstado?.forEach { estado ->
                DropdownMenuItem(
                    text = { Text(estado.nome) },
                    onClick = {
                        selectedOption = estado.nome
                        expanded = false
                        onEstadoSelecionado(estado)
                    }
                )
            }

            optionsCidade?.forEach { cidade ->
                DropdownMenuItem(
                    text = { Text(cidade.nome) },
                    onClick = {
                        selectedOption = cidade.nome
                        expanded = false
                        onCidadeSelecionada(cidade)
                    }
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