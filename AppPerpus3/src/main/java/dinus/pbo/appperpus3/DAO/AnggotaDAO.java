package dinus.pbo.appperpus3.DAO;

import dinus.pbo.appperpus3.models.Anggota;
import dinus.pbo.appperpus3.models.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnggotaDAO {

    public static ObservableList<Anggota> getDataAnggota() {
        ObservableList<Anggota> listAnggota = FXCollections.observableArrayList();
        Connection conn = DBConnection.getConn();
        String sql = "SELECT * FROM anggota";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int idAnggota = rs.getInt("idanggota");
                String nama = rs.getString("nama");
                String alamat = rs.getString("alamat");
                Anggota anggota = new Anggota(idAnggota, nama, alamat);
                listAnggota.add(anggota);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listAnggota;
    }

    public static void addAnggota(Anggota anggota) throws SQLException {
        Connection conn = DBConnection.getConn();
        String sql = "INSERT INTO anggota(idanggota, nama, alamat) VALUES (?, ?, ?)";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, anggota.getIdAnggota());
        st.setString(2, anggota.getNama());
        st.setString(3, anggota.getAlamat());
        st.executeUpdate();
    }

    public static void updateAnggota(Anggota anggota) throws SQLException {
        Connection conn = DBConnection.getConn();
        String sql = "UPDATE anggota SET nama = ?, alamat = ? WHERE idanggota = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, anggota.getNama());
        st.setString(2, anggota.getAlamat());
        st.setInt(3, anggota.getIdAnggota());
        st.executeUpdate();
    }

    public static void deleteAnggota(int idAnggota) throws SQLException {
        Connection conn = DBConnection.getConn();
        String sql = "DELETE FROM anggota WHERE idanggota = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, idAnggota);
        st.executeUpdate();
    }
}
