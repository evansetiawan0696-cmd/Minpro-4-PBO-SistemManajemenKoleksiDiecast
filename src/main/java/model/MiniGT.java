/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public class MiniGT extends Diecast implements Pajangable {
    private int nomorBox;

    public MiniGT(String idKoleksi, String namaModel, int tahunRilis, int nomorBox) {
        super(idKoleksi, namaModel, tahunRilis);
        this.nomorBox = nomorBox;
    }

    public int getNomorBox() { return nomorBox; }
    public void setNomorBox(int nomorBox) { this.nomorBox = nomorBox; }

    @Override
    public void tampilkanDetail() {
        System.out.println("Mini GT     | ID: " + idKoleksi + " | Model: " + namaModel + 
                           " | Tahun: " + tahunRilis + " | No Box: #" + nomorBox);
    }

    @Override
    public void pajang(String tujuan) {
        System.out.println("Mini GT " + namaModel + " dipajang dengan Box #" + nomorBox + " untuk: " + tujuan);
    }

    @Override
    public void cekKondisi() {
        System.out.println("Kondisi Mini GT " + namaModel + ": Cat mulus, spion utuh, box rapi.");
    }
}
