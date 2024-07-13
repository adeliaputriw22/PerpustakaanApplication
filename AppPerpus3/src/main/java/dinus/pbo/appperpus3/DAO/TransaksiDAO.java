package dinus.pbo.appperpus3.DAO;

import dinus.pbo.appperpus3.models.DBConnection;
import dinus.pbo.appperpus3.models.Transaksi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class TransaksiDAO {
    public static ObservableList<Transaksi> getAllTransaksi() {
        ObservableList<Transaksi> transaksiList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM transaksi";

        try (Connection conn = DBConnection.getConn();
             PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                transaksiList.add(new Transaksi(
                        rs.getInt("idTransaksi"),
                        rs.getInt("idBuku"),
                        rs.getInt("idAnggota"),
                        rs.getDate("tanggalPinjam").toLocalDate(),
                        rs.getDate("tanggalJatuhTempo").toLocalDate(),
                        rs.getDate("tanggalKembali") != null ? rs.getDate("tanggalKembali").toLocalDate() : null,
                        rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transaksiList;
    }

    public static void insertTransaksi(Transaksi transaksi) {
        String sql = "INSERT INTO transaksi (idTransaksi, idBuku, idAnggota, tanggalPinjam, tanggalJatuhTempo, tanggalKembali, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConn();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, transaksi.getIdTransaksi());
            st.setInt(2, transaksi.getIdBuku());
            st.setInt(3, transaksi.getIdAnggota());
            st.setDate(4, Date.valueOf(transaksi.getTanggalPinjam()));
            st.setDate(5, Date.valueOf(transaksi.getTanggalJatuhTempo()));

            if (transaksi.getTanggalKembali() != null) {
                st.setDate(6, Date.valueOf(transaksi.getTanggalKembali()));
            } else {
                st.setNull(6, Types.DATE);
            }

            st.setString(7, transaksi.getStatus());
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateTransaksi(Transaksi transaksi) {
        String sql = "UPDATE transaksi SET idBuku = ?, idAnggota = ?, tanggalPinjam = ?, tanggalJatuhTempo = ?, tanggalKembali = ?, status = ? WHERE idTransaksi = ?";

        try (Connection conn = DBConnection.getConn();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, transaksi.getIdBuku());
            st.setInt(2, transaksi.getIdAnggota());
            st.setDate(3, Date.valueOf(transaksi.getTanggalPinjam()));
            st.setDate(4, Date.valueOf(transaksi.getTanggalJatuhTempo()));

            if (transaksi.getTanggalKembali() != null) {
                st.setDate(5, Date.valueOf(transaksi.getTanggalKembali()));
            } else {
                st.setNull(5, Types.DATE);
            }

            st.setString(6, transaksi.getStatus());
            st.setInt(7, transaksi.getIdTransaksi());
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteTransaksi(int idTransaksi) {
        String sql = "DELETE FROM transaksi WHERE idTransaksi = ?";

        try (Connection conn = DBConnection.getConn();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, idTransaksi);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
