/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Diecast;
import model.HotWheels;
import model.MiniGT;
/**
 *
 * @author ACER
 */
public class DiecastDAO {

    public List<Diecast> getAllData() {
        List<Diecast> list = new ArrayList<>();
        String sql = "SELECT * FROM koleksi_diecast";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("id_koleksi");
                String jenis = rs.getString("jenis_diecast");
                String nama = rs.getString("nama_model");
                int tahun = rs.getInt("tahun_rilis");

                if (jenis.equalsIgnoreCase("HotWheels")) {
                    boolean isTh = rs.getBoolean("is_treasure_hunt");
                    list.add(new HotWheels(id, nama, tahun, isTh));
                } else if (jenis.equalsIgnoreCase("MiniGT")) {
                    int noBox = rs.getInt("nomor_box");
                    list.add(new MiniGT(id, nama, tahun, noBox));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error Ambil Data: " + e.getMessage());
        }
        return list;
    }

    public boolean tambahData(Diecast d) {
        String sql = "INSERT INTO koleksi_diecast (id_koleksi, jenis_diecast, nama_model, tahun_rilis, is_treasure_hunt, nomor_box) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, d.getIdKoleksi());
            ps.setString(3, d.getNamaModel());
            ps.setInt(4, d.getTahunRilis());

            if (d instanceof HotWheels) {
                HotWheels hw = (HotWheels) d;
                ps.setString(2, "HotWheels");
                ps.setBoolean(5, hw.isTreasureHunt());
                ps.setInt(6, 0);
            } else if (d instanceof MiniGT) {
                MiniGT mg = (MiniGT) d;
                ps.setString(2, "MiniGT");
                ps.setBoolean(5, false);
                ps.setInt(6, mg.getNomorBox());
            }

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error Tambah Data: " + e.getMessage());
            return false;
        }
    }

    public boolean ubahData(String id, String namaBaru, int tahunBaru) {
        String sql = "UPDATE koleksi_diecast SET nama_model = ?, tahun_rilis = ? WHERE id_koleksi = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, namaBaru);
            ps.setInt(2, tahunBaru);
            ps.setString(3, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error Ubah Data: " + e.getMessage());
            return false;
        }
    }

    public boolean hapusData(String id) {
        String sql = "DELETE FROM koleksi_diecast WHERE id_koleksi = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error Hapus Data: " + e.getMessage());
            return false;
        }
    }
}
