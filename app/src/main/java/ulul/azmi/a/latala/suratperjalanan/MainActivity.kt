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
            var tripList by remember { mutableStateOf(listOf<TripData>()) }
            var showAddTripScreen by remember { mutableStateOf(false) }
            var tripToEdit by remember { mutableStateOf<TripData?>(null) }

            val onDeleteTrip: (Int) -> Unit = { id ->
                tripList = tripList.filter { it.id != id }
            }

            val onEditTrip: (TripData) -> Unit = { trip ->
                tripToEdit = trip
                showAddTripScreen = true // Tampilkan layar tambah/edit
            }

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
                                id = (tripList.size + 1),
                                nama = nama,
                                tujuan = tujuan,
                                tanggal = tanggal,
                                keterangan = keterangan
                            )
                            tripList = tripList + newTrip
                        }
                        showAddTripScreen = false // Kembali ke layar daftar setelah menyimpan
                        tripToEdit = null // Reset trip yang diedit
                    },
                    onCancel = {
                        showAddTripScreen = false
                        tripToEdit = null // Reset trip yang diedit
                    }
                )
            } else {
                TripListScreen(
                    trips = tripList,
                    onAddTripClick = { showAddTripScreen = true },
                    onDeleteTrip = onDeleteTrip,
                    onEditTrip = onEditTrip
                )
            }
        }
    }
}

