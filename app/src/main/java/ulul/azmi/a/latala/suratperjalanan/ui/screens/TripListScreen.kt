// Layar untuk menampilkan daftar perjalanan
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
    trips: List<TripData>, // Daftar perjalanan yang akan ditampilkan
    onAddTripClick: () -> Unit, // Callback untuk menambah perjalanan
    onDeleteTrip: (Int) -> Unit, // Callback untuk menghapus perjalanan
    onEditTrip: (TripData) -> Unit // Callback untuk mengedit perjalanan
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Daftar Perjalanan Dinas") } // Judul aplikasi
            )
        },
        floatingActionButton = {
            // Tombol untuk menambah perjalanan
            FloatingActionButton(onClick = onAddTripClick) {
                Text("+") // Teks tombol tambah
            }
        },
        content = { padding ->
            // Menampilkan daftar perjalanan dalam bentuk list
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp) // Padding untuk isi daftar
            ) {
                items(trips.size) { index ->
                    val trip = trips[index] // Mengambil perjalanan berdasarkan index
                    TripItem(trip = trip, onDeleteTrip = onDeleteTrip, onEditTrip = onEditTrip) // Menampilkan item perjalanan
                }
            }
        }
    )
}

// Layar untuk menampilkan setiap item perjalanan
@Composable
fun TripItem(trip: TripData, onDeleteTrip: (Int) -> Unit, onEditTrip: (TripData) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp), // Padding vertikal untuk item
        elevation = CardDefaults.cardElevation(4.dp) // Elevasi untuk efek bayangan
    ) {
        Column(
            modifier = Modifier.padding(16.dp) // Padding untuk isi card
        ) {
            // Menampilkan informasi perjalanan
            Text(text = "Nama: ${trip.nama}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp)) // Jarak antara elemen
            Text(text = "Tujuan: ${trip.tujuan}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Tanggal: ${trip.tanggal}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Keterangan: ${trip.keterangan}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))

            // Tombol untuk mengedit dan menghapus perjalanan
            Row {
                Button(onClick = { onEditTrip(trip) }) {
                    Text("Edit") // Tombol edit
                }
                Spacer(modifier = Modifier.width(8.dp)) // Jarak antara tombol
                Button(onClick = { onDeleteTrip(trip.id) }) {
                    Text("Hapus") // Tombol hapus
                }
            }
        }
    }
}
