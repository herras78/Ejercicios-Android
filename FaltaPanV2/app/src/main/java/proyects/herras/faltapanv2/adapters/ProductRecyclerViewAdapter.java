package proyects.herras.faltapanv2.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import proyects.herras.faltapanv2.R;
import proyects.herras.faltapanv2.support.Producto;

/**
 * Created by Herras on 30/07/2015.
 */
public class ProductRecyclerViewAdapter
        extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductRecyclerViewHolder>
        implements View.OnClickListener {

    private ArrayList<Producto> datos;
    private View.OnClickListener listener;

    public ProductRecyclerViewAdapter(ArrayList<Producto> datos){this.datos = datos;}

    public ProductRecyclerViewHolder onCreateViewHolder(ViewGroup viewgroup, int viewType){
        View itemView = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.item_product_card,viewgroup,false);

        itemView.setOnClickListener(this);

        ProductRecyclerViewHolder prvh = new ProductRecyclerViewHolder(itemView);
        return prvh;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }

    public void onBindViewHolder(ProductRecyclerViewHolder viewHolder, int pos){
        Producto item = datos.get(pos);
        viewHolder.bindLista(item);
    }

    public int getItemCount() {
        return datos.size();
    }

    public static class ProductRecyclerViewHolder extends RecyclerView.ViewHolder{
        private TextView productTit;
        private TextView productDate;
        private TextView productFamily;
        private TextView productStatus;
        private TextView productCuantity;
        private TextView productCuantityUnit;
        private TextView productBrand;

        public ProductRecyclerViewHolder(View itemView){
            super(itemView);
            productTit = (TextView)itemView.findViewById(R.id.product_tit);
            productDate = (TextView)itemView.findViewById(R.id.product_date);
            productFamily = (TextView)itemView.findViewById(R.id.product_family);
            productStatus = (TextView)itemView.findViewById(R.id.product_status);
            productCuantity = (TextView)itemView.findViewById(R.id.product_cuantity);
            productCuantityUnit = (TextView)itemView.findViewById(R.id.product_cuantity_unit);
            productBrand = (TextView)itemView.findViewById(R.id.product_brand);
         }

        public void bindLista(Producto product){
            productTit.setText(product.getTitle());
            productDate.setText(product.getFechaCreacion());
            productFamily.setText(product.getFamily());
            productStatus.setText(product.getStatus());
            productCuantity.setText(product.getCuantity() + "");
            productCuantityUnit.setText(product.getCuantityUnit());
            productBrand.setText(product.getBrand());
        }
    }
}
