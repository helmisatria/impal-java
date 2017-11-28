/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author helmisatria
 */
public class Barang {
    private String nama;
    private String alamat;
    private Integer harga;

    public Barang() {
    }

    public Barang(String nama, String alamat, Integer harga) {
        this.nama = nama;
        this.alamat = alamat;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public Integer getHarga() {
        return harga;
    }
    
    
}
