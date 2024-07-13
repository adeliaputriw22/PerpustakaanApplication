package dinus.pbo.appperpus3.DAO;

import dinus.pbo.appperpus3.models.DBConnection;
import dinus.pbo.appperpus3.models.Pustakawan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PustakawanDAO {

    private Connection conn;

    public PustakawanDAO() {
        conn = DBConnection.getConn();
    }

    public ObservableList<Pustakawan> getAllPustakawan() {
        ObservableList<Pustakawan> pustakawanList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM pustakawan";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                pustakawanList.add(new Pustakawan(
                        rs.getInt("idpustakawan"),
                        rs.getString("nama"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pustakawanList;
    }

    public void addPustakawan(Pustakawan p) {
        String sql = "INSERT INTO pustakawan(idpustakawan, nama, email) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getIdPustakawan());
            ps.setString(2, p.getNama());
            ps.setString(3, p.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePustakawan(Pustakawan p) {
        String sql = "UPDATE pustakawan SET nama=?, email=? WHERE idpustakawan=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNama());
            ps.setString(2, p.getEmail());
            ps.setInt(3, p.getIdPustakawan());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePustakawan(int id) {
        String sql = "DELETE FROM pustakawan WHERE idpustakawan=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
