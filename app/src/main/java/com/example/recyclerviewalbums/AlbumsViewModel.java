package com.example.recyclerviewalbums;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class AlbumsViewModel extends AndroidViewModel {
    AlbumsRepositorio albumsRepositorio;

    MutableLiveData<List<Album>> listAlbumsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Album> albumSeleccionado = new MutableLiveData<>();

    public AlbumsViewModel(@NonNull Application application) {
        super(application);

        albumsRepositorio = new AlbumsRepositorio();

        listAlbumsMutableLiveData.setValue(albumsRepositorio.obtener());
    }

    MutableLiveData<List<Album>> obtener(){
        return listAlbumsMutableLiveData;
    }

    void seleccionar(Album album){
        albumSeleccionado.setValue(album);
    }

    MutableLiveData<Album> seleccionado(){
        return albumSeleccionado;
    }
}
