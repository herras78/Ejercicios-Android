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
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class EjerList extends Activity {
	
	private BodyDB  AccesoDB = new BodyDB(this,"BDBodytrain",null,19);
	
	private ProgressBar entreprogres;
	private TextView Porcent;
	private TextView lbltitulo;
	private LinearLayout btnterminar;
	private LinearLayout btnagregar;
	
	private static ArrayList<EjerItemList> EjerArray =  new ArrayList<EjerItemList>();
	private EjeAdapter adaptador = null;
	int idEntre=0;
	String EstadoRutina;
	String Procede;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejerlist);
        
        btnterminar = (LinearLayout)findViewById(R.id.btnterminar);
        btnagregar = (LinearLayout)findViewById(R.id.btnagregar);
        lbltitulo = (TextView) findViewById(R.id.tituloentre);
        entreprogres = (ProgressBar) findViewById(R.id.entrelistprogres);
        Porcent = (TextView) findViewById(R.id.porcententre);
              
        GetEstado();
        CargaDatos();

        ListView ejerList = (ListView)findViewById(R.id.Lstrealizaentre);
        adaptador = new EjeAdapter(this);
        ejerList.setAdapter(adaptador);
             
        ejerList.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> a, View v, int position, long id) {       		
        		
        		Toast.makeText(EjerList.this,EjerArray.get(position).titEjer.toString(),Toast.LENGTH_SHORT).show();
        		
        		Bundle bundle = new Bundle();
        		bundle.putInt("idejer", EjerArray.get(position).idejer);
        		bundle.putString("titejer", EjerArray.get(position).titEjer.toString());
        		bundle.putInt("imgejer", EjerArray.get(position).iconoEjer);
        		bundle.putInt("serejer", EjerArray.get(position).numSeries);
        		bundle.putInt("repejer", EjerArray.get(position).numRepet);
        		bundle.putInt("pesoejer", EjerArray.get(position).pesoEjer);
        		bundle.putInt("seractu", EjerArray.get(position).serieactu);

        		Intent intent = new Intent(EjerList.this, RealizaEjer.class); 
        		intent.putExtras(bundle);
                startActivity(intent);
        	}
        });
        
        btnterminar.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {       		
        		Salir();
        	}  
        });
        
        btnagregar.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Intent intent = new Intent(EjerList.this, EjerBody.class);
        		startActivity(intent);
        	}  
        });
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Salir(); 
           return true;
        }
        return false;
    }
	
	public void Salir(){
		Cursor rutina = null;
		Cursor estado = null;
		
		SQLiteDatabase getEstado = AccesoDB.getReadableDatabase();
		
		if(getEstado!=null){
			rutina = getEstado.rawQuery("SELECT * FROM Rutina" ,null);
			estado = getEstado.rawQuery("SELECT * FROM Estado" ,null);
			estado.moveToFirst();
		}
		if(!rutina.moveToFirst()){
			getEstado.close();
			SetEstadoRutina();
		}else{
			getEstado.close();
			PopSalir();
		}
	}
	
	public void ARutinasGuiadas(){
		Intent intent = new Intent(EjerList.this,EntreList.class);
		startActivity(intent);
	}
	
	public void AMisRutinas(){
		Intent intent = new Intent(EjerList.this,MisRutinas.class);
		startActivity(intent);
	}
	
	public void AMain(){
		Intent intent = new Intent(EjerList.this,Main.class);
		startActivity(intent);
	}
	
	public void SetEstadoRutina(){
		Cursor c=null;
		SQLiteDatabase WDB = AccesoDB.getReadableDatabase();       		
		
		if(WDB!=null){
			c = WDB.rawQuery("SELECT * FROM Estado",null);
			c.moveToFirst();
		}
		WDB.close();
		
		WDB = AccesoDB.getWritableDatabase();       		
		WDB.execSQL("DELETE FROM Rutina");
		WDB.execSQL("DELETE FROM Estado");
		WDB.execSQL("INSERT INTO Estado (est_id,est_titulo,est_rutina,est_entrada) VALUES(0,'Sin Titulo','false','false')");
		WDB.close();

		if(c.getString(3).charAt(0)=='f'){ARutinasGuiadas();}
		else{AMisRutinas();}	
	}
	
	public void PopSalir(){

		final AlertDialog.Builder grouplist = new AlertDialog.Builder(this);
		View botones = LayoutInflater.from(this).inflate(R.layout.popsalir,(ViewGroup) findViewById(R.id.dialogsalir));
		final LinearLayout ContRutina = (LinearLayout)botones.findViewById(R.id.btncontrut);
		final LinearLayout GuardarRutina = (LinearLayout)botones.findViewById(R.id.btnguardarut);
		final LinearLayout EliminarRutina = (LinearLayout)botones.findViewById(R.id.btnborrarut);

		ContRutina.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {  	
        		AMain();       		
        	}  
        });
		
		GuardarRutina.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {  	
        		Guardahistorial();
        	}  
        });
		
		EliminarRutina.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {  	
        		SetEstadoRutina();      		
        	}  
        });
		
		grouplist.setIcon(R.drawable.pesoico);
		grouplist.setTitle("¿Que desea hacer?");
		grouplist.setView(botones);
		grouplist.create();
		grouplist.show();
	}
	
public void Guardahistorial(){	
		long millis=0;
		Cursor r = null;
		SQLiteDatabase RDB = AccesoDB.getReadableDatabase();       			
		if(RDB!=null){
			r = RDB.rawQuery("SELECT * FROM Rutina",null);
			r.getCount();
		}				
		RDB.close();
		
		SQLiteDatabase WDB = AccesoDB.getWritableDatabase();
		if(WDB!=null){
			if(r.moveToFirst()){
				millis = new java.util.Date().getTime();
				do{
					WDB.execSQL("INSERT INTO Historial(histdate,histtit,histimg,histseries,histseriescatual,histpesoserie1,histrepserie1,histpesoserie2,histrepserie2,histpesoserie3,histrepserie3,histtimerepet,histejegrupo,histruttit)"+
							" VALUES ('"+ millis +"','"+r.getString(1) +"',"+r.getInt(2) +","+ r.getInt(3) +","+ r.getInt(4) +","+ r.getInt(5) +","+ r.getInt(6)+"," + r.getInt(7)+","+ r.getInt(8) +","+ r.getInt(9)+"," + r.getInt(10)+","+ r.getInt(11) +","+ r.getString(12) +",'"+lbltitulo.getText().toString()+"')");
				}while(r.moveToNext());//new java.sql.Date(new java.util.Date().getTime())
			}
		}
		WDB.close();
		
		SetEstadoRutina();
	}
	
	public void GuardarRutina(){
		Cursor r = null;
		SQLiteDatabase RDB = AccesoDB.getReadableDatabase();       			
		if(RDB!=null){
			r = RDB.rawQuery("SELECT * FROM Rutina",null);
			if(r.moveToFirst()){
				
			}else{	
				final AlertDialog.Builder grouplist = new AlertDialog.Builder(this);
				grouplist.setIcon(R.drawable.pesoico);
				grouplist.setTitle("Atencion");
				grouplist.setMessage("Esta rutina no contiene ejercicios");
				grouplist.setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					} 
				});		
				grouplist.create();
				grouplist.show();
			}
		}				
		RDB.close();
	}
		
	public void GetEstado(){
		
		Cursor estado = null;
		SQLiteDatabase getEstado = AccesoDB.getReadableDatabase();
		
		if(getEstado!=null){	
			estado = getEstado.rawQuery("SELECT * FROM Estado" ,null);
		}
		if(estado.moveToFirst()){
			idEntre = estado.getInt(0);
			lbltitulo.setText(estado.getString(1)+"");
			EstadoRutina = estado.getString(2);
			Procede = estado.getString(3);
			if(Procede.charAt(0)=='f'){btnagregar.setVisibility(View.GONE);}
			else{btnagregar.setVisibility(View.VISIBLE);}
		}
	}
	
	public class EjerItemList{
		public Integer idejer;
		public Integer iconoEjer;
		public String titEjer;
		public Integer tiempoEjer;
		public Integer numSeries;
		public Integer serieactu;
		public Integer numRepet;
		public Integer pesoEjer;			
	}
	
	private void  CargaDatos(){
		Cursor c = null;
		
		Log.e("Body",EstadoRutina);
		
		if(EstadoRutina.charAt(0)=='f'){	
			EjerArray.clear();
			
			SQLiteDatabase RDB = AccesoDB.getReadableDatabase();
			if(RDB!=null){	
				c = RDB.rawQuery("SELECT ejeid,ejetit,ejeimg,ejeseries,ejerep,ejepeso,ejetime FROM Ejercicios,Entrenamientos,Relacion "+
								"WHERE Relacion.id_rutina = " + idEntre  +" AND "+
								"Entrenamientos.entreid=Relacion.id_rutina AND Ejercicios.ejeid=Relacion.id_ejercicio;",null);
			}	
			
			if(c.moveToFirst()){
				do{
					EjerItemList item = new EjerItemList();
					
					item.idejer = c.getInt(0);
					item.titEjer = c.getString(1);
					item.iconoEjer = c.getInt(2);
					item.numSeries = c.getInt(3);
					item.numRepet = c.getInt(4);			
					item.pesoEjer = c.getInt(5);
					item.tiempoEjer = c.getInt(6);
					item.serieactu = 0;
					
					EjerArray.add(item);
				
				}while(c.moveToNext());
			}			
			RDB.close();
	
			SQLiteDatabase WDB = AccesoDB.getWritableDatabase();
			
			if(WDB!=null){
				if(c.moveToFirst()){
					WDB.execSQL("DELETE From Rutina");
					do{
						WDB.execSQL("INSERT INTO Rutina(ejeid,ejetit,ejeimg,ejeseries,seriescatual,timerepet,pesoserie1,repserie1) VALUES"+
									"("+ c.getInt(0)+",'"+c.getString(1) +"',"+c.getInt(2) +","+ c.getInt(3) +", 0 , 2,"+ c.getInt(5)+"," + c.getInt(4) +")");		
					}while(c.moveToNext());
				}
				WDB.execSQL("DELETE From Estado");
				WDB.execSQL("INSERT INTO Estado (est_id,est_titulo,est_rutina,est_entrada) VALUES("+ idEntre +",'"+ lbltitulo.getText() +"','true','"+ Procede +"')");	
			}	
			WDB.close();	
		
		}else{
			Cursor d = null;
			EjerArray.clear();
			int progreso = 0;
			int max = 0;
			
			SQLiteDatabase RDB = AccesoDB.getReadableDatabase();	
		
			if(RDB!=null){
				d = RDB.rawQuery("SELECT ejeid,ejetit,ejeimg,ejeseries,seriescatual,timerepet,pesoserie1,repserie1,pesoserie2,repserie2,pesoserie3,repserie3 FROM Rutina" ,null);
			}	

			if(d.moveToFirst()){				
				do{					
					EjerItemList item = new EjerItemList();
					
					item.idejer = d.getInt(0);
					item.titEjer = d.getString(1);
					item.iconoEjer = d.getInt(2);
					item.numSeries = d.getInt(3);
					item.serieactu = d.getInt(4);
					item.tiempoEjer = d.getInt(5);
					
					if(item.serieactu <=1){
						item.numRepet = d.getInt(7);			
						item.pesoEjer = d.getInt(6);
					}
					if(item.serieactu == 2){
						item.numRepet = d.getInt(9);			
						item.pesoEjer = d.getInt(8);
					}
					if(item.serieactu == 3){
						item.numRepet = d.getInt(11);			
						item.pesoEjer = d.getInt(10);
					}				
					progreso += d.getInt(4);
					max += d.getInt(3);
						
					EjerArray.add(item);
				}while(d.moveToNext());
				
				entreprogres.setMax(max);
				entreprogres.setProgress(progreso);
				Porcent.setText(((progreso*100)/max)+"%");
			}
			RDB.close();
		}
	}
	
public static class EjeAdapter extends BaseAdapter{
		
		private Context context;
		
		public EjeAdapter(Context c){
			
			context = c;	
		}

		public int getCount() {
			
			return EjerArray.size();
		}

		public Object getItem(int position) {
			
			return EjerArray.get(position);
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		public View getView(int position,View convertView,ViewGroup parent) {
			
			View view = null;
			
			if(convertView== null){
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(R.layout.ejerlistitem, null);
			}else{
				view = convertView;				
			}	
			
			ImageView img = (ImageView) view.findViewById(R.id.logoEjer);
			img.setImageDrawable(context.getResources().getDrawable(EjerArray.get(position).iconoEjer));
						
			TextView tituloEjer = (TextView) view.findViewById(R.id.LblTitEjer);
			tituloEjer.setText(EjerArray.get(position).titEjer);			
						
			TextView series = (TextView) view.findViewById(R.id.LblSeries);
			series.setText(EjerArray.get(position).serieactu+"/" + EjerArray.get(position).numSeries.toString());
						
			TextView repeticiones = (TextView) view.findViewById(R.id.LblRepet);
			repeticiones.setText(EjerArray.get(position).numRepet.toString());
						
			TextView peso = (TextView) view.findViewById(R.id.LblPeso);
			if(EjerArray.get(position).pesoEjer==0){peso.setText("Sin");}				
			else{peso.setText(EjerArray.get(position).pesoEjer.toString()+"Kg");}

			TextView tiempo = (TextView) view.findViewById(R.id.Lbltiempo);
			tiempo.setText(EjerArray.get(position).tiempoEjer.toString()+"s");
		
			return view;
		}		
	}
}

/*btnstart.setOnClickListener(new OnClickListener() {
    public void onClick(View v) {
    	
    	crono.setOnChronometerTickListener(
    			new Chronometer.OnChronometerTickListener(){
        			int hor = 0;
        			int min = 0;
            		int sec = 0;
            		String smin;
            		String ssec;
            		String shor;
            		
        			public void onChronometerTick(Chronometer chronometer) {
						
						long millis = SystemClock.elapsedRealtime() - chronometer.getBase();
						chronometer.setText(DateFormat.format("kk:mm:ss", millis));
						
						hor = (int) (((millis / 1000)/60)/60);
						min = (int) (((millis / 1000)/60)%60);
           	    	 	sec = (int) (((millis / 1000)%60)%60);
           	    	 
           	    	 	if(hor<10){shor= "0" + hor;}
        	    	 	else{shor= "" + hor;}
           	    	 	
           	    	 	if(min<10){smin= "0" + min;}
           	    	 	else{smin= "" + min;}
           	    	 
           	    	 	if(sec<10){ssec= "0" + sec;}
           	    	 	else{ssec= "" + sec;}
           	    	 
           	    	 	lblentretimer.setText(shor+":"+smin + ":" + ssec);			
					}
        		});
    	crono.start();	
    }
});*/
