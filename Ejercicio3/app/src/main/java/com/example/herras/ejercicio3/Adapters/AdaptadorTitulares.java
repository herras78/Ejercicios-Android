package com.example.herras.ejercicio3.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.herras.ejercicio3.R;
import com.example.herras.ejercicio3.Titular;

import java.util.ArrayList;

/**
 * Created by Herras on 07/07/2015.
 */
public class AdaptadorTitulares
        extends RecyclerView.Adapter<AdaptadorTitulares.TitularesViewHolder>
        implements View.OnClickListener{

    private ArrayList<Titular> datos;
    private View.OnClickListener listener;

    public AdaptadorTitulares(ArrayList<Titular> datos){
        this.datos = datos;
    }
    @Override
    public TitularesViewHolder onCreateViewHolder(ViewGroup viewGroup,int viewType){
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_titular,viewGroup,false);
        itemView.setOnClickListener(this);

        TitularesViewHolder tvh = new TitularesViewHolder(itemView);

        return tvh;
    }
    @Override
    public void  onBindViewHolder(TitularesViewHolder viewHolder,int pos){
        Titular item = datos.get(pos);
        viewHolder.bindTitular(item);
    }
    @Override
    public int getItemCount(){
        return datos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public void  onClick(View view){
        if(listener != null){
            listener.onClick(view);
        }
    }
    public static class TitularesViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTitulo;
        private TextView txtSubTitulo;

        public TitularesViewHolder(View itemView){
            super(itemView);

            txtTitulo = (TextView)itemView.findViewById(R.id.lblTitulo);
            txtSubTitulo = (TextView)itemView.findViewById(R.id.lblSubTitulo);
        }

        public void bindTitular(Titular t){
            txtTitulo.setText(t.getTitulo());
            txtSubTitulo.setText(t.getSubtitulo());
        }
    }
}
