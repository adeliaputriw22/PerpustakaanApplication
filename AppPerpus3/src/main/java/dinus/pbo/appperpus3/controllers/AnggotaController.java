package dinus.pbo.appperpus3.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dinus.pbo.appperpus3.DAO.AnggotaDAO;
import dinus.pbo.appperpus3.models.Anggota;
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

public class AnggotaController implements Initializable {
    Stage stage;
    ObservableList<Anggota> listAnggota = FXCollections.observableArrayList();
    boolean flagAdd = true;

    @FXML
    private TableColumn<Anggota, Integer> cidAnggota;
    @FXML
    private TableColumn<Anggota, String> cnama;
    @FXML
    private TableColumn<Anggota, String> calamat;
    @FXML
    private TableView<Anggota> tbAnggota;
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
    private TextField tfidAnggota;
    @FXML
    private TextField tfnama;
    @FXML
    private TextField tfalamat;
    @FXML
    private TextField tfsearch;

    @FXML
    void add(ActionEvent event) {
        setButton(false, false, false, true, true);
        clearTeks();
        setTeks(true);
        tfidAnggota.requestFocus();
    }

    @FXML
    void cancel(ActionEvent event) {
        setButton(true, true, true, false, false);
        clearTeks();
    }

    @FXML
    void del(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "hapus data ?", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            try {
                AnggotaDAO.deleteAnggota(Integer.parseInt(tfidAnggota.getText()));
                loadData();
                clearTeks();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void edit(ActionEvent event) {
        flagAdd = false;
        setButton(false, false, false, true, true);
        setTeks(true);
        tfidAnggota.setEditable(false);
        tfnama.requestFocus();
    }

    @FXML
    void update(ActionEvent event) {
        Anggota anggota = new Anggota(Integer.parseInt(tfidAnggota.getText()), tfnama.getText(), tfalamat.getText());
        try {
            if (flagAdd) {
                AnggotaDAO.addAnggota(anggota);
            } else {
                AnggotaDAO.updateAnggota(anggota);
            }
            loadData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setButton(true, true, true, false, false);
        clearTeks();
    }

    @FXML
    void pilihAnggota(MouseEvent event) {
        Anggota a = tbAnggota.getSelectionModel().getSelectedItem();
        tfidAnggota.setText(String.valueOf(a.getIdAnggota()));
        tfnama.setText(a.getNama());
        tfalamat.setText(a.getAlamat());
    }

    void initTabel() {
        cidAnggota.setCellValueFactory(new PropertyValueFactory<Anggota, Integer>("idAnggota"));
        cnama.setCellValueFactory(new PropertyValueFactory<Anggota, String>("nama"));
        calamat.setCellValueFactory(new PropertyValueFactory<Anggota, String>("alamat"));
    }

    void loadData() {
        listAnggota = AnggotaDAO.getDataAnggota();
        tbAnggota.setItems(listAnggota);
    }

    void setFilter() {
        FilteredList<Anggota> filterData = new FilteredList<>(listAnggota, b -> true);
        tfsearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(anggota -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (anggota.getNama().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (String.valueOf(anggota.getIdAnggota()).contains(searchKeyword)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Anggota> sortedData = new SortedList<>(filterData);
        sortedData.comparatorProperty().bind(tbAnggota.comparatorProperty());
        tbAnggota.setItems(sortedData);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTabel();
        loadData();
        setFilter();
        setButton(true, true, true, false, false);
        setTeks(false);
    }

    protected void clearTeks() {
        tfidAnggota.setText(null);
        tfnama.setText(null);
        tfalamat.setText(null);
    }

    protected void setButton(Boolean b1, Boolean b2, Boolean b3, Boolean b4, Boolean b5) {
        bAdd.setDisable(!b1);
        bEdit.setDisable(!b2);
        bDel.setDisable(!b3);
        bUpdate.setDisable(!b4);
        bCancel.setDisable(!b5);
    }

    protected void setTeks(Boolean b) {
        tfidAnggota.setEditable(b);
        tfnama.setEditable(b);
        tfalamat.setEditable(b);
    }
}
