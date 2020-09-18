/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilman.latihan.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ilman
 */
public class Kecamatan {
    
    @NotNull(message = "Tidak boleh kosong")
    @Min(value = 1)
    private int idKecamatan;
    
    @NotEmpty
    @Size(min = 3)
    private String namaKecamatan;
    
    private int kodeKabupaten;
    private Kabupaten kabupaten;
    
    private int kodeProvinsi;
    private Provinsi provinsi;

    public int getIdKecamatan() {
        return idKecamatan;
    }

    public void setIdKecamatan(int idKecamatan) {
        this.idKecamatan = idKecamatan;
    }

    public String getNamaKecamatan() {
        return namaKecamatan;
    }

    public void setNamaKecamatan(String namaKecamatan) {
        this.namaKecamatan = namaKecamatan;
    }

    public int getKodeKabupaten() {
        return kodeKabupaten;
    }

    public void setKodeKabupaten(int kodeKabupaten) {
        this.kodeKabupaten = kodeKabupaten;
    }

    public Kabupaten getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(Kabupaten kabupaten) {
        this.kabupaten = kabupaten;
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
