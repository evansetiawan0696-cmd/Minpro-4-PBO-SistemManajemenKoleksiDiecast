/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public abstract class Diecast {
    protected String idKoleksi;
    protected String namaModel;
    protected int tahunRilis;

    public Diecast(String idKoleksi, String namaModel, int tahunRilis) {
        this.idKoleksi = idKoleksi;
        this.namaModel = namaModel;
        this.tahunRilis = tahunRilis;
    }

    public String getIdKoleksi() { return idKoleksi; }
    public void setIdKoleksi(String idKoleksi) { this.idKoleksi = idKoleksi; }

    public String getNamaModel() { return namaModel; }
    public void setNamaModel(String namaModel) { this.namaModel = namaModel; }

    public int getTahunRilis() { return tahunRilis; }
    public void setTahunRilis(int tahunRilis) { this.tahunRilis = tahunRilis; }

    public abstract void tampilkanDetail();

    public void rawatKoleksi() {
        System.out.println("Koleksi " + namaModel + " berhasil dibersihkan dari debu.");
    }

    public void rawatKoleksi(String cairan) {
        System.out.println("Koleksi " + namaModel + " dipoles menggunakan " + cairan + ".");
    }
}
