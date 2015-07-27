package com.faltapan.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.faltapan.bd.BD_Manager;

public class ListadoProductos extends Activity{

	Intent itn;
	Bundle b;
	String nombreLista;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_listado_productos);
		TextView txv = null;
		
		itn = this.getIntent();
		b = itn.getExtras();
		
		nombreLista = b.getString("NOMBRE").toString();
		
		txv = (TextView) findViewById(R.id.C_TXV_ID1);		
		txv.setText(nombreLista);
		
		ListView lstv = (ListView)findViewById(R.id.C_LV_ID0);
		lstv.setAdapter(getAdapter());
	}
	
	public Cursor getCursorLista(String query)
	{	
		Cursor cursor = null;
		try{
			BD_Manager dbm = new BD_Manager(ListadoProductos.this);
			SQLiteDatabase db = dbm.getReadableDatabase();
				
			cursor = db.rawQuery(query, null);
			
		}catch(SQLException e){
			Log.e("**SQL FALTAPAN","Error SQL:" + e.getMessage() );
		};		
		return cursor;	
	}
	
	public SimpleCursorAdapter getAdapter(){
		Context context = this;
		int layoutItem = R.layout.item_listado_productos;
		String query = "SELECT T_PRODUCTO._id, T_PRODUCTO.NOMBRE FROM T_PRODUCTO, T_LISTA,T_A_LISTAPRODUCTO " +
						"WHERE T_LISTA._id = T_A_LISTAPRODUCTO.ID_LISTA " +
							"AND T_A_LISTAPRODUCTO.ID_PRODUCTO = T_PRODUCTO._id " +
							"AND T_LISTA.NOMBRE = '" + nombreLista + "'";
		Cursor cursor = getCursorLista(query);
		int[] ref_controles = new int[]{R.id.C_TXV_ID0};
		String[] nombre_cabeceras = new String[]{"NOMBRE"};

		return  new SimpleCursorAdapter(context, layoutItem, cursor, nombre_cabeceras, ref_controles,0) ; 
	}
}