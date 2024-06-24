package com.pojokbersih.Model;

import java.util.ArrayList;
import java.util.List;

import com.pojokbersih.DB;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DetailTransaksi {
    private final StringProperty idDetailTransaksi = new SimpleStringProperty();
    private final StringProperty idTransaksi = new SimpleStringProperty();
    private final StringProperty idProduk = new SimpleStringProperty();
    private final StringProperty harga = new SimpleStringProperty();
    private final StringProperty idStaff = new SimpleStringProperty();

    public String crudState = "DEFAULT";

    public DetailTransaksi(){

    }

    public DetailTransaksi(Object obj) {
        List<String> list = ((ArrayList<String>) obj);

        idDetailTransaksi.set(list.get(0).toString());
        idTransaksi.set(list.get(1).toString());
        idProduk.set(list.get(2).toString());
        harga.set(list.get(3).toString());
        idStaff.set(list.get(4).toString());
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

    public StringProperty idDetailTransaksiProperty() {
        return idDetailTransaksi;
    }

    public String getIdDetailTransaksi() {
        return idDetailTransaksi.get();
    }

    public void setIdDetailTransaksi(String idDetailTransaksi) {
        this.idDetailTransaksi.set(idDetailTransaksi);
    }

    public StringProperty idProdukProperty() {
        return idProduk;
    }

    public String getIdProduk() {
        return idProduk.get();
    }

    public void setIdProduk(String idProduk) {
        this.idProduk.set(idProduk);
    }

    public StringProperty hargaProperty() {
        return harga;
    }

    public String getHarga() {
        return harga.get();
    }

    public void setHarga(String harga) {
        this.harga.set(harga);
    }

    public StringProperty idStaffProperty() {
        return idStaff;
    }

    public String getIdStaff() {
        return idStaff.get();
    }

    public void setIdStaff(String idStaff) {
        this.idStaff.set(idStaff);
    }

    public Boolean create() {
        DB db = new DB();
        String data[] = new String[]{getIdTransaksi(), getIdProduk(), getHarga(), getIdStaff()};
        String queryVal = String.join("','", data);

        String sql = "INSERT INTO detailtransaksi (id_transaksi, id_produk, harga, id_staff) VALUES ('" + queryVal + "')";
        
        return db.runSql(sql);
    }

    public Boolean delete() {
        DB db = new DB();
        String sql = "DELETE FROM detailtransaksi WHERE id_detail_transaksi = '" + getIdDetailTransaksi() + "'";

        return db.runSql(sql);
    }
}
