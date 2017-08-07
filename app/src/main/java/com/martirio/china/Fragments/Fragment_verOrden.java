package com.martirio.china.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.martirio.china.Adapters.AdapterProductoVendedor;
import com.martirio.china.Modelo.Orden;
import com.martirio.china.R;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;


public class Fragment_verOrden extends Fragment {

    private RecyclerView recyclerProductos;
    private AdapterProductoVendedor adapterOrden;
    private LinearLayoutManager layoutManager;
    private RealmList<Orden> listaOrdenes;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_fragment_ver_orden, container, false);

        recyclerProductos= (RecyclerView) view.findViewById(R.id.recyclerVerProductos);
        adapterOrden=new AdapterProductoVendedor();
        adapterOrden.setContext(getContext());
        layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerProductos.setLayoutManager(layoutManager);
        recyclerProductos.setAdapter(adapterOrden);

        Realm realm = Realm.getDefaultInstance();
        RealmResults<Orden> result2 = realm.where(Orden.class)
                .findAll();

        listaOrdenes=new RealmList<>();
        listaOrdenes.addAll(result2);
        adapterOrden.setListaOrdenesOriginales(listaOrdenes);
        adapterOrden.notifyDataSetChanged();

        return view;
    }
    public void borrarProducto(){

    }
}
