# Rencana Implementasi UI Pelanggan Toko (Jetpack Compose)

Implementasi UI lengkap untuk aplikasi pelanggan toko dengan tema "Dark Industrial Minimalist" menggunakan Jetpack Compose.

## User Review Required

> [!IMPORTANT]
> Proyek akan dimigrasi ke **Kotlin** dan **Jetpack Compose**. File `MainActivity.java` akan dikonversi ke Kotlin (`MainActivity.kt`).

## Proposed Changes

### Configuration & Dependencies

#### [MODIFY] [libs.versions.toml](file:///C:/Users/ASUS/AndroidStudioProjects/NBImplementation/gradle/libs.versions.toml)
Menambahkan dependensi Jetpack Compose, Navigation, dan Kotlin.

#### [MODIFY] [build.gradle.kts (project)](file:///C:/Users/ASUS/AndroidStudioProjects/NBImplementation/build.gradle.kts)
Menambahkan plugin Kotlin.

#### [MODIFY] [build.gradle.kts (app)](file:///C:/Users/ASUS/AndroidStudioProjects/NBImplementation/app/build.gradle.kts)
Mengaktifkan fitur Compose dan menambahkan dependensi.

---

### Core UI & Theme

#### [NEW] [Theme.kt](file:///C:/Users/ASUS/AndroidStudioProjects/NBImplementation/app/src/main/java/com/example/nbimplementation/ui/theme/Theme.kt)
Mendefinisikan skema warna Dark Industrial (#555555, #666666, #424242) dan tipografi.

#### [NEW] [NavGraph.kt](file:///C:/Users/ASUS/AndroidStudioProjects/NBImplementation/app/src/main/java/com/example/nbimplementation/ui/navigation/NavGraph.kt)
Mengatur rute navigasi untuk semua 9 halaman.

---

### Screens

#### [NEW] [AuthScreens.kt](file:///C:/Users/ASUS/AndroidStudioProjects/NBImplementation/app/src/main/java/com/example/nbimplementation/ui/screens/AuthScreens.kt)
Implementasi Halaman Login & Register.

#### [NEW] [MainScreens.kt](file:///C:/Users/ASUS/AndroidStudioProjects/NBImplementation/app/src/main/java/com/example/nbimplementation/ui/screens/MainScreens.kt)
Implementasi Dashboard (Home), Katalog Produk, dan Profile.

#### [NEW] [ProductScreens.kt](file:///C:/Users/ASUS/AndroidStudioProjects/NBImplementation/app/src/main/java/com/example/nbimplementation/ui/screens/ProductScreens.kt)
Implementasi Detail Barang.

#### [NEW] [TransactionScreens.kt](file:///C:/Users/ASUS/AndroidStudioProjects/NBImplementation/app/src/main/java/com/example/nbimplementation/ui/screens/TransactionScreens.kt)
Implementasi Keranjang (Cart), Checkout, dan History.

---

### Integration

#### [NEW] [MainActivity.kt](file:///C:/Users/ASUS/AndroidStudioProjects/NBImplementation/app/src/main/java/com/example/nbimplementation/MainActivity.kt)
EntryPoint aplikasi menggunakan Compose.

#### [DELETE] [MainActivity.java](file:///C:/Users/ASUS/AndroidStudioProjects/NBImplementation/app/src/main/java/com/example/nbimplementation/MainActivity.java)
Dihapus setelah migrasi ke Kotlin.

## Verification Plan

### Manual Verification
- Menjalankan aplikasi di emulator.
- Verifikasi visual setiap layar (warna, spacing, scannability).
- Verifikasi navigasi antar layar.
- Menguji responsivitas komponen (Grid & List).
