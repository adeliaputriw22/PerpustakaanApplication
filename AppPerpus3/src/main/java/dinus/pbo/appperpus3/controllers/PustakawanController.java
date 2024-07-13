package dinus.pbo.appperpus3.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dinus.pbo.appperpus3.DAO.PustakawanDAO;
import dinus.pbo.appperpus3.models.Pustakawan;
import dinus.pbo.appperpus3.models.DBConnection;
import javafx.collections.FXCollections;
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

public class PustakawanController implements Initializable {
    private Stage stage;
    private PustakawanDAO pustakawanDAO = new PustakawanDAO();
    private ObservableList<Pustakawan> listPustakawan = FXCollections.observableArrayList();
    private boolean flagAdd = true;

    @FXML
    private TableColumn<Pustakawan, Integer> cidPustakawan;
    @FXML
    private TableColumn<Pustakawan, String> cnama;
    @FXML
    private TableColumn<Pustakawan, String> cemail;
    @FXML
    private TableView<Pustakawan> tbPustakawan;
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
    private TextField tfidPustakawan;
    @FXML
    private TextField tfnama;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfsearch;

    @FXML
    void add(ActionEvent event) {
        setButton(false, false, false, true, true);
        clearTeks();
        setTeks(true);
        tfidPustakawan.requestFocus();
    }

    @FXML
    void cancel(ActionEvent event) {
        setButton(true, true, true, false, false);
        clearTeks();
    }

    @FXML
    void del(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Hapus data?", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            int id = Integer.parseInt(tfidPustakawan.getText());
            pustakawanDAO.deletePustakawan(id);
            loadData();
            clearTeks();
        }
    }

    @FXML
    void edit(ActionEvent event) {
        flagAdd = false;
        setButton(false, false, false, true, true);
        setTeks(true);
        tfidPustakawan.setEditable(false);
        tfnama.requestFocus();
    }

    @FXML
    void update(ActionEvent event) {
        Pustakawan p = new Pustakawan(
                Integer.parseInt(tfidPustakawan.getText()),
                tfnama.getText(),
                tfemail.getText()
        );
        if (flagAdd) {
            pustakawanDAO.addPustakawan(p);
        } else {
            pustakawanDAO.updatePustakawan(p);
        }
        loadData();
        setButton(true, true, true, false, false);
        clearTeks();
    }

    @FXML
    private void pilihPustakawan(MouseEvent event) {
        Pustakawan p = tbPustakawan.getSelectionModel().getSelectedItem();
        if (p != null) {
            tfidPustakawan.setText(String.valueOf(p.getIdPustakawan()));
            tfnama.setText(p.getNama());
            tfemail.setText(p.getEmail());
        }
    }

    private void initTabel() {
        cidPustakawan.setCellValueFactory(new PropertyValueFactory<>("idPustakawan"));
        cnama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        cemail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadData() {
        listPustakawan.clear();
        listPustakawan.addAll(pustakawanDAO.getAllPustakawan());
        tbPustakawan.setItems(listPustakawan);
    }

    private void setFilter() {
        FilteredList<Pustakawan> filteredData = new FilteredList<>(listPustakawan, b -> true);
        tfsearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(pustakawan -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return pustakawan.getNama().toLowerCase().contains(lowerCaseFilter)
                        || String.valueOf(pustakawan.getIdPustakawan()).contains(lowerCaseFilter)
                        || pustakawan.getEmail().toLowerCase().contains(lowerCaseFilter);
            });
        });
        SortedList<Pustakawan> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tbPustakawan.comparatorProperty());
        tbPustakawan.setItems(sortedData);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTabel();
        loadData();
        setFilter();
        setButton(true, true, true, false, false);
        setTeks(false);
    }

    private void clearTeks() {
        tfidPustakawan.clear();
        tfnama.clear();
        tfemail.clear();
    }

    private void setButton(Boolean add, Boolean edit, Boolean del, Boolean update, Boolean cancel) {
        bAdd.setDisable(!add);
        bEdit.setDisable(!edit);
        bDel.setDisable(!del);
        bUpdate.setDisable(!update);
        bCancel.setDisable(!cancel);
    }

    private void setTeks(Boolean editable) {
        tfidPustakawan.setEditable(editable);
        tfnama.setEditable(editable);
        tfemail.setEditable(editable);
    }
}
