package com.martirio.china.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.martirio.china.Modelo.Orden;
import com.martirio.china.Modelo.Producto;
import com.martirio.china.Modelo.Vendedor;
import com.martirio.china.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by elmar on 18/5/2017.
 */

public class AdapterProductoVendedor extends RecyclerView.Adapter implements View.OnClickListener {

    private Context context;
    private List<Orden> listaOrdenesOriginales;
    private View.OnClickListener listener;
    private Favoritable favoritable;






    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public void setContext(Context context) {
        this.context = context;

    }

    public void setListaOrdenesOriginales(List<Orden> listaOrdenesOriginales) {
        this.listaOrdenesOriginales = listaOrdenesOriginales;
    }

    public void addListaOrdensOriginales(List<Orden> listaOrdensOriginales) {
        this.listaOrdenesOriginales.addAll(listaOrdensOriginales);
    }


    public List<Orden> getListaOrdenesOriginales() {
        return listaOrdenesOriginales;
    }

    //crear vista y viewholder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View viewCelda;
        FragmentActivity unaActivity = (FragmentActivity) context;
        FragmentManager fragmentManager = (FragmentManager) unaActivity.getSupportFragmentManager();
        viewCelda = layoutInflater.inflate(R.layout.detalle_celda_recycler_ver_producto, parent, false);

        OrdenViewHolder peliculasViewHolder = new OrdenViewHolder(viewCelda);
        viewCelda.setOnClickListener(this);

        return peliculasViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Orden unOrden = listaOrdenesOriginales.get(position);
        OrdenViewHolder OrdenViewHolder = (OrdenViewHolder) holder;
        OrdenViewHolder.cargarOrden(unOrden);


    }

    @Override
    public int getItemCount() {
        return listaOrdenesOriginales.size();
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view);
    }



    //creo el viewholder que mantiene las referencias
    //de los elementos de la celda

    private static class OrdenViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textProducto;
        private TextView textVendedor;
        private FloatingActionButton fabProductoVendedor;
        //private TextView textViewTitulo;


        public OrdenViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img_detalle_orden);
            textProducto =(TextView) itemView.findViewById(R.id.text_producto_orden);
            textVendedor =(TextView) itemView.findViewById(R.id.text_vendedor_orden);
            fabProductoVendedor=(FloatingActionButton) itemView.findViewById(R.id.fab_detalle_producto);
            fabProductoVendedor.setButtonSize(FloatingActionButton.SIZE_MINI);
            fabProductoVendedor.setImageResource(R.drawable.ic_delete_forever_black_24dp);
        }

        public void cargarOrden(final Orden unOrden) {

            fabProductoVendedor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext(), R.style.Dialog);

                    builder.setTitle("Eliminar Producto")
                            .setMessage("El producto: " + unOrden.getProducto().getNombreProducto() +"\n"+ "Sera eliminado en forma permanente.")
                            .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Realm realm = Realm.getDefaultInstance();

                                    realm.executeTransaction(new Realm.Transaction() {
                                        @Override
                                        public void execute(Realm realm) {
                                            RealmResults<Orden> results = realm.where(Orden.class)
                                                    .equalTo("id", unOrden.getId())
                                                    .findAll();

                                            // Delete all matches
                                            results.deleteAllFromRealm();

                                        }
                                    });


                                }
                            })
                            .setNegativeButton("Volver", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });


                    AlertDialog unDialogo = builder.create();
                    unDialogo.show();
                    final Button positiveButton = unDialogo.getButton(AlertDialog.BUTTON_POSITIVE);
                    final Button negativeButton = unDialogo.getButton(AlertDialog.BUTTON_NEGATIVE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        negativeButton.setTextColor(itemView.getContext().getColor(R.color.Amarillo));
                        positiveButton.setTextColor(itemView.getContext().getColor(R.color.Amarillo));
                    } else {
                        negativeButton.setTextColor(itemView.getContext().getResources().getColor(R.color.Amarillo));
                        positiveButton.setTextColor(itemView.getContext().getResources().getColor(R.color.Amarillo));
                    }
                    LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                    positiveButtonLL.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    positiveButton.setLayoutParams(positiveButtonLL);

                }
            });


            textVendedor.setText(unOrden.getVendedor().getMailVendedor());
            textProducto.setText(unOrden.getProducto().getNombreProducto());
            if (unOrden.getProducto().getFotos().size() < 1) {

            }
            else {
                String unString = (unOrden.getProducto().getFotos()).get(0).getFoto();

                File f = new File(unString);
                Picasso.with(imageView.getContext())
                        .load(f)
                        .placeholder(R.drawable.back)
                        .into(imageView);
            }
        }


    }

    public interface Favoritable {
        public void recibirOrdenFavorito(Orden unOrden);
    }


}
