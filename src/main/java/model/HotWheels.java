/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public class HotWheels extends Diecast implements Pajangable {
    private boolean isTreasureHunt;

    public HotWheels(String idKoleksi, String namaModel, int tahunRilis, boolean isTreasureHunt) {
        super(idKoleksi, namaModel, tahunRilis);
        this.isTreasureHunt = isTreasureHunt;
    }

    public boolean isTreasureHunt() { return isTreasureHunt; }
    public void setTreasureHunt(boolean isTreasureHunt) { this.isTreasureHunt = isTreasureHunt; }

    @Override
    public void tampilkanDetail() {
        String th = isTreasureHunt ? "Ya" : "Tidak";
        System.out.println("Hot Wheels | ID: " + idKoleksi + " | Model: " + namaModel + 
                           " | Tahun: " + tahunRilis + " | Treasure Hunt: " + th);
    }

    @Override
    public void pajang(String tujuan) {
        System.out.println("Hot Wheels " + namaModel + " dipajang di etalase untuk: " + tujuan);
    }

    @Override
    public void cekKondisi() {
        System.out.println("Kondisi Hot Wheels " + namaModel + ": Cardboard & blister mulus (95%).");
    }
}
