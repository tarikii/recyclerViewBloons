package com.example.recyclerviewalbums;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recyclerviewalbums.databinding.FragmentMostrarAlbumBinding;

public class MostrarAlbumFragment extends Fragment {

    private FragmentMostrarAlbumBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentMostrarAlbumBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AlbumsViewModel albumsViewModel = new ViewModelProvider(requireActivity()).get(AlbumsViewModel.class);

        albumsViewModel.seleccionado().observe(getViewLifecycleOwner(), new Observer<Album>() {
            @Override
            public void onChanged(Album album) {
                binding.nombre.setText(album.nomAlbum);
                binding.descripcion.setText(album.descripcion);
                binding.imagen.setImageResource(album.imagen);
            }
        });
    }
}