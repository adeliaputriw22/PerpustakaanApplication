package dinus.pbo.appperpus3.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuController {
    @FXML
    private Button btnHome;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnBuku;
    @FXML
    private Button btnPustakawan;
    @FXML
    private Button btnAnggota;
    @FXML
    private Button btnPeminjaman;
    @FXML
    private Button btnTransaksi;

    // private Button btnKoleksi;
    @FXML
    void home(ActionEvent event) {
        try {
            homee();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }@FXML
    private Button btnselesai;
    @FXML
    void p1(ActionEvent event) {
    }
    @FXML
    void p2(ActionEvent event) {
    }
    @FXML
    void p3(ActionEvent event) {
    }@FXML
    private StackPane contentArea;
    @FXML
    private Label lbTeks;
    public void initialize(URL location,ResourceBundle resources) {
        try {
            homee();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }@FXML
    public void selesai() {
        System.exit(0);
    }@FXML
    public void homee() throws IOException {
        Parent fxml =
                FXMLLoader.load(getClass().getResource("/fxml/fhome.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }@FXML
    public void buku() throws IOException {
        Parent fxml =
                FXMLLoader.load(getClass().getResource("/fxml/fbuku.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }@FXML
    public void pustakawan() throws IOException {
        Parent fxml =
                FXMLLoader.load(getClass().getResource("/fxml/fpustakawan.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }@FXML
    public void anggota() throws IOException {
        Parent fxml =
                FXMLLoader.load(getClass().getResource("/fxml/fanggota.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    /* @FXML
    public void peminjaman() throws IOException {
        Parent fxml =
                FXMLLoader.load(getClass().getResource("/fxml/fpeminjaman.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    } */

    /*@FXML
    void StrukPeminjaman(String idBuku, String judul, String penulis, String tanggalPinjam, String tanggalJatuhTempo, String namaAnggota, String alamatAnggota) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fstruk_peminjaman.fxml"));
            Parent root = loader.load();
            StrukPeminjamanController controller = loader.getController();
            controller.setData(idBuku, judul, penulis, tanggalPinjam, tanggalJatuhTempo, namaAnggota, alamatAnggota);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Struk Peminjaman Buku");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /* @FXML
    public void Koleksi() throws IOException {
        Parent fxml =
                FXMLLoader.load(getClass().getResource("/fxml/fkoleksi_buku.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    } */
    @FXML
    public void transaksi() throws IOException {
        Parent fxml =
                FXMLLoader.load(getClass().getResource("/fxml/ftransaksi.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    /* public void transaksi() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ftransaksi.fxml"));
            Parent fxml = loader.load();
            contentArea.getChildren().clear();  // Menghapus semua elemen anak di contentArea
            contentArea.getChildren().add(fxml);  // Menambahkan elemen fxml ke contentArea
        } catch (IOException e) {
            e.printStackTrace();  // Menampilkan stack trace untuk debugging
        }
    } */

    public void setUserInfo(String userName) {
        lbTeks.setText("Selamat Datang ....." + userName);
    }
}