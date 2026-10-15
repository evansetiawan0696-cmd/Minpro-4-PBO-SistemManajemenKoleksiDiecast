/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author ACER
 */
public class KoleksiView {
    public void tampilkanMenu() {
        System.out.println("\n=== SISTEM MANAJEMEN KOLEKSI DIECAST ===");
        System.out.println("1. Tampilkan Semua Koleksi");
        System.out.println("2. Cari Koleksi");
        System.out.println("3. Tambah Hot Wheels");
        System.out.println("4. Tambah Mini GT");
        System.out.println("5. Ubah Data Koleksi");
        System.out.println("6. Hapus Koleksi");
        System.out.println("7. Simulasi Pajang & Cek Kondisi (Interface)");
        System.out.println("8. Keluar");
        System.out.print("Pilih menu: ");
    }

    public void tampilkanPesan(String pesan) {
        System.out.println(pesan);
    }
}
