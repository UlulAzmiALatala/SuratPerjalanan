package ulul.azmi.a.latala.suratperjalanan.data

data class TripData(
    val id: Int, // ID unik untuk setiap perjalanan
    val nama: String, // Nama pemohon perjalanan
    val tujuan: String, // Tujuan perjalanan
    val tanggal: String, // Tanggal perjalanan
    val keterangan: String // Keterangan tambahan
)
