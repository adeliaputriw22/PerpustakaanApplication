package dinus.pbo.appperpus3.DAO;

import dinus.pbo.appperpus3.models.Buku;
import dinus.pbo.appperpus3.models.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BukuDAO {

    public static ObservableList<Buku> getAllBuku() {
        ObservableList<Buku> listBuku = FXCollections.observableArrayList();
        String sql = "SELECT * FROM buku";
        try (Connection conn = DBConnection.getConn();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                listBuku.add(new Buku(
                        rs.getInt("idbuku"),
                        rs.getString("judul"),
                        rs.getString("penerbit"),
                        rs.getString("penulis"),
                        rs.getInt("tahun_terbit")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listBuku;
    }

    public static void addBuku(Buku buku) {
        String sql = "INSERT INTO buku(idbuku, judul, penerbit, penulis, tahun_terbit) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConn();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, buku.getIdBuku());
            statement.setString(2, buku.getJudul());
            statement.setString(3, buku.getPenerbit());
            statement.setString(4, buku.getPenulis());
            statement.setInt(5, buku.getTahun_terbit());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateBuku(Buku buku) {
        String sql = "UPDATE buku SET judul = ?, penerbit = ?, penulis = ?, tahun_terbit = ? WHERE idbuku = ?";
        try (Connection conn = DBConnection.getConn();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, buku.getJudul());
            statement.setString(2, buku.getPenerbit());
            statement.setString(3, buku.getPenulis());
            statement.setInt(4, buku.getTahun_terbit());
            statement.setInt(5, buku.getIdBuku());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteBuku(int idBuku) {
        String sql = "DELETE FROM buku WHERE idbuku = ?";
        try (Connection conn = DBConnection.getConn();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, idBuku);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
