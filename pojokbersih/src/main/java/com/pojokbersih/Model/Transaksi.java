package com.pojokbersih.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.pojokbersih.DB;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Transaksi {
    private static final String List = null;
    private final StringProperty idTransaksi = new SimpleStringProperty();
    private final StringProperty idCustomer = new SimpleStringProperty();
    private final StringProperty pic = new SimpleStringProperty();
    private final StringProperty tanggalTransaksi = new SimpleStringProperty();
    private final StringProperty tanggalPengerjaan = new SimpleStringProperty();
    private final StringProperty biayaJasa = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    public Transaksi() {

    }

    public Transaksi(Object obj) {
        List<String> list = ((ArrayList<String>) obj);

        idTransaksi.set(list.get(0).toString());
        idCustomer.set(list.get(1).toString());
        pic.set(list.get(2).toString());
        tanggalTransaksi.set(list.get(3).toString());
        tanggalPengerjaan.set(list.get(4).toString());
        biayaJasa.set(list.get(5).toString());
        status.set(list.get(6).toString());
    }

    public StringProperty idTransaksiProperty() {
        return idTransaksi;
    }

    public String getIdTransaksi() {
        return idTransaksi.get();
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi.set(idTransaksi);
    }

    public StringProperty idCustomerProperty() {
        return idCustomer;
    }

    public String getIdCustomer() {
        return idCustomer.get();
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer.set(idCustomer);
    }

    public StringProperty picProperty() {
        return pic;
    }

    public String getPic() {
        return pic.get();
    }

    public void setPic(String pic) {
        this.pic.set(pic);
    }

    public StringProperty tanggalTransaksiProperty() {
        return tanggalTransaksi;
    }

    public String getTanggalTransaksi() {
        return tanggalTransaksi.get();
    }

    public void setTanggalTransaksi(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = localDate.format(formatter);
        this.tanggalTransaksi.set(formattedDate);
    }

    public StringProperty tanggalPengerjaanProperty() {
        return tanggalPengerjaan;
    }

    public String getTanggalPengerjaan() {
        return tanggalPengerjaan.get();
    }

    public void setTanggalPengerjaan(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = localDate.format(formatter);
        this.tanggalPengerjaan.set(formattedDate);
    }

    public StringProperty biayaJasaProperty() {
        return biayaJasa;
    }

    public String getBiayaJasa() {
        return biayaJasa.get();
    }

    public void setBiayaJasa(String biayaJasa) {
        this.biayaJasa.set(biayaJasa);
    }

    public StringProperty statusProperty() {
        return status;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public Boolean create() {
        DB db = new DB();

        String data[] = new String[]{getIdTransaksi(), getIdCustomer(), getPic(), getTanggalTransaksi(), getTanggalPengerjaan(), getBiayaJasa(), getStatus()};
        String queryVal = String.join("','", data);

        String sql = "INSERT INTO transaksi (id_transaksi, id_customer, pic, tgl_transaksi, tgl_pengerjaan, biaya_jasa, status) VALUES ('" + queryVal + "')";

        System.out.println(sql);

        return db.runSql(sql);
    }

    public Boolean update() {
        String sql = "UPDATE transaksi SET id_customer = '" + getIdCustomer() + "', pic = '" + getPic() + "', tgl_transaksi = '" + getTanggalTransaksi() + "', tgl_pengerjaan = '" + getTanggalPengerjaan() + "', biaya_jasa = '" + getBiayaJasa() + "', status = '" + getStatus() + "' WHERE id_transaksi = " + getIdTransaksi();
        DB db = new DB();
    
        return db.runSql(sql);
    }

    public Boolean delete() {
        DB db = new DB();
        String sql = "DELETE FROM transaksi WHERE id_transaksi = '" + getIdTransaksi() + "'";
        // String sql_detail = "DELETE FROM detailtransaksi WHERE id_transaksi = '" + getIdTransaksi() + "'";

        // db.runSql(sql_detail);
        return db.runSql(sql);
    }
}