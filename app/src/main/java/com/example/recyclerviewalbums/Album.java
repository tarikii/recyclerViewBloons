package com.example.recyclerviewalbums;

public class Album {
    int imagen;
    String nomAlbum;
    String descripcion;

    public Album(int imagen,String nom, String descripcion) {
        this.nomAlbum = nom;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }
}
