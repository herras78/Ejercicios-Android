package proyects.herras.faltapanv2.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import proyects.herras.faltapanv2.R;
import proyects.herras.faltapanv2.support.Producto;

/**
 * Created by Herras on 30/07/2015.
 */
public class ProductRecyclerViewAdapter
        extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductRecyclerViewHolder>
        implements View.OnClickListener,View.OnLongClickListener {

    private ArrayList<Producto> datos;
    private View.OnClickListener listener;
    private View.OnLongClickListener longListener;

    public ProductRecyclerViewAdapter(ArrayList<Producto> datos){this.datos = datos;}

    public ProductRecyclerViewHolder onCreateViewHolder(ViewGroup viewgroup, int viewType){
        View itemView = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.item_product_card,viewgroup,false);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);

        ProductRecyclerViewHolder prvh = new ProductRecyclerViewHolder(itemView);
        return prvh;
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

    public void onBindViewHolder(ProductRecyclerViewHolder viewHolder, int pos){
        Producto item = datos.get(pos);
        viewHolder.bindProduct(item,pos);

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
        private TextView productPrice;
        private TextView productId;
        private TextView productPosotion;
        private View line;
        private RelativeLayout cardView;
        private Context context;
        ArrayList<TextView> textViews;

        public ProductRecyclerViewHolder(View itemView){
            super(itemView);
            productTit = (TextView)itemView.findViewById(R.id.product_tit);
            productDate = (TextView)itemView.findViewById(R.id.product_date);
            productFamily = (TextView)itemView.findViewById(R.id.product_family);
            productStatus = (TextView)itemView.findViewById(R.id.product_status);
            productCuantity = (TextView)itemView.findViewById(R.id.product_cuantity);
            productCuantityUnit = (TextView)itemView.findViewById(R.id.product_cuantity_unit);
            productBrand = (TextView)itemView.findViewById(R.id.product_brand);
            productPrice = (TextView)itemView.findViewById(R.id.product_price);
            productId = (TextView)itemView.findViewById(R.id.product_id);
            productPosotion =(TextView)itemView.findViewById(R.id.product_position);
            line = (View)itemView.findViewById(R.id.product_line);

            cardView = (RelativeLayout)itemView.findViewById(R.id.product_item_card);
            context = itemView.getContext();

            textViews = new ArrayList<TextView>();
            textViews.add(productTit);
            textViews.add(productDate);
            textViews.add(productFamily);
            textViews.add(productStatus);
            textViews.add(productCuantity);
            textViews.add(productCuantityUnit);
            textViews.add(productBrand);
            textViews.add(productPrice);
         }

        public void bindProduct(Producto product,int position){
            productTit.setText(product.getTitle());
            productDate.setText(product.getFechaCreacion());
            productFamily.setText(product.getFamily());
            productStatus.setText(product.getStatus());
            productCuantity.setText(product.getCuantity() + "");
            productCuantityUnit.setText(product.getCuantityUnit());
            productBrand.setText(product.getBrand());
            productId.setText(product.getProductId()+"");
            productPrice.setText(product.getPrice() + "€/u");//Parametrizar €
            productPosotion.setText(position+"");
            setStatusColor(product.getStatus());
        }

        public void setStatusColor(String status){
            // Estado del producto Pendiente"P", Comprado"C",Subrayado"S",Descartado"D",Agotado"A"
            Log.d("FaltaPan", "El estatus es " +status);
            switch (status.trim()){
                case "P":
                    cardView.setBackgroundColor(context.getResources().getColor(R.color.color_status_backgroud_p));
                    for(TextView tv:textViews){
                        tv.setTextColor(context.getResources().getColor(R.color.color_status_text_p));
                    }
                    line.setBackgroundColor(context.getResources().getColor(R.color.color_status_text_p));
                    break;
                case "C":
                    cardView.setBackgroundColor(context.getResources().getColor(R.color.color_status_backgroud_c));
                    for(TextView tv:textViews){
                        tv.setTextColor(context.getResources().getColor(R.color.color_status_text_c));
                    }
                    line.setBackgroundColor(context.getResources().getColor(R.color.color_status_text_c));
                    break;
                case "S":
                    break;
                case "D":
                    break;
                case "A":
                    break;
            }
        }
    }
}
