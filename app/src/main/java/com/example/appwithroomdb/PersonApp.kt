package com.example.appwithroomdb

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.* // Use material3 if you're using Material3 components
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appwithroomdb.ui.theme.App_with_roomdbTheme

@Composable
fun PersonApp(viewModel: PersonViewModel = viewModel()) {
    val people by viewModel.people.collectAsState()
    var name by remember { mutableStateOf("") }
    var editingPerson by remember { mutableStateOf<Person?>(null) }

    Column(Modifier.padding(16.dp)) {
        Row {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            Button(
                onClick = {
                    if (editingPerson != null) {
                        viewModel.update(editingPerson!!.copy(name = name))
                        editingPerson = null
                    } else {
                        viewModel.add(name)
                    }
                    name = ""
                },
            ) {
                Text(if (editingPerson != null) "Update" else "Add")
            }
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(people) { person ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Sr. No: ${person.id} | Name: ${person.name}")
                    Row {
                        IconButton(onClick = {
                            name = person.name
                            editingPerson = person
                        }) {
                            Icon(Icons.Default.Edit, contentDescription = "Edit")
                        }
                        IconButton(onClick = { viewModel.delete(person) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                }
            }
        }
    }
}
