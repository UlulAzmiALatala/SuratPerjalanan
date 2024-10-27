package ulul.azmi.a.latala.suratperjalanan.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ulul.azmi.a.latala.suratperjalanan.data.TripData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripListScreen(
    trips: List<TripData>,
    onAddTripClick: () -> Unit,
    onDeleteTrip: (Int) -> Unit,
    onEditTrip: (TripData) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Daftar Perjalanan Dinas") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddTripClick) {
                Text("+")
            }
        },
        content = { padding ->
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(trips.size) { index ->
                    val trip = trips[index]
                    TripItem(trip = trip, onDeleteTrip = onDeleteTrip, onEditTrip = onEditTrip)
                }
            }
        }
    )
}

@Composable
fun TripItem(trip: TripData, onDeleteTrip: (Int) -> Unit, onEditTrip: (TripData) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Nama: ${trip.nama}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Tujuan: ${trip.tujuan}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Tanggal: ${trip.tanggal}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Keterangan: ${trip.keterangan}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Button(onClick = { onEditTrip(trip) }) {
                    Text("Edit")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { onDeleteTrip(trip.id) }) {
                    Text("Hapus")
                }
            }
        }
    }
}

