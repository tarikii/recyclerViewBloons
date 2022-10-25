package com.example.recyclerviewalbums;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewalbums.databinding.FragmentRecyclerAlbumsBinding;
import com.example.recyclerviewalbums.databinding.ViewholderAlbumBinding;

import java.util.List;

public class RecyclerAlbumsFragment extends Fragment {

    private FragmentRecyclerAlbumsBinding binding;
    private AlbumsViewModel albumsViewModel;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentRecyclerAlbumsBinding.inflate(inflater, container, false)).getRoot();
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderAlbumBinding binding;

        public AlbumViewHolder(ViewholderAlbumBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        albumsViewModel = new ViewModelProvider(requireActivity()).get(AlbumsViewModel.class);
        navController = Navigation.findNavController(view);

        // crear el Adaptador
        AlbumsAdapter albumsAdapter = new AlbumsAdapter();

        // asociar el Adaptador con el RecyclerView
        binding.recyclerView.setAdapter(albumsAdapter);

        // obtener el array de Elementos, y pasarselo al Adaptador
        albumsViewModel.obtener().observe(getViewLifecycleOwner(), new Observer<List<Album>>() {
            @Override
            public void onChanged(List<Album> albums) {
                albumsAdapter.establecerLista(albums);
            }
        });
    }

    class AlbumsAdapter extends RecyclerView.Adapter<AlbumViewHolder> {

        // referencia al Array que obtenemos del ViewModel
        List<Album> albums;

        // crear un nuevo ViewHolder
        @NonNull
        @Override
        public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AlbumViewHolder(ViewholderAlbumBinding.inflate(getLayoutInflater(), parent, false));
        }

        // rellenar un ViewHolder en una posición del Recycler con los datos del elemento que
        // esté en esa misma posición en el Array
        @Override
        public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {

            Album album = albums.get(position);
            holder.binding.nombre.setText(album.nomAlbum);
        }

        // informar al Recycler de cuántos elementos habrá en la lista
        @Override
        public int getItemCount() {
            return albums != null ? albums.size() : 0;
        }

        // establecer la referencia a la lista, y notificar al Recycler para que se regenere
        public void establecerLista(List<Album> albums){
            this.albums = albums;
            notifyDataSetChanged();
        }
    }
}
