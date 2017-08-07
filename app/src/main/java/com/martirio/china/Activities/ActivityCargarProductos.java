package com.martirio.china.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.martirio.china.Fragments.FragmentCargarProducto;
import com.martirio.china.R;

public class ActivityCargarProductos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_productos);
        cargarFragmentCargaProducto();
    }
    public void cargarFragmentCargaProducto(){
        FragmentCargarProducto fragmentCargarProducto=new FragmentCargarProducto();
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedorCargarProductos, fragmentCargarProducto);
        fragmentTransaction.commit();
    }

}
