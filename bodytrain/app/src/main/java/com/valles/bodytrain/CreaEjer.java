package com.valles.bodytrain;

import java.util.ArrayList;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

public class CreaEjer extends Activity {
	
	private BodyDB  bodyDB = new BodyDB(this,"BDBodytrain",null,19);
	private static ArrayList<EjerItemImg> EjerImgArray =  new ArrayList<EjerItemImg>();
	
	private ImgAdapter imgadaptador = null;
	
	private static Integer imageselect ;
	private static Integer serselect ;
	private static Integer repselect ;
	private static Integer pesselect ;
 
	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.creaejer);
	        CargaDatos();
	        
	        LinearLayout Portada = (LinearLayout) findViewById(R.id.portcreaeje);
	        Portada.setBackgroundColor(Color.WHITE);
	        
	        final EditText ejernomedit = (EditText)findViewById(R.id.creanomeje);  
	        final Spinner ejerimgspin = (Spinner)findViewById(R.id.spimgejer);
	        final SeekBar ejerserbar = (SeekBar)findViewById(R.id.barserejer);
	        final TextView ejerserlbl = (TextView)findViewById(R.id.lblserejer);
	        final SeekBar ejerrepbar = (SeekBar)findViewById(R.id.barrepejer);
	        final TextView ejerreplbl = (TextView)findViewById(R.id.lblrepejer);
	        final SeekBar ejerpesbar = (SeekBar)findViewById(R.id.barpesejer);
	        final TextView ejerpeslbl = (TextView)findViewById(R.id.lblpesejer);
	        final ImageButton btnguardaeje = (ImageButton) findViewById(R.id.btngrabaeje);
	        
	        imgadaptador = new ImgAdapter(this);    
	        ejerimgspin.setAdapter(imgadaptador);

	        ejerimgspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
	        		public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
	        			imageselect = EjerImgArray.get(position).iconoEjer;
	        		}
	        		
	        		public void onNothingSelected(AdapterView<?> parent) {
	        			imageselect = 0;
	        		}
	        });
	        
	        ejerserbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
						
					ejerserlbl.setText(progress+"");								
					serselect = progress;
					
				}
				
				public void onStartTrackingTouch(SeekBar arg0) {
		 
				}
				
				public void onStopTrackingTouch(SeekBar seekBar) {
				
				}
			});
	        
	        ejerrepbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
						
					ejerreplbl.setText(progress+"");								
					repselect = progress;
					
				}
				
				public void onStartTrackingTouch(SeekBar arg0) {
		 
				}
				
				public void onStopTrackingTouch(SeekBar seekBar) {
				
				}
			});
	        
	        ejerpesbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
						
					ejerpeslbl.setText(progress+"Kg");								
					pesselect = progress;
					
				}

				public void onStartTrackingTouch(SeekBar arg0) {
		 
				}
				
				public void onStopTrackingTouch(SeekBar seekBar) {
				
				}
			});

	        btnguardaeje.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {           	
	            	GrabaDatos(ejernomedit.getText().toString());            	
	            }
	        });
	 }
	 
	 public class EjerItemImg{
			public Integer iconoEjer;		
	 }
	 
	 public static class ImgAdapter extends BaseAdapter{
			
			private Context context;
			
			public ImgAdapter(Context c){
				
				context = c;	
			}

			public int getCount() {
				
				return EjerImgArray.size();
			}

			public Object getItem(int position) {
				
				return EjerImgArray.get(position);
			}

			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}

			public View getView(int position,View convertView,ViewGroup parent) {
				
				View view = null;
				
				if(convertView== null){
					LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					view = inflater.inflate(R.layout.ejerimglist, null);
				}else{
					view = convertView;				
				}	
				
				ImageView img = (ImageView) view.findViewById(R.id.imageitem);
				img.setImageDrawable(context.getResources().getDrawable(EjerImgArray.get(position).iconoEjer));

				return view;
			}	
		}
	 
	 private void CargaDatos(){
			
		 Cursor c = null;
			EjerImgArray.clear();
	
			SQLiteDatabase db = bodyDB.getReadableDatabase();
							
			if(db!=null){c = db.rawQuery("SELECT imagename FROM Imagenejer" ,null);}
			
			if(c.moveToFirst()){
				do{
					EjerItemImg item = new EjerItemImg();
				
					item.iconoEjer = c.getInt(0);
	
					EjerImgArray.add(item);
					
				}while(c.moveToNext());
			}			
			db.close();						
	}
	 
	 private void GrabaDatos(String tit){

		 SQLiteDatabase wdb = bodyDB.getWritableDatabase();
		 	
			 if(wdb!=null){
				 wdb.execSQL("INSERT INTO Ejercicios (ejetit,ejeimg,ejeseries,ejerep,ejepeso,ejetime) VALUES ('"+
						 tit +"',"+ imageselect +","+ serselect +","+ repselect +","+ pesselect+",90)");			 
				 wdb.close();
				 
				 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);  
			     dialogo1.setTitle("Ejercicio guardado correctamente");  
			     dialogo1.setMessage("¿Desea crear otro ejercicio?");            
			     dialogo1.setCancelable(false);  
			    
			     dialogo1.setPositiveButton("Si", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialogo1, int id) {  
			            Intent intent = new Intent(CreaEjer.this, CreaEjer.class);
			             startActivity(intent);
			        }  
			     });  
			        
			     dialogo1.setNegativeButton("No", new DialogInterface.OnClickListener() {  
			         public void onClick(DialogInterface dialogo1, int id) {  
			            Intent intent = new Intent(CreaEjer.this, RutEdit.class);
			            startActivity(intent);
			         }  
			     });
		        
			     dialogo1.show();
			 }        
	 }
 }