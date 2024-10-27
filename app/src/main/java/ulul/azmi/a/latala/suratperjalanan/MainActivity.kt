package ulul.azmi.a.latala.suratperjalanan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import ulul.azmi.a.latala.suratperjalanan.data.TripData
import ulul.azmi.a.latala.suratperjalanan.ui.screens.AddTripScreen
import ulul.azmi.a.latala.suratperjalanan.ui.screens.TripListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // State untuk menyimpan daftar perjalanan
            var tripList by remember { mutableStateOf(listOf<TripData>()) }
            // State untuk menampilkan layar tambah perjalanan
            var showAddTripScreen by remember { mutableStateOf(false) }
            // State untuk menyimpan perjalanan yang sedang diedit
            var tripToEdit by remember { mutableStateOf<TripData?>(null) }

            // Fungsi untuk menghapus perjalanan dari daftar
            val onDeleteTrip: (Int) -> Unit = { id ->
                tripList = tripList.filter { it.id != id } // Menghapus perjalanan berdasarkan ID
            }

            // Fungsi untuk mengedit perjalanan
            val onEditTrip: (TripData) -> Unit = { trip ->
                tripToEdit = trip // Menyimpan perjalanan yang akan diedit
                showAddTripScreen = true // Menampilkan layar tambah/edit
            }

            // Menampilkan layar tambah/edit jika 'showAddTripScreen' true
            if (showAddTripScreen) {
                AddTripScreen(
                    onSaveTrip = { nama, tujuan, tanggal, keterangan ->
                        if (tripToEdit != null) {
                            // Edit perjalanan yang sudah ada
                            tripList = tripList.map {
                                if (it.id == tripToEdit!!.id) {
                                    it.copy(nama = nama, tujuan = tujuan, tanggal = tanggal, keterangan = keterangan)
                                } else {
                                    it
                                }
                            }
                        } else {
                            // Tambah perjalanan baru
                            val newTrip = TripData(
                                id = (tripList.size + 1), // Menghasilkan ID unik
                                nama = nama,
                                tujuan = tujuan,
                                tanggal = tanggal,
                                keterangan = keterangan
                            )
                            tripList = tripList + newTrip // Menambahkan perjalanan baru ke daftar
                        }
                        showAddTripScreen = false // Kembali ke layar daftar setelah menyimpan
                        tripToEdit = null // Reset trip yang diedit
                    },
                    onCancel = {
                        showAddTripScreen = false // Menutup layar tambah/edit
                        tripToEdit = null // Reset trip yang diedit
                    }
                )
            } else {
                // Menampilkan daftar perjalanan
                TripListScreen(
                    trips = tripList, // Daftar perjalanan yang akan ditampilkan
                    onAddTripClick = { showAddTripScreen = true }, // Menampilkan layar tambah perjalanan
                    onDeleteTrip = onDeleteTrip, // Menghapus perjalanan
                    onEditTrip = onEditTrip // Mengedit perjalanan
                )
            }
        }
    }
}
