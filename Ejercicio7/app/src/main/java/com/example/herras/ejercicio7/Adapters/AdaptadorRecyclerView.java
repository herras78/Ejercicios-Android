package com.example.herras.ejercicio7.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.herras.ejercicio7.Item;
import com.example.herras.ejercicio7.R;

import java.util.ArrayList;

/**
 * Created by Herras on 14/07/2015.
 */
public class AdaptadorRecyclerView  extends RecyclerView.Adapter<AdaptadorRecyclerView.RecyclerViewHolder>implements View.OnClickListener{

    private ArrayList<Item> datos;
    private View.OnClickListener listener;

    public AdaptadorRecyclerView(ArrayList<Item> datos){
        this.datos = datos;
    }

    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewgroup, int viewType){
        View itemView = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.list_item,viewgroup,false);

        itemView.setOnClickListener(this);
        //itemView.findViewById(R.id.listItem);

        RecyclerViewHolder rvh = new RecyclerViewHolder(itemView);
        return rvh;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }

    public void onBindViewHolder(RecyclerViewHolder viewHolder, int pos){
        Item item = datos.get(pos);
        viewHolder.bindItem(item);
    }

    public int getItemCount() {
        return datos.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private TextView item;

        public RecyclerViewHolder(View itemView){
            super(itemView);
            item = (TextView)itemView.findViewById(R.id.listItem);
        }

        public void bindItem(Item i){
            item.setText(i.getItem());
        }
    }
}
