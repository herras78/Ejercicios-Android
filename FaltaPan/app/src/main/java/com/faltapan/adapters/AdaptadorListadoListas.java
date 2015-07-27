package com.faltapan.adapters;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.faltapan.activities.R;
import com.faltapan.supportclass.Lista;
import com.faltapan.adapters.AdaptadorListadoListas;


public class AdaptadorListadoListas extends ArrayAdapter 
{
	 
    Activity context;
    Lista[] listado_listas;
    int item_listado_listas;
 
    public AdaptadorListadoListas(Activity context,int item_layout,Lista[] datos) 
    {
        super(context, item_layout, datos);
        this.context = context;
        listado_listas = datos;
        item_listado_listas = item_layout;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) 
    {
    	View item = convertView;
    	
    	if(item == null)
    	{
    		 LayoutInflater inflater = context.getLayoutInflater();
    	     item = inflater.inflate(item_listado_listas, null);
    	}
         
       /* ImageView imv1 = (ImageView) item.findViewById(R.id.B_IV_0);
        imv1.setImageDrawable(context.getResources().getDrawable(listado_listas[position].getImagen()));
        
        ImageView imv2 = (ImageView) item.findViewById(R.id.B_IV_1);
        imv2.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_delete));*/
 
        TextView lblNombre = (TextView)item.findViewById(R.id.B_TXV_ID0);
        lblNombre.setText(listado_listas[position].getNombre());
        InicializaBoton(lblNombre);
 
        TextView num_elementos = (TextView)item.findViewById(R.id.B_TXV_ID1);
        num_elementos.setText("" + listado_listas[position].getElementos() + " Productos");
        InicializaBoton(num_elementos);
 
        return(item);
    }
    
    public void InicializaBoton(TextView tv)
	{	
		Typeface font = Typeface.createFromAsset(context.getAssets(), "rabiohead.ttf");
		tv.setTypeface(font);
	}
}
