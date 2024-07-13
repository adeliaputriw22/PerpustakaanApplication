package dinus.pbo.appperpus3.controllers;

import dinus.pbo.appperpus3.DAO.BukuDAO;
import dinus.pbo.appperpus3.models.Buku;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class BukuController implements Initializable {
    Stage stage;
    ObservableList<Buku> listBuku = BukuDAO.getAllBuku();
    boolean flagAdd = true;

    @FXML
    private TableColumn<Buku, Integer> cidBuku;
    @FXML
    private TableColumn<Buku, String> cjudul;
    @FXML
    private TableColumn<Buku, String> cpenerbit;
    @FXML
    private TableColumn<Buku, String> cpenulis;
    @FXML
    private TableColumn<Buku, Integer> ctahun_terbit;
    @FXML
    private TableView<Buku> tbBuku;
    @FXML
    private Button bAdd;
    @FXML
    private Button bCancel;
    @FXML
    private Button bDel;
    @FXML
    private Button bEdit;
    @FXML
    private Button bUpdate;
    @FXML
    private TextField tfidBuku;
    @FXML
    private TextField tfjudul;
    @FXML
    private TextField tfpenerbit;
    @FXML
    private TextField tfpenulis;
    @FXML
    private TextField tftahun_terbit;
    @FXML
    private TextField tfsearch;

    @FXML
    void add(ActionEvent event) {
        setButton(false,false,false,true,true);
        clearTeks();
        setTeks(true);
        tfidBuku.requestFocus();
    }

    @FXML
    void cancel(ActionEvent event) {
        setButton(true,true,true,false,false);
        clearTeks();
    }

    @FXML
    void del(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Hapus data?", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            BukuDAO.deleteBuku(Integer.parseInt(tfidBuku.getText()));
            loadData();
            clearTeks();
        }
    }

    @FXML
    void edit(ActionEvent event) {
        flagAdd = false;
        setButton(false,false,false,true,true);
        setTeks(true);
        tfidBuku.setEditable(false);
        tfjudul.requestFocus();
    }

    @FXML
    void update(ActionEvent event) {
        if (flagAdd) {
            Buku buku = new Buku(
                    Integer.parseInt(tfidBuku.getText()),
                    tfjudul.getText(),
                    tfpenerbit.getText(),
                    tfpenulis.getText(),
                    Integer.parseInt(tftahun_terbit.getText())
            );
            BukuDAO.addBuku(buku);
        } else {
            Buku buku = new Buku(
                    Integer.parseInt(tfidBuku.getText()),
                    tfjudul.getText(),
                    tfpenerbit.getText(),
                    tfpenulis.getText(),
                    Integer.parseInt(tftahun_terbit.getText())
            );
            BukuDAO.updateBuku(buku);
        }
        loadData();
        setButton(true,true,true,false,false);
        clearTeks();
    }

    @FXML
    void pilihProduk(MouseEvent event) {
        Buku buku = tbBuku.getSelectionModel().getSelectedItem();
        tfidBuku.setText(String.valueOf(buku.getIdBuku()));
        tfjudul.setText(buku.getJudul());
        tfpenerbit.setText(buku.getPenerbit());
        tfpenulis.setText(buku.getPenulis());
        tftahun_terbit.setText(String.valueOf(buku.getTahun_terbit()));
    }

    void initTabel() {
        cidBuku.setCellValueFactory(new PropertyValueFactory<>("idBuku"));
        cjudul.setCellValueFactory(new PropertyValueFactory<>("judul"));
        cpenerbit.setCellValueFactory(new PropertyValueFactory<>("penerbit"));
        cpenulis.setCellValueFactory(new PropertyValueFactory<>("penulis"));
        ctahun_terbit.setCellValueFactory(new PropertyValueFactory<>("tahun_terbit"));
    }

    void loadData() {
        listBuku = BukuDAO.getAllBuku();
        tbBuku.setItems(listBuku);
    }

    void setFilter() {
        FilteredList<Buku> filterData = new FilteredList<>(listBuku, b -> true);
        tfsearch.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Pencarian: " + newValue); // Debugging
            filterData.setPredicate(buku -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (buku.getJudul().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (buku.getPenulis().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (String.valueOf(buku.getIdBuku()).contains(searchKeyword)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Buku> sortedData = new SortedList<>(filterData);
        sortedData.comparatorProperty().bind(tbBuku.comparatorProperty());
        tbBuku.setItems(sortedData);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTabel();
        loadData();
        setFilter();
        setButton(true,true,true,false,false);
        setTeks(false);
    }

    protected void clearTeks() {
        tfidBuku.setText(null);
        tfjudul.setText(null);
        tfpenerbit.setText(null);
        tfpenulis.setText(null);
        tftahun_terbit.setText(null);
    }

    protected void setButton(Boolean bAdd, Boolean bDel, Boolean bEdit, Boolean bUpdate, Boolean bCancel) {
        this.bAdd.setDisable(!bAdd);
        this.bDel.setDisable(!bDel);
        this.bEdit.setDisable(!bEdit);
        this.bUpdate.setDisable(!bUpdate);
        this.bCancel.setDisable(!bCancel);
    }

    protected void setTeks(Boolean enabled) {
        tfidBuku.setEditable(enabled);
        tfjudul.setEditable(enabled);
        tfpenerbit.setEditable(enabled);
        tfpenulis.setEditable(enabled);
        tftahun_terbit.setEditable(enabled);
    }
}
