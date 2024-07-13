package dinus.pbo.appperpus3.controllers;

import dinus.pbo.appperpus3.DAO.TransaksiDAO;
import dinus.pbo.appperpus3.models.Transaksi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TransaksiController implements Initializable {
    @FXML
    private Button bAdd, bEdit, bDel, bUpdate, bCancel;
    @FXML
    private TableView<Transaksi> tbTransaksi;
    @FXML
    private TableColumn<Transaksi, Integer> cidTransaksi, cidBuku, cidAnggota;
    @FXML
    private TableColumn<Transaksi, LocalDate> cTanggalPinjam, cTanggalJatuhTempo, cTanggalKembali;
    @FXML
    private TableColumn<Transaksi, String> cStatus;
    @FXML
    private TextField tfsearch, tfidTransaksi, tfidBuku, tfidAnggota;
    @FXML
    private DatePicker dpTanggalPinjam, dpTanggalJatuhTempo, dpTanggalKembali;
    @FXML
    private CheckBox cbStatus;

    private ObservableList<Transaksi> transaksiList = FXCollections.observableArrayList();
    private boolean flagAdd = true;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTabel();
        loadData();
        setFilter();
        setButton(true, true, true, false, false);
        setTeks(false);
    }

    private void initTabel() {
        cidTransaksi.setCellValueFactory(new PropertyValueFactory<>("idTransaksi"));
        cidBuku.setCellValueFactory(new PropertyValueFactory<>("idBuku"));
        cidAnggota.setCellValueFactory(new PropertyValueFactory<>("idAnggota"));
        cTanggalPinjam.setCellValueFactory(new PropertyValueFactory<>("tanggalPinjam"));
        cTanggalJatuhTempo.setCellValueFactory(new PropertyValueFactory<>("tanggalJatuhTempo"));
        cTanggalKembali.setCellValueFactory(new PropertyValueFactory<>("tanggalKembali"));
        cStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tbTransaksi.setEditable(true);
        cidTransaksi.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        cidBuku.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        cidAnggota.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        cTanggalPinjam.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        cTanggalJatuhTempo.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        cTanggalKembali.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        cStatus.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    private void loadData() {
        transaksiList = TransaksiDAO.getAllTransaksi();
        tbTransaksi.setItems(transaksiList);
    }

    private void setFilter() {
        FilteredList<Transaksi> filteredData = new FilteredList<>(transaksiList, b -> true);

        tfsearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(transaksi -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(transaksi.getIdTransaksi()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(transaksi.getIdBuku()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(transaksi.getIdAnggota()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (transaksi.getTanggalPinjam().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (transaksi.getTanggalJatuhTempo().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (transaksi.getTanggalKembali() != null && transaksi.getTanggalKembali().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (transaksi.getStatus().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }

                return false;
            });
        });

        SortedList<Transaksi> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tbTransaksi.comparatorProperty());

        tbTransaksi.setItems(sortedData);
    }

    @FXML
    private void addAction(ActionEvent event) {
        flagAdd = true;
        clearForm();
        setButton(false, false, false, true, true);
        setTeks(true);
    }

    @FXML
    private void editAction(ActionEvent event) {
        flagAdd = false;
        setButton(false, false, false, true, true);
        setTeks(true);
    }

    @FXML
    private void deleteAction(ActionEvent event) {
        Transaksi transaksi = tbTransaksi.getSelectionModel().getSelectedItem();
        if (transaksi != null) {
            TransaksiDAO.deleteTransaksi(transaksi.getIdTransaksi());
            loadData();
        }
    }

    @FXML
    private void updateAction(ActionEvent event) {
        if (flagAdd) {
            Transaksi transaksi = new Transaksi(
                    Integer.parseInt(tfidTransaksi.getText()),
                    Integer.parseInt(tfidBuku.getText()),
                    Integer.parseInt(tfidAnggota.getText()),
                    dpTanggalPinjam.getValue(),
                    dpTanggalJatuhTempo.getValue(),
                    dpTanggalKembali.getValue(),
                    cbStatus.isSelected() ? "Kembali" : "Pinjam"
            );

            TransaksiDAO.insertTransaksi(transaksi);
        } else {
            Transaksi transaksi = tbTransaksi.getSelectionModel().getSelectedItem();
            transaksi.setIdBuku(Integer.parseInt(tfidBuku.getText()));
            transaksi.setIdAnggota(Integer.parseInt(tfidAnggota.getText()));
            transaksi.setTanggalPinjam(dpTanggalPinjam.getValue());
            transaksi.setTanggalJatuhTempo(dpTanggalJatuhTempo.getValue());
            transaksi.setTanggalKembali(dpTanggalKembali.getValue());
            transaksi.setStatus(cbStatus.isSelected() ? "Kembali" : "Pinjam");

            TransaksiDAO.updateTransaksi(transaksi);
        }

        clearForm();
        setButton(true, true, true, false, false);
        setTeks(false);
        loadData();
    }

    @FXML
    private void cancelAction(ActionEvent event) {
        clearForm();
        setButton(true, true, true, false, false);
        setTeks(false);
    }

    @FXML
    private void tableClicked(MouseEvent event) {
        Transaksi transaksi = tbTransaksi.getSelectionModel().getSelectedItem();
        if (transaksi != null) {
            tfidTransaksi.setText(String.valueOf(transaksi.getIdTransaksi()));
            tfidBuku.setText(String.valueOf(transaksi.getIdBuku()));
            tfidAnggota.setText(String.valueOf(transaksi.getIdAnggota()));
            dpTanggalPinjam.setValue(transaksi.getTanggalPinjam());
            dpTanggalJatuhTempo.setValue(transaksi.getTanggalJatuhTempo());
            dpTanggalKembali.setValue(transaksi.getTanggalKembali());
            cbStatus.setSelected("Kembali".equals(transaksi.getStatus()));
        }
    }

    private void clearForm() {
        tfidTransaksi.clear();
        tfidBuku.clear();
        tfidAnggota.clear();
        dpTanggalPinjam.setValue(null);
        dpTanggalJatuhTempo.setValue(null);
        dpTanggalKembali.setValue(null);
        cbStatus.setSelected(false);
    }

    private void setButton(boolean add, boolean edit, boolean del, boolean update, boolean cancel) {
        bAdd.setDisable(!add);
        bEdit.setDisable(!edit);
        bDel.setDisable(!del);
        bUpdate.setDisable(!update);
        bCancel.setDisable(!cancel);
    }

    private void setTeks(boolean enabled) {
        tfidTransaksi.setDisable(!enabled);
        tfidBuku.setDisable(!enabled);
        tfidAnggota.setDisable(!enabled);
        dpTanggalPinjam.setDisable(!enabled);
        dpTanggalJatuhTempo.setDisable(!enabled);
        dpTanggalKembali.setDisable(!enabled);
        cbStatus.setDisable(!enabled);
    }
}
