package dinus.pbo.appperpus3.models;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Transaksi {
    private final IntegerProperty idTransaksi;
    private final IntegerProperty idBuku;
    private final IntegerProperty idAnggota;
    private final ObjectProperty<LocalDate> tanggalPinjam;
    private final ObjectProperty<LocalDate> tanggalJatuhTempo;
    private final ObjectProperty<LocalDate> tanggalKembali;
    private final StringProperty status;

    public Transaksi(int idTransaksi, int idBuku, int idAnggota, LocalDate tanggalPinjam, LocalDate tanggalJatuhTempo, LocalDate tanggalKembali, String status) {
        this.idTransaksi = new SimpleIntegerProperty(idTransaksi);
        this.idBuku = new SimpleIntegerProperty(idBuku);
        this.idAnggota = new SimpleIntegerProperty(idAnggota);
        this.tanggalPinjam = new SimpleObjectProperty<>(tanggalPinjam);
        this.tanggalJatuhTempo = new SimpleObjectProperty<>(tanggalJatuhTempo);
        this.tanggalKembali = new SimpleObjectProperty<>(tanggalKembali);
        this.status = new SimpleStringProperty(status);
    }

    // Getters and Setters
    public int getIdTransaksi() {
        return idTransaksi.get();
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi.set(idTransaksi);
    }

    public IntegerProperty idTransaksiProperty() {
        return idTransaksi;
    }

    public int getIdBuku() {
        return idBuku.get();
    }

    public void setIdBuku(int idBuku) {
        this.idBuku.set(idBuku);
    }

    public IntegerProperty idBukuProperty() {
        return idBuku;
    }

    public int getIdAnggota() {
        return idAnggota.get();
    }

    public void setIdAnggota(int idAnggota) {
        this.idAnggota.set(idAnggota);
    }

    public IntegerProperty idAnggotaProperty() {
        return idAnggota;
    }

    public LocalDate getTanggalPinjam() {
        return tanggalPinjam.get();
    }

    public void setTanggalPinjam(LocalDate tanggalPinjam) {
        this.tanggalPinjam.set(tanggalPinjam);
    }

    public ObjectProperty<LocalDate> tanggalPinjamProperty() {
        return tanggalPinjam;
    }

    public LocalDate getTanggalJatuhTempo() {
        return tanggalJatuhTempo.get();
    }

    public void setTanggalJatuhTempo(LocalDate tanggalJatuhTempo) {
        this.tanggalJatuhTempo.set(tanggalJatuhTempo);
    }

    public ObjectProperty<LocalDate> tanggalJatuhTempoProperty() {
        return tanggalJatuhTempo;
    }

    public LocalDate getTanggalKembali() {
        return tanggalKembali.get();
    }

    public void setTanggalKembali(LocalDate tanggalKembali) {
        this.tanggalKembali.set(tanggalKembali);
    }

    public ObjectProperty<LocalDate> tanggalKembaliProperty() {
        return tanggalKembali;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public StringProperty statusProperty() {
        return status;
    }
}
