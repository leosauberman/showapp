package br.com.series.showapp;

import android.net.Uri;

/**
 * Created by ADM on 02/11/2017.
 */

class Serie {
    String nome;
    String desc;
    Uri imageUri;
    String categoria;

    public Serie(String nome, String desc, Uri imageUri, String categoria) {
        this.nome = nome;
        this.desc = desc;
        this.imageUri = imageUri;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
