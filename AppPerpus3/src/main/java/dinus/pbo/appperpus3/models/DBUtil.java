package dinus.pbo.appperpus3.models;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.Instant;

public class DBUtil {
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String userName) {
        Parent root = null;
        if (userName != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtil.class.getResource(fxmlFile));
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DBUtil.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 1100, 800));
        stage.show();
    }

    public static ObservableList<Buku> getDataBuku() {
        ObservableList<Buku> listBuku = FXCollections.observableArrayList();
        try {
            Connection c = DBConnection.getConn();
            String sql = "select * from buku";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Buku m = new Buku(rs.getInt("idBuku"), rs.getString("judul"), rs.getString("penerbit"), rs.getString("penulis"), rs.getInt("tahun_terbit"));
                listBuku.add(m);
            }
            return listBuku;
        } catch (SQLException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static ObservableList<Pustakawan> getDataPustakawan() {
        ObservableList<Pustakawan> listPustakawan = FXCollections.observableArrayList();
        try {
            Connection c = DBConnection.getConn();
            String sql = "select * from pustakawan";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pustakawan p = new Pustakawan(rs.getInt("idPustakawan"), rs.getString("nama"), rs.getString("email"));
                listPustakawan.add(p);
            }
            return listPustakawan;
        } catch (SQLException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static ObservableList<Anggota> getDataAnggota() {
        ObservableList<Anggota> listAnggota = FXCollections.observableArrayList();
        try {
            Connection c = DBConnection.getConn();
            String sql = "select * from anggota";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Anggota a = new Anggota(rs.getInt("idAnggota"), rs.getString("nama"), rs.getString("alamat"));
                listAnggota.add(a);
            }
            return listAnggota;
        } catch (SQLException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static ObservableList<Transaksi> getDataTransaksi() {
        ObservableList<Transaksi> listTransaksi = FXCollections.observableArrayList();
        try {
            Connection c = DBConnection.getConn();
            String sql = "select * from transaksi";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Transaksi a = new Transaksi(rs.getInt("idtransaksi"), rs.getInt("idbuku"), rs.getInt("idanggota"), rs.getDate("tanggalPinjam").toLocalDate(), rs.getDate("tanggalJatuhTempo").toLocalDate(), rs.getDate("tanggalKembali").toLocalDate(), rs.getString("status"));
                listTransaksi.add(a);
            }
            return listTransaksi;
        } catch (SQLException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
