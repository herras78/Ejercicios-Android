package proyects.herras.faltapanv2.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
public class ListRecyclerViewAdapter
        extends RecyclerView.Adapter<ListRecyclerViewAdapter.ListaRecyclerViewHolder>
        implements View.OnClickListener,View.OnLongClickListener{

    private ArrayList<Lista> datos;
    private View.OnClickListener listener;
    private View.OnLongClickListener longListener;

    public ListRecyclerViewAdapter(ArrayList<Lista> datos){
        this.datos = datos;
    }

    public ListaRecyclerViewHolder onCreateViewHolder(ViewGroup viewgroup, int viewType){
        View itemView = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.item_list_card,viewgroup,false);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        //itemView.findViewById(R.id.listItem);

        ListaRecyclerViewHolder rvh = new ListaRecyclerViewHolder(itemView);
        return rvh;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public void setOnLongClickListener(View.OnLongClickListener listener){
        this.longListener = listener;
    }

    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }

    public boolean onLongClick(View view) {
        if(longListener != null)
            longListener.onLongClick(view);
        return false;
    }

    public void onBindViewHolder(ListaRecyclerViewHolder viewHolder, int pos){
        Lista item = datos.get(pos);
        viewHolder.bindLista(item,pos);
    }

    public int getItemCount() {
        return datos.size();
    }

    public static class ListaRecyclerViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgList ;
        private TextView txtTitList;
        private TextView txtNumProduct;
        private TextView txtPercentList;
        private TextView txtDate;
        private TextView listId;
        private TextView listPosotion;
        //private ImageView imgStatusList ;
        //private ImageView imgPercentList ;

        public ListaRecyclerViewHolder(View itemView){
            super(itemView);
            imgList = (ImageView)itemView.findViewById(R.id.list_img);
            txtTitList = (TextView)itemView.findViewById(R.id.list_tit);
            txtNumProduct = (TextView)itemView.findViewById(R.id.num_product);
            txtPercentList = (TextView)itemView.findViewById(R.id.percent_list);
            txtDate = (TextView)itemView.findViewById(R.id.list_date);
            listId = (TextView)itemView.findViewById(R.id.list_id);
            listPosotion =(TextView)itemView.findViewById(R.id.list_position);
        }

        public void bindLista(Lista list,int pos){
            imgList.setImageResource(list.getImagen());
            txtTitList.setText(list.getNombre());
            txtNumProduct.setText(list.getNumElementosComprados() + "/" + list.getNumElementos() + " Productos");
            txtPercentList.setText(getPercent(list)+"% Completado");
            txtDate.setText(list.getFechaCreacion());
            listId.setText(list.getListId()+"");
            listPosotion.setText(pos+"");
        }

        public int getPercent(Lista list){
            //Log.d("FaltaPan","Numero de elementos:"+list.getNumElementosComprados());
            if(list.getNumElementosComprados() == 0 ) return 0;
            else return (int)((list.getNumElementosComprados()*100)/ list.getNumElementos());
        }
    }
}

