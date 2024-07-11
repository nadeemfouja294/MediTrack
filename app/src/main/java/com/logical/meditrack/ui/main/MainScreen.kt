package com.logical.meditrack.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.logical.meditrack.domain.model.Medicine
import com.logical.meditrack.viewmodel.MainViewModel

@Composable
fun MainScreen(navController: NavController, username: String?) {
    val viewModel: MainViewModel = hiltViewModel()
    val medicineList by viewModel.medicineList.collectAsStateWithLifecycle()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Hello, ${username ?: "User"}")
        Text("Good ${viewModel.getGreeting()}")
        LazyColumn {
            items(medicineList) { medicine ->
                MedicineCard(medicine) { selectedMedicine ->
                    // Navigate to a detailed view if needed
                }
            }
        }
    }
}

@Composable
fun MedicineCard(medicine: Medicine, onClick: (Medicine) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(medicine) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Name: ${medicine.name}")
            Text("Dose: ${medicine.dose}")
            Text("Strength: ${medicine.strength}")
        }
    }
}
