package com.valles.bodytrain;

import java.util.ArrayList;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MisRutinas extends Activity {
	
	private BodyDB  bodyDB = new BodyDB(this,"BDBodytrain",null,19);
	private static ArrayList<EntreItemList> EntreArray =  new ArrayList<EntreItemList>();
	private EjeAdapter adaptador = null;
	
	public void onCreate(Bundle SavedInstanceState){
		super.onCreate(SavedInstanceState);
		setContentView(R.layout.misrutinas);
		final LinearLayout NuevaRut = (LinearLayout)findViewById(R.id.btnnueva);
		final LinearLayout SalirRut = (LinearLayout)findViewById(R.id.salirmisrut);
		
		CargaDatos();
		
		ListView ejerList = (ListView)findViewById(R.id.lstmisrut);
        adaptador = new EjeAdapter(this);
        ejerList.setAdapter(adaptador);
        
        ejerList.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> a, View v, int position, long id) {       		
        		
        		Toast.makeText(MisRutinas.this,"Comenzando : "+EntreArray.get(position).titEntre,Toast.LENGTH_SHORT).show();
        		setEstado(position+1);
        		
        		Intent intent = new Intent(MisRutinas.this, EjerList.class); 		    
                startActivity(intent);		
        	}
        }); 
        
        NuevaRut.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	setEstado(0);
            	Intent intent = new Intent(MisRutinas.this, EjerBody.class);
        		startActivity(intent);
            }
        });
        
        SalirRut.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(MisRutinas.this, Main.class);
        		startActivity(intent);
            }
        });   
	}
	
	public void setEstado(int position){		
		
		SQLiteDatabase db = bodyDB.getWritableDatabase();
		
		if(db!=null){
			db.execSQL("DELETE From Rutina");
			db.execSQL("DELETE From Estado");
			if(position==0){
				db.execSQL("INSERT INTO Estado (est_id,est_titulo,est_rutina,est_entrada) VALUES(0,'Rutina Libre','true','true')");		
			}else{
				db.execSQL("INSERT INTO Estado (est_id,est_titulo,est_rutina,est_entrada) VALUES("+EntreArray.get(position-1).idEntre +",'"+ EntreArray.get(position-1).titEntre.toString()+"','false','true')");
			}
		}
		db.close();
	}
	
	public class EntreItemList{	
		public Integer idEntre;
		public Integer iconoEntre;
		public String tiempoEntre;
		public String titEntre;
		public String numEntre;
		public String intenEntre;			
	}
	
	public String IntAStr(int t){
		
		int min;
		int sec;	
		String smin;
		String ssec;
			    	 
		min = t / 60;	    	 
		sec = t % 60;
	    	 
	    if(min<10){smin= "0" + min;}
	    else{smin= "" + min;}
	    	 
	    if(sec<10){ssec= "0" + sec;}
	    else{ssec= "" + sec;}
		
		String tiempo = smin + ":" + ssec; 

		return tiempo;
	}
	
	private void CargaDatos(){
		Cursor c = null;
		EntreArray.clear();

		SQLiteDatabase db = bodyDB.getReadableDatabase();
			
		if(db!=null){
			c = db.rawQuery("SELECT * FROM Entrenamientos WHERE entretipo = 'true'" ,null);
		}
		
		if(c.moveToFirst()){
			do{
				EntreItemList item = new EntreItemList();
				
				item.idEntre = c.getInt(0);
				item.titEntre = c.getString(1);
				item.iconoEntre = c.getInt(2);
				item.intenEntre = c.getString(3);
				item.tiempoEntre = IntAStr(c.getInt(4));
				item.numEntre = Integer.toString(c.getInt(5));
				
				EntreArray.add(item);
			}while(c.moveToNext());
		}		
		db.close();			
	}
	
	public static class EjeAdapter extends BaseAdapter{
		
		private Context context;
		
		public EjeAdapter(Context c){
			
			context = c;	
		}

		public int getCount() {
			
			return EntreArray.size();
		}

		public Object getItem(int position) {
			
			return EntreArray.get(position);
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		public View getView(int position,View convertView,ViewGroup parent) {
			
			View view = null;
			
			if(convertView== null){
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(R.layout.entrelistitem, null);
			}else{
				view = convertView;				
			}	
			
			ImageView img = (ImageView) view.findViewById(R.id.logoEntre);
			img.setImageDrawable(context.getResources().getDrawable(EntreArray.get(position).iconoEntre));
			
			TextView tituloEntre = (TextView) view.findViewById(R.id.LblTitEntre);
			tituloEntre.setText(EntreArray.get(position).titEntre);			
			
			TextView tiempo = (TextView) view.findViewById(R.id.LblMinEntre);
			tiempo.setText(EntreArray.get(position).tiempoEntre);
			
			TextView numEntre = (TextView) view.findViewById(R.id.LblNumEntre);
			numEntre.setText(EntreArray.get(position).numEntre);
			
			TextView intensidad = (TextView) view.findViewById(R.id.LblIntesidad);
			intensidad.setText(EntreArray.get(position).intenEntre);
			String Intens = intensidad.getText().toString();
			
			if(Intens.charAt(0)=='a'){
				intensidad.setTextColor(Color.RED);
			}else if(Intens.charAt(0)=='m'){
				intensidad.setTextColor(Color.rgb(204, 112, 00));
			}else{
				intensidad.setTextColor(Color.GREEN);
			}
			
			//Textos fijos
			TextView txtNumEntre = (TextView) view.findViewById(R.id.LbltxtNumEntre);
			TextView txtTiempo = (TextView) view.findViewById(R.id.lblTiempoEntre);
			TextView txtIntensidad = (TextView) view.findViewById(R.id.lblIntensidadTxt);

			return view;
		}		
	}
}


