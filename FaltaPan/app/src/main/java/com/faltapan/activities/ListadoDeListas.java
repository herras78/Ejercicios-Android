package com.faltapan.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.faltapan.bd.BD_Manager;

public class ListadoDeListas extends Activity{	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_listado_listas);
	
		ListView listado_listas = (ListView) findViewById(R.id.B_LV_0);
		listado_listas.setAdapter(getAdapter());
		setFont();
			 
		listado_listas.setOnItemClickListener(new OnItemClickListener() {
		    @Override
		    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
		    	
		    	//Log.i("PRUEBA ******************", ((TextView)v.findViewById(R.id.B_TXV_ID1)).getText().toString());    
		    	
		    	Bundle bdl = new Bundle();
		    	
		    	bdl.putString("NOMBRE", ((TextView)v.findViewById(R.id.B_TXV_ID1)).getText().toString());
		    	//Log.i("PRUEBA bdl  ******************",bdl.get("NOMBRE").toString());
		    	Intent itn = new Intent(ListadoDeListas.this, ListadoProductos.class);
		    	itn.putExtras(bdl);	    
		    	startActivity(itn);
		    }
		});		
	}
		
	public SimpleCursorAdapter getAdapter(){
		Context context = this;
		int layoutItem = R.layout.item_listado_listas;
		String query = "SELECT _id,NOMBRE, NUM_ELEMENTOS,REF_IMAGEN FROM T_LISTA";
		Cursor cursor = getCursorLista(query);
		int[] ref_controles = new int[]{R.id.B_TXV_ID1, R.id.B_TXV_ID2,R.id.B_IV_ID1};
		String[] nombre_cabeceras = new String[]{"NOMBRE", "NUM_ELEMENTOS","REF_IMAGEN"};

		return  new SimpleCursorAdapter(context, layoutItem, cursor, nombre_cabeceras, ref_controles,0) ; 
	}
	
	public Cursor getCursorLista(String query)
	{	
		Cursor cursor = null;
		try{
			BD_Manager dbm = new BD_Manager(ListadoDeListas.this);
			SQLiteDatabase db = dbm.getReadableDatabase();
				
			cursor = db.rawQuery(query, null);
			
		}catch(SQLException e){
			Log.e("**SQL FALTAPAN","Error SQL:" + e.getMessage() );
		};		
		return cursor;	
	}
	
	public void setFont()
	{
		int[] ref_controles = new int[]{R.id.B_TXV_ID0};
		Typeface font = Typeface.createFromAsset(getAssets(), "rabiohead.ttf");
		
		for(int txvId:ref_controles){
			TextView tv = (TextView) findViewById(txvId);
			tv.setTypeface(font);
		}
	}
}


