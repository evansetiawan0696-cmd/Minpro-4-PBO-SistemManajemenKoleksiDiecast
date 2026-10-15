/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author ACER
 */
import dao.DiecastDAO;
import java.util.List;
import java.util.Scanner;
import model.Diecast;
import model.HotWheels;
import model.MiniGT;
import model.Pajangable;
import view.KoleksiView;

public class KoleksiController {
    private DiecastDAO dao; 
    private KoleksiView view;
    private Scanner input;

    public KoleksiController(KoleksiView view) {
        this.dao = new DiecastDAO(); 
        this.view = view;
        this.input = new Scanner(System.in);

    }

    public void jalankan() {
        int pilih = 0;
        do {
            view.tampilkanMenu();
            try {
                pilih = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                view.tampilkanPesan("Input harus berupa angka!");
                continue;
            }

            switch (pilih) {
                case 1: tampilkanSemua(); break;
                case 2: cariData(); break;
                case 3: tambahHotWheels(); break;
                case 4: tambahMiniGT(); break;
                case 5: ubahData(); break;
                case 6: hapusData(); break;
                case 7: simulasiPajang(); break;
                case 8: view.tampilkanPesan("Terima kasih, program selesai."); break;
                default: view.tampilkanPesan("Pilihan menu tidak ada!");
            }
        } while (pilih != 8);
    }

    private void tampilkanSemua() {
        List<Diecast> listKoleksi = dao.getAllData();
        if (listKoleksi.isEmpty()) {
            view.tampilkanPesan("Data koleksi masih kosong.");
            return;
        }
        System.out.println("\n--- Daftar Koleksi ---");
        for (Diecast d : listKoleksi) {
            d.tampilkanDetail();
        }
    }

    private void cariData() {
        List<Diecast> listKoleksi = dao.getAllData();
        if (listKoleksi.isEmpty()) {
            view.tampilkanPesan("Data koleksi masih kosong.");
            return;
        }
        System.out.print("Masukkan ID/Nama model: ");
        String cari = input.nextLine().toLowerCase();
        boolean ketemu = false;

        for (Diecast d : listKoleksi) {
            if (d.getIdKoleksi().toLowerCase().contains(cari) || 
                d.getNamaModel().toLowerCase().contains(cari)) {
                d.tampilkanDetail();
                ketemu = true;
            }
        }
        if (!ketemu) {
            view.tampilkanPesan("Data tidak ditemukan.");
        }
    }

    private void tambahHotWheels() {
        System.out.print("ID: "); String id = input.nextLine();
        System.out.print("Nama Model: "); String nama = input.nextLine();
        System.out.print("Tahun Rilis: "); int tahun = Integer.parseInt(input.nextLine());
        System.out.print("Treasure Hunt? (y/n): "); String thInput = input.nextLine();
        boolean th = thInput.equalsIgnoreCase("y");

        if (dao.tambahData(new HotWheels(id, nama, tahun, th))) {
            view.tampilkanPesan("Data Hot Wheels berhasil ditambahkan ke database!");
        } else {
            view.tampilkanPesan("Gagal menambahkan data.");
        }
    }

    private void tambahMiniGT() {
        System.out.print("ID: "); String id = input.nextLine();
        System.out.print("Nama Model: "); String nama = input.nextLine();
        System.out.print("Tahun Rilis: "); int tahun = Integer.parseInt(input.nextLine());
        System.out.print("Nomor Box: "); int noBox = Integer.parseInt(input.nextLine());

        if (dao.tambahData(new MiniGT(id, nama, tahun, noBox))) {
            view.tampilkanPesan("Data Mini GT berhasil ditambahkan ke database!");
        } else {
            view.tampilkanPesan("Gagal menambahkan data.");
        }
    }

    private void ubahData() {
        tampilkanSemua();
        System.out.print("Masukkan ID yang ingin diubah: ");
        String id = input.nextLine();

        System.out.print("Nama Model Baru: "); String namaBaru = input.nextLine();
        System.out.print("Tahun Rilis Baru: "); int tahunBaru = Integer.parseInt(input.nextLine());

        if (dao.ubahData(id, namaBaru, tahunBaru)) {
            view.tampilkanPesan("Data berhasil diubah di database!");
        } else {
            view.tampilkanPesan("ID tidak ditemukan atau gagal mengubah data.");
        }
    }

    private void hapusData() {
        tampilkanSemua();
        System.out.print("Masukkan ID yang ingin dihapus: ");
        String id = input.nextLine();

        if (dao.hapusData(id)) {
            view.tampilkanPesan("Data berhasil dihapus dari database!");
        } else {
            view.tampilkanPesan("ID tidak ditemukan atau gagal menghapus data.");
        }
    }

    private void simulasiPajang() {
        List<Diecast> listKoleksi = dao.getAllData();
        if (listKoleksi.isEmpty()) return;

        System.out.print("Masukkan ID Koleksi: ");
        String id = input.nextLine();

        for (Diecast d : listKoleksi) {
            if (d.getIdKoleksi().equalsIgnoreCase(id)) {
                if (d instanceof Pajangable) {
                    System.out.print("Tujuan Pajang (Dijual/Dikoleksi/Disimpan): ");
                    String tujuan = input.nextLine();

                    Pajangable p = (Pajangable) d;
                    p.pajang(tujuan);
                    p.cekKondisi();
                }
                return;
            }
        }
        view.tampilkanPesan("ID tidak ditemukan.");
    }
}
