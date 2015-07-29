package proyects.herras.faltapanv2.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import proyects.herras.faltapanv2.R;
import proyects.herras.faltapanv2.support.Lista;

/**
 * Created by Herras on 27/07/2015.
 */
public class ListRecyclerViewAdapter extends RecyclerView.Adapter<ListRecyclerViewAdapter.ListaRecyclerViewHolder>implements View.OnClickListener{

    private ArrayList<Lista> datos;
    private View.OnClickListener listener;

    public ListRecyclerViewAdapter(ArrayList<Lista> datos){
        this.datos = datos;
    }

    public ListaRecyclerViewHolder onCreateViewHolder(ViewGroup viewgroup, int viewType){
        View itemView = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.card_list_item,viewgroup,false);

        itemView.setOnClickListener(this);
        //itemView.findViewById(R.id.listItem);

        ListaRecyclerViewHolder rvh = new ListaRecyclerViewHolder(itemView);
        return rvh;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }

    public void onBindViewHolder(ListaRecyclerViewHolder viewHolder, int pos){
        Lista item = datos.get(pos);
        viewHolder.bindLista(item);
    }

    public int getItemCount() {
        return datos.size();
    }

    public static class ListaRecyclerViewHolder extends RecyclerView.ViewHolder{
       private ImageView imgList ;
        private ImageView imgStatusList ;
        private ImageView imgPercentList ;
        private TextView txtTitList;
        private TextView txtNumProduct;
        private TextView txtPercentList;
        private TextView txtDate;

        public ListaRecyclerViewHolder(View itemView){
            super(itemView);
            imgList = (ImageView)itemView.findViewById(R.id.img_list);
            txtTitList = (TextView)itemView.findViewById(R.id.tit_list);
            txtNumProduct = (TextView)itemView.findViewById(R.id.num_product);
            txtPercentList = (TextView)itemView.findViewById(R.id.percent_product);
            txtDate = (TextView)itemView.findViewById(R.id.date_list);
        }

        public void bindLista(Lista list){
            imgList.setImageResource(list.getImagen());
            txtTitList.setText(list.getNombre());
            txtNumProduct.setText(list.getNumElementos()+" Productos");
            txtPercentList.setText(list.getPorcentajeCompletado()+"%");
            txtDate.setText(list.getFechaCreacion());
        }
    }
}

