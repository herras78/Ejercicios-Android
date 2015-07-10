package com.valles.bodytrain;

import java.sql.Date;
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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class HistoriList extends Activity {
	private BodyDB  bodyDB = new BodyDB(this,"BDBodytrain",null,19);
	private static ArrayList<EntreItemList> EntreArray =  new ArrayList<EntreItemList>();
	private EjeAdapter adaptador = null;	
		
	public void onCreate(Bundle SavedInstanceState){
		super.onCreate(SavedInstanceState);
		setContentView(R.layout.histolist);
		
		CargaDatos();
		
		ListView ejerList = (ListView)findViewById(R.id.lsthistorial);
        adaptador = new EjeAdapter(this);
        ejerList.setAdapter(adaptador);
        
        ejerList.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> a, View v, int position, long id) {       		
        		/*Intent intent = new Intent(EntreList.this, EjerList.class);
                startActivity(intent);*/		
        	}
        });
	}	

	private void CargaDatos(){
		Cursor c = null;
		EntreArray.clear();

		SQLiteDatabase RDB = bodyDB.getReadableDatabase();
			
		if(RDB!=null){//(strftime('%s',histdate)*1000)
			c = RDB.rawQuery("SELECT histdate,histruttit,histimg FROM Historial Group By histdate",null);
		}
		
		if(c.moveToFirst()){
			do{
				EntreItemList item = new EntreItemList();
				
				item.dateEntre =  c.getLong(0);//Revista esto....
				item.titEntre = c.getString(1);
				item.iconoEntre = c.getInt(2);

				EntreArray.add(item);
				Log.e("Body", c.getLong(0)+","+c.getString(1)+","+c.getInt(2));
			}while(c.moveToNext());
		}		
		RDB.close();			
	}
	
	public class EntreItemList{		
		public long dateEntre;
		public int iconoEntre;
		public String titEntre;			
	}
		
	private static class EjeAdapter extends BaseAdapter{
		
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
				view = inflater.inflate(R.layout.histolistitem, null);
			}else{
				view = convertView;				
			}	
			
			ImageView img = (ImageView) view.findViewById(R.id.logohisto);
			img.setImageDrawable(context.getResources().getDrawable(EntreArray.get(position).iconoEntre));
			
			TextView tituloEntre = (TextView) view.findViewById(R.id.lblhistotit);
			tituloEntre.setText(EntreArray.get(position).titEntre);			
			
			TextView tiempo = (TextView) view.findViewById(R.id.lblhistodate);
			tiempo.setText("" + new Date(EntreArray.get(position).dateEntre));

			return view;
		}		
	}	
		
	}
