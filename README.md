# Minpro-4-PBO-SistemManajemenKoleksiDiecast

## Deskripsi Singkat Program
Program ini adalah aplikasi Java berbasis CLI (Command Line Interface) untuk mengelola koleksi diecast, yaitu miniatur kendaraan. Versi Mini Project 4 ini mengembangkan proyek Minpro 3 dengan mengintegrasikan sistem secara penuh ke database **MySQL** (`db_diecast`) menggunakan driver **JDBC**, **PreparedStatement**, serta arsitektur **DAO (Data Access Object)** murni tanpa ORM framework. Seluruh operasi CRUD (Create, Read, Update, Delete) tersimpan secara permanen di dalam basis data. Program tetap mempertahankan arsitektur **MVC**, penerapan **Abstraction**, **Polymorphism**, serta **Interface** (`Pajangable`).


## Penjelasan Perubahan Struktur (Dari Minpro 3 ke Minpro 4)
Pengembangan dari Mini Project 3 ke Mini Project 4 mencakup beberapa perubahan struktur sesuai modul praktikum:
1. **Integrasi Basis Data MySQL & File `db_diecast.sql`:** Data koleksi yang sebelumnya disimpan sementara di dalam `ArrayList` memori kini disimpan secara permanen di tabel `koleksi_diecast` pada database `db_diecast`. File skrip SQL (`db_diecast.sql`) disertakan di root proyek sebagai instrumen pembuatan database dan dummy data awal.
2. **Penerapan Package `config` (`DatabaseConnection.java`):** Memisahkan manajemen koneksi basis data menggunakan JDBC `DriverManager` dengan konfigurasi driver MySQL (`com.mysql.cj.jdbc.Driver`).
3. **Penerapan Package `dao` (`DiecastDAO.java`):** Menerapkan pola Data Access Object untuk mengisolasi seluruh logika kueri SQL. Semua operasi database menggunakan `PreparedStatement` untuk keamanan kueri dari ancaman SQL Injection.
4. **Pembaruan Layer Controller (`KoleksiController.java`):** Mengganti manipulasi `ArrayList` lokal menjadi pemanggilan metode-metode eksekusi database pada `DiecastDAO`.
5. **Penyelarasan Tipe Data & Polimorfisme:** Hasil query dari database dikonversi secara manual (mapping) oleh DAO menjadi objek entitas polimorfik (`HotWheels` atau `MiniGT`) dan dikembalikan dalam bentuk `List<Diecast>`.


## Penjelasan Alur Program

<img width="382" height="163" alt="image" src="https://github.com/user-attachments/assets/020f870d-3678-45e1-86e4-7ee9baf47a6b" />

Saat program dijalankan, sistem secara otomatis menghubungkan aplikasi ke MySQL Laragon via JDBC dan membaca data awal pada database melalui `DiecastDAO`.

**1. Tampilkan Semua Koleksi**

<img width="682" height="83" alt="image" src="https://github.com/user-attachments/assets/3bf23f92-8753-450c-bc98-d501d4849ad3" />


Program memanggil method `getAllData()` pada `DiecastDAO` untuk mengeksekusi kueri `SELECT * FROM koleksi_diecast` dan menampilkan daftar seluruh diecast beserta atribut khususnya. Jika database masih kosong, program menampilkan pesan bahwa data koleksi belum ada.

**2. Cari Data Koleksi**

<img width="649" height="109" alt="image" src="https://github.com/user-attachments/assets/7e2bfc86-c18e-4f75-8628-b2853e23aff9" />


Masukkan ID Koleksi atau kata kunci nama model. Program melakukan pencarian pada daftar data yang diambil dari database dan menampilkan hasilnya. Jika tidak ada yang cocok, program menampilkan pesan "Data tidak ditemukan."

**3. Tambah Hot Wheels & 4. Tambah Mini GT**

<img width="292" height="134" alt="image" src="https://github.com/user-attachments/assets/1983098b-02aa-48be-822f-c7b14c33f63b" />


Masukkan ID, Nama Model, dan Tahun Rilis.
- Pada menu 3 (Hot Wheels), program meminta input tambahan: apakah diecast tersebut tipe Treasure Hunt (y/n).
- Pada menu 4 (Mini GT), program meminta input Nomor Box (angka).

Setelah semua data diisi, program mengeksekusi kueri `INSERT INTO` melalui `PreparedStatement` di `DiecastDAO` untuk menyimpan data secara permanen ke MySQL.

**5. Ubah Data Koleksi**

<img width="690" height="161" alt="image" src="https://github.com/user-attachments/assets/0322f1af-35bd-4dde-81f5-8e34d60a8b0b" />


Program menampilkan daftar koleksi dari database. Masukkan ID diecast yang ingin diubah, lalu masukkan Nama Model dan Tahun Rilis yang baru. Program mengeksekusi kueri `UPDATE koleksi_diecast` di `DiecastDAO`.

**6. Hapus Koleksi**

<img width="683" height="129" alt="image" src="https://github.com/user-attachments/assets/0c501c61-362f-4472-abd9-e85f5b93e837" />


Program menampilkan daftar koleksi. Masukkan ID diecast yang akan dihapus, lalu `DiecastDAO` mengeksekusi kueri `DELETE FROM koleksi_diecast`. Jika ID tidak ditemukan, program menampilkan pesan kesalahan.

**7. Simulasi Pajang & Cek Kondisi (Interface)**

<img width="621" height="166" alt="image" src="https://github.com/user-attachments/assets/bdf69826-c9ce-4fd5-9955-8bf1f91d22a2" />


Program menampilkan daftar koleksi dari database. Masukkan ID diecast yang ingin dipajang, lalu masukkan tujuan pemajangan (misalnya "Dijual", "Dikoleksi", atau "Disimpan"). Program mengecek antarmuka `Pajangable` dengan `instanceof` lalu memanggil method `pajang()` dan `cekKondisi()`.

**8. Keluar**

<img width="294" height="37" alt="image" src="https://github.com/user-attachments/assets/ee8ef150-4404-498f-9628-7ef77b55f2d5" />

Pilih angka 8 di menu utama. Program berhenti dan menampilkan pesan "Terima kasih, program selesai."


## Penjelasan Penerapan Encapsulation dan Inheritance

**A. Encapsulation (Pembungkusan Data):**
Atribut di `Diecast.java`, `HotWheels.java`, dan `MiniGT.java` menggunakan access modifier `protected` atau `private`. Dengan begitu, data tidak bisa diubah sembarangan dari luar. Data hanya bisa dibaca dan diubah melalui method Getter dan Setter.

**B. Inheritance (Pewarisan):**
Program memiliki satu superclass dan dua subclass:
- **Superclass (`Diecast`):** Menyimpan atribut dasar yang dimiliki semua diecast, yaitu ID Koleksi, Nama Model, dan Tahun Rilis.
- **Subclass (`HotWheels` & `MiniGT`):** Mewarisi atribut induk melalui keyword `extends Diecast`, lalu menambahkan atribut khusus: `isTreasureHunt` pada Hot Wheels dan `nomorBox` pada Mini GT. Constructor subclass wajib memanggil `super()` untuk meneruskan data ke kelas induk.


## Penjelasan Penerapan Abstraction dan Polymorphism

<img width="610" height="141" alt="image" src="https://github.com/user-attachments/assets/99c2efc6-c442-4419-887d-f5b84c999abb" />

**A. Abstraction (Abstraksi):**
- **Abstract Class:** `Diecast` dideklarasikan sebagai `public abstract class Diecast` sehingga tidak dapat diinstansiasi secara langsung.
- **Abstract Method:** `public abstract void tampilkanDetail();` tidak memiliki body di kelas induk. Method ini memaksa `HotWheels` dan `MiniGT` menyediakan implementasi sendiri untuk menampilkan detail masing-masing.

**B. Polymorphism (Polimorfisme):**
- **Method Overriding (Dynamic Polymorphism):** Diterapkan pada `tampilkanDetail()`, `pajang()`, dan `cekKondisi()`. Method dideklarasikan di superclass atau interface, lalu di-override (`@Override`) di `HotWheels` dan `MiniGT`.
- **Method Overloading (Static Polymorphism):** Diterapkan di `Diecast` lewat dua method dengan nama sama tetapi parameter berbeda: `rawatKoleksi()` untuk pembersihan dasar dan `rawatKoleksi(String cairan)` untuk pemolesan dengan cairan tertentu.
- **Polymorphic Collection:** Method `getAllData()` pada `DiecastDAO` mengembalikan `List<Diecast>`. List ini secara fleksibel menampung objek instansiasi `HotWheels` dan `MiniGT`.


## Penjelasan Penerapan JDBC, PreparedStatement, dan DAO Pattern

<img width="724" height="395" alt="image" src="https://github.com/user-attachments/assets/86bcafab-0ec9-480e-8dc8-a18354f06d45" />
<img width="840" height="286" alt="image" src="https://github.com/user-attachments/assets/018a70cd-f109-44c8-945d-86c0bb863c35" />
<img width="720" height="407" alt="image" src="https://github.com/user-attachments/assets/8473abee-6096-4852-9409-0877e2cdd43f" />
<img width="278" height="199" alt="image" src="https://github.com/user-attachments/assets/f47a0db3-df8e-421c-82c9-cb65eeabdaaf" />



1. **Penggunaan JDBC (`DatabaseConnection.java`):** Koneksi basis data dikelola terpusat dengan URL `jdbc:mysql://localhost:3306/db_diecast`, user `root`, dan tanpa password (default Laragon).
2. **Penerapan PreparedStatement (`DiecastDAO.java`):** Seluruh perintah SQL menggunakan `PreparedStatement` dengan parameter placeholder (`?`). Hal ini berguna untuk menghindari SQL Injection serta mempermudah pengisian parameter dinamis.
3. **Pola DAO (Data Access Object):** Memisahkan seluruh logika manipulasi SQL dari kelas Controller. Controller cukup memanggil metode `getAllData()`, `tambahData()`, `ubahData()`, dan `hapusData()`.
4. **Pengelolaan File `.sql` (`db_diecast.sql`):** Menyediakan skrip SQL di dalam repositori untuk mempermudah inisialisasi skema tabel `koleksi_diecast` beserta data awal secara otomatis.


## Penjelasan Letak Penerapan Nilai Tambah

**A. Menerapkan Arsitektur MVC yang Terintegrasi Layer Database**

<img width="357" height="219" alt="image" src="https://github.com/user-attachments/assets/c2432563-f92a-404e-b647-1e479404a63f" />

Program memisahkan logika, antarmuka, dan akses data ke dalam package yang terstruktur:
- **`package main`:** Berisi `Main.java` sebagai titik masuk (entry point) aplikasi.
- **`package config`:** Berisi `DatabaseConnection.java` untuk mengelola koneksi JDBC ke MySQL.
- **`package dao`:** Berisi `DiecastDAO.java` tempat pemrosesan query SQL berbasis `PreparedStatement`.
- **`package model`:** Berisi `Diecast.java`, `HotWheels.java`, `MiniGT.java`, dan `Pajangable.java` yang menangani entitas data dan interface.
- **`package view`:** Berisi `KoleksiView.java` yang menangani pencetakan menu dan pesan ke konsol.
- **`package controller`:** Berisi `KoleksiController.java` yang mengatur alur aplikasi dan menjembatani View dengan `DiecastDAO`.

**B. Interface (`Pajangable.java`) dan Operator `instanceof`**

<img width="663" height="368" alt="image" src="https://github.com/user-attachments/assets/6d9947ff-d257-4d56-b366-6b84a908f853" />

- **Interface `Pajangable`:** Berada di `package model` dan memuat kontrak method `pajang(String tujuan)` dan `cekKondisi()`. Class `HotWheels` dan `MiniGT` mengimplementasikannya dengan kata kunci `implements`.
- **Downcasting dengan `instanceof`:** Pada method `simulasiPajang()` di `KoleksiController.java`, program mengecek `if (d instanceof Pajangable)` terlebih dahulu sebelum melakukan casting objek `(Pajangable) d`.
