package ulul.azmi.a.latala.suratperjalanan.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ulul.azmi.a.latala.suratperjalanan.data.TripData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTripScreen(
    onSaveTrip: (String, String, String, String) -> Unit,
    onCancel: () -> Unit,
    trip: TripData? = null
) {
    var nama by remember { mutableStateOf(trip?.nama ?: "") }
    var tujuan by remember { mutableStateOf(trip?.tujuan ?: "") }
    var tanggal by remember { mutableStateOf(trip?.tanggal ?: "") }
    var keterangan by remember { mutableStateOf(trip?.keterangan ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (trip == null) "Tambah Perjalanan Dinas" else "Edit Perjalanan Dinas") },
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = nama,
                    onValueChange = { nama = it },
                    label = { Text("Nama") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = tujuan,
                    onValueChange = { tujuan = it },
                    label = { Text("Tujuan") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = tanggal,
                    onValueChange = { tanggal = it },
                    label = { Text("Tanggal") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = keterangan,
                    onValueChange = { keterangan = it },
                    label = { Text("Keterangan") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Button(onClick = { onSaveTrip(nama, tujuan, tanggal, keterangan) }) {
                        Text("Simpan")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedButton(onClick = onCancel) {
                        Text("Batal")
                    }
                }
            }
        }
    )
}
