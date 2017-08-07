package com.martirio.china.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.martirio.china.Fragments.FragmentCargarProducto;
import com.martirio.china.Fragments.Fragment_verOrden;
import com.martirio.china.R;

public class ActivityVerProductos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_productos);
        cargarFragmentCargaProducto();
    }
    public void cargarFragmentCargaProducto(){
        Fragment_verOrden fragment_verOrden=new Fragment_verOrden();
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedorVerProductos, fragment_verOrden);
        fragmentTransaction.commit();
    }
}
