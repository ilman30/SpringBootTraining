/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilman.latihan.model;

/**
 *
 * @author ilman30
 */
public class Kabupaten {
    
    private int idKabupaten;
    private String namaKabupaten;
    private int kodeProvinsi;
    private Provinsi provinsi;

    public int getIdKabupaten() {
        return idKabupaten;
    }

    public void setIdKabupaten(int idKabupaten) {
        this.idKabupaten = idKabupaten;
    }

    public String getNamaKabupaten() {
        return namaKabupaten;
    }

    public void setNamaKabupaten(String namaKabupaten) {
        this.namaKabupaten = namaKabupaten;
    }

    public int getKodeProvinsi() {
        return kodeProvinsi;
    }

    public void setKodeProvinsi(int kodeProvinsi) {
        this.kodeProvinsi = kodeProvinsi;
    }

    public Provinsi getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(Provinsi provinsi) {
        this.provinsi = provinsi;
    }
    
}
