package com.martirio.china.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.martirio.china.Adapters.AdapterFotos;
import com.martirio.china.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;


public class FragmentCargarProducto extends Fragment implements AdapterView.OnItemSelectedListener {

    private RecyclerView recyclerFotos;
    private List<String> listaFotos;
    private AdapterFotos adapterFotos;
    private LinearLayoutManager layoutManager;



    public FragmentCargarProducto() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.cargar_producto_fragment, container, false);

        recyclerFotos=(RecyclerView)view.findViewById(R.id.recyclerFotos);
        listaFotos=new ArrayList<>();
        layoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerFotos.setLayoutManager(layoutManager);
        adapterFotos=new AdapterFotos();
        adapterFotos.setContext(getContext());
        adapterFotos.setListaStringsOriginales(listaFotos);
        recyclerFotos.setAdapter(adapterFotos);



        FloatingActionButton sacarFoto=(FloatingActionButton) view.findViewById(R.id.fabImagen);
        sacarFoto.setImageResource(R.drawable.ic_camera_alt_black_24dp);
        sacarFoto.setButtonSize(FloatingActionButton.SIZE_NORMAL);
        sacarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyImage.openCamera(FragmentCargarProducto.this, 1);
            }
        });



        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                //Some error handling
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {

            listaFotos.add(imageFile.getAbsolutePath());
            adapterFotos.notifyDataSetChanged();



            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                super.onCanceled(source, type);
                //Cancel handling, you might wanna remove taken photo if it was canceled
                if (source == EasyImage.ImageSource.CAMERA) {
                    File photoFile = EasyImage.lastlyTakenButCanceledPhoto(getActivity());
                    if (photoFile != null) photoFile.delete();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), "hola que tal ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

