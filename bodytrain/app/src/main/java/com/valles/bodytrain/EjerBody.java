package com.valles.bodytrain;

import java.util.ArrayList;

import com.valles.bodytrain.EjerList.EjeAdapter;
import com.valles.bodytrain.EjerList.EjerItemList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class EjerBody extends Activity{
	
	BodyDB  bodyDB = new BodyDB(this,"BDBodytrain",null,19);
	private static ArrayList<EjerItemList> EjerArray =  new ArrayList<EjerItemList>();
	private EjeAdapter adaptador = null;
	
	private TextView Cuello;
	private TextView Trapecio;
	private TextView Pectoral;
	private TextView Hombros;
	private TextView Biceps;
	private TextView Triceps;
	private TextView Abs;
	private TextView Dorsales;
	private TextView Lumbares;
	private TextView AntBrazo;
	private TextView Gluteos;
	private TextView Cuadriceps;
	private TextView Isquiotibial;
	private TextView Gemelos;
	private TextView Estiramientos;
	private TextView Cardio;
	
	private String Musculo;

	public void onCreate(Bundle savedInstanceState) {
        
		super.onCreate(savedInstanceState);
        setContentView(R.layout.bodyselect);
        Toast.makeText(this,"Seleccione Grupo y Ejercicios",Toast.LENGTH_LONG).show();
        
        final LinearLayout btnSalir = (LinearLayout)findViewById(R.id.btnsalir);
        final LinearLayout btnEjercicios = (LinearLayout)findViewById(R.id.btnejercicios);
        final LinearLayout btnComenzar = (LinearLayout)findViewById(R.id.btncomenzar);
        
        Cuello = (TextView) findViewById(R.id.lblcuello); 
        Trapecio = (TextView) findViewById(R.id.lbltrapecio);
        Hombros = (TextView) findViewById(R.id.lblhombros);
        Pectoral = (TextView) findViewById(R.id.lblpectoral);
        Biceps = (TextView) findViewById(R.id.lblbiceps); 
        Triceps = (TextView) findViewById(R.id.lbltriceps);
        Abs = (TextView) findViewById(R.id.lblabdom);
        Dorsales = (TextView) findViewById(R.id.lbldorsales);
        Lumbares = (TextView) findViewById(R.id.lbllumbares); 
        AntBrazo = (TextView) findViewById(R.id.lblantbra);
        Gluteos = (TextView) findViewById(R.id.lblgluteos);
        Cuadriceps = (TextView) findViewById(R.id.lblcuadriceps);
        Isquiotibial = (TextView) findViewById(R.id.lblisquiotibial);
        Gemelos = (TextView) findViewById(R.id.lblgemelos);
        Estiramientos = (TextView) findViewById(R.id.lblestiramientos);
        Cardio = (TextView) findViewById(R.id.lblcardio);
        
        Context context;
        
        Cuello.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {  	
        		CargaDatos("cuello");
        		Musclist("cuello");
        	}  
        });
        
        Trapecio.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {  	
        		CargaDatos("trapecio");
        		Musclist("trapecio");
        	}  
        });
        
        Hombros.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {  	
        		CargaDatos("hombros");
        		Musclist("hombros");
        	}  
        });
        
        Pectoral.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {  	
        		CargaDatos("pectoral");
        		Musclist("pectoral");
        	}  
        });
        
        Biceps.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {  	
        		CargaDatos("biceps");
        		Musclist("biceps");
        	}  
        });
        
        Triceps.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {  	
        		CargaDatos("triceps");
        		Musclist("triceps");
        	}  
        });
        
        Abs.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {  	
        		CargaDatos("abs");
        		Musclist("abs");
        	}  
        });
        
        Dorsales.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {  	
        		CargaDatos("dorsales");
        		Musclist("dorsales");
        	}  
        });
        
        Lumbares.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {  	
        		CargaDatos("lumbares");
        		Musclist("lumbares");
        	}  
        });
        
        AntBrazo.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {  	
        		CargaDatos("antbrazo");
        		Musclist("antbrazo");
        	}  
        });
        
        Gluteos.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {  	
        		CargaDatos("gluteos");
        		Musclist("gluteos");
        	}  
        });
        
        Cuadriceps.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {  	
        		CargaDatos("cuadriceps");
        		Musclist("cuadriceps");
        	}  
        });
        
        Isquiotibial.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {  	
        		CargaDatos("isquiotibial");
        		Musclist("isquiotibial");
        	}  
        });
        
        Estiramientos.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {  	
        		CargaDatos("estiramiento");
        		Musclist("estiramiento");
        	}  
        });
        
        Cardio.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {  	
        		CargaDatos("cardio");
        		Musclist("cardio");
        	}  
        });
        
        Gemelos.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {  	
        		CargaDatos("gemelo");
        		Musclist("gemelo");
        	}  
        });
        
        btnSalir.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Salir();
        	}  
        });
        
        btnEjercicios.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
      		
        		CargaDatos("rutina");
        		Musclist("rutina");
        	}  
        });
        
        btnComenzar.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		
        		//1Comprobar que hay ejercicios en la rutina, si no los hay simplemente informar "no ha seleccionado ningun ejercicio"
    		
        		Intent intent = new Intent(EjerBody.this,EjerList.class);
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
		
		SQLiteDatabase getEstado = bodyDB.getReadableDatabase();
		
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
		Intent intent = new Intent(EjerBody.this,EntreList.class);
		startActivity(intent);
	}
	
	public void AMisRutinas(){
		Intent intent = new Intent(EjerBody.this,MisRutinas.class);
		startActivity(intent);
	}
	
	public void AMain(){
		Intent intent = new Intent(EjerBody.this,Main.class);
		startActivity(intent);
	}
	
	public void SetEstadoRutina(){
		Cursor c=null;
		SQLiteDatabase WDB = bodyDB.getReadableDatabase();       			
		if(WDB!=null){
			c = WDB.rawQuery("SELECT * FROM Estado",null);
			c.moveToFirst();
		}
		WDB.close();
		
		WDB = bodyDB.getWritableDatabase();       		
		WDB.execSQL("DELETE FROM Rutina");
		WDB.execSQL("DELETE From Estado");
		WDB.execSQL("INSERT INTO Estado (est_id,est_titulo,est_rutina,est_entrada) VALUES(0,'Sin Titulo','false','false')");
		WDB.close();

		if(c.getString(3).charAt(0)=='f'){ARutinasGuiadas();}
		else{AMisRutinas();}	
	}
	
	public void PopSalir(){

		final AlertDialog.Builder grouplist = new AlertDialog.Builder(this);
		View botones = LayoutInflater.from(this).inflate(R.layout.popsalir,(ViewGroup) findViewById(R.id.dialogsalir));
		final LinearLayout ContRutina = (LinearLayout) botones.findViewById(R.id.btncontrut);
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
	
	public void Musclist(String musculo){
		
		Musculo = musculo;
		final AlertDialog.Builder grouplist = new AlertDialog.Builder(this);
		final ListView list = new ListView(this);		
		
		 adaptador = new EjeAdapter(this);
	     list.setAdapter(adaptador);
	     
	     list.setOnItemClickListener(new OnItemClickListener() {
	        	public void onItemClick(AdapterView<?> a, View v, int position, long id) {       		

	        		SQLiteDatabase WDB = bodyDB.getWritableDatabase();
	        		if(WDB!=null){
		        		if(Musculo!="rutina"){
		        			String sql = "INSERT INTO Rutina (ejeid,ejetit,ejeimg,ejeseries,seriescatual,pesoserie1,repserie1,timerepet) VALUES("+EjerArray.get(position).idejer +",'"+EjerArray.get(position).titEjer.toString()+"',"+EjerArray.get(position).iconoEjer+","+EjerArray.get(position).numSeries+",0,"+EjerArray.get(position).pesoEjer+","+EjerArray.get(position).numRepet+","+EjerArray.get(position).tiempoEjer +")";
			        		WDB.execSQL(sql);
			        		Toast.makeText(EjerBody.this,"Ejercicio "+ EjerArray.get(position).titEjer.toString() +" agregado a la Rutina",Toast.LENGTH_LONG).show();
		        		}else{
		        			String sql ="DELETE FROM Rutina WHERE ejeid='"+EjerArray.get(position).idejer+"'";		        		
			        		WDB.execSQL(sql);
			        		Toast.makeText(EjerBody.this,"Ejercicio "+ EjerArray.get(position).titEjer.toString() +" borrado de la Rutina",Toast.LENGTH_LONG).show();
		        		}
	        		}	        		
	        		WDB.close();
	        		EjerArray.remove(position);
	        		adaptador.notifyDataSetChanged();	
	        	}
	        });
				
		grouplist.setIcon(R.drawable.pesoico);
		grouplist.setTitle("Ejercicios de "+ Musculo);
		grouplist.setView(list);

		if(Musculo!="rutina"){	
			grouplist.setMessage("Pulse para Agregar");
			grouplist.setPositiveButton("Cerrar Lista",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				} 
			});	
			
			grouplist.setNegativeButton("Comenzar Rutina", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Intent intent = new Intent(EjerBody.this,EjerList.class);
					startActivity(intent);	
				} 
			});		
		}else{
			grouplist.setMessage("Pulse para Eliminar");
			grouplist.setPositiveButton("Cerrar ",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				} 
			});	
			
			grouplist.setNegativeButton("Guardar Rutina", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					GuardarRutinaPop(); //en EjerList debe de haber otro boton como este para guardar la rutina en Mis rutinas.		
				} 
			});	
		}
		grouplist.create();
		grouplist.show();
	}
	
	public void Guardahistorial(){	
		
		Cursor r = null;
		SQLiteDatabase RDB = bodyDB.getReadableDatabase();       			
		if(RDB!=null){
			r = RDB.rawQuery("SELECT * FROM Rutina",null);
			r.getCount();
		}				
		RDB.close();
		
		SQLiteDatabase WDB = bodyDB.getWritableDatabase();
		if(WDB!=null){
			if(r.moveToFirst()){
				do{
					WDB.execSQL("INSERT INTO Historial(histdate,histtit,histimg,histseries,histseriescatual,histpesoserie1,histrepserie1,histpesoserie2,histrepserie2,histpesoserie3,histrepserie3,histtimerepet,histejegrupo)"+
							" VALUES ('"+ new java.sql.Date(new java.util.Date().getTime()) +"','"+r.getString(1) +"',"+r.getInt(2) +","+ r.getInt(3) +","+ r.getInt(4) +","+ r.getInt(5) +","+ r.getInt(6)+"," + r.getInt(7)+","+ r.getInt(8) +","+ r.getInt(9)+"," + r.getInt(10)+","+ r.getInt(11) +","+ r.getString(12) +")");
				}while(r.moveToNext());
			}
		}
		WDB.close();
		SetEstadoRutina();
	}
	
	public void GuardarRutinaPop(){
		Cursor r = null;
		SQLiteDatabase RDB = bodyDB.getReadableDatabase();       			
		if(RDB!=null){
			r = RDB.rawQuery("SELECT ejeid FROM Rutina",null);
			if(r.moveToFirst()){
				RDB.close();
				final AlertDialog.Builder grouplist = new AlertDialog.Builder(this);
				final EditText NombreRut = new EditText(this);
				
				grouplist.setIcon(R.drawable.pesoico);
				grouplist.setTitle("Guardando Rutina");
				grouplist.setMessage("Indique un nombre para la Rutina:");
				grouplist.setView(NombreRut);
				
				grouplist.setPositiveButton("Guardar",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						GuardarRutina(NombreRut.getText()+"");
						SetEstadoRutina();						
					} 
				});
				
				grouplist.setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					} 
				});	
				grouplist.create();
				grouplist.show();
				
			}else{
				RDB.close();
				final AlertDialog.Builder grouplist = new AlertDialog.Builder(this);
				grouplist.setIcon(R.drawable.pesoico);
				grouplist.setTitle("Atencion");
				grouplist.setMessage("Esta rutina no contiene ejercicios.\n\nPara Guardar la Rutina, debe contener al menos un ejercicio");
				grouplist.setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					} 
				});		
				grouplist.create();
				grouplist.show();
			}
		}				
		/*
		 * 1) Comprueba si hay ejercicios a)Si no hay informa, b) si los hay habre popup para ponerle el nombre
		 * 2) Debe poner un nombre, y presionar guardar, esto creara una nuevo entrenamiento, con los ejercicios elejidos asociados a ella, se marcara como mis rutinas
		*/	
	}
	
	public void GuardarRutina(String titulo){
		Cursor r = null;
		Cursor e = null;
		int Nrut=0;
		SQLiteDatabase RDB = bodyDB.getReadableDatabase();
		SQLiteDatabase WDB = bodyDB.getWritableDatabase();
		if(RDB!=null){
			if(WDB!=null){
				r = RDB.rawQuery("SELECT ejeid FROM Rutina",null);
				if(r.moveToFirst()){
					Nrut = r.getCount();
					WDB.execSQL("INSERT INTO Entrenamientos(entretit,entreimg,entreint,entretime,entrenum,entretipo) VALUES ('"+ titulo +"',2130837504,'baja',300,"+Nrut+",'true')");
					e = RDB.rawQuery("SELECT entreid FROM Entrenamientos WHERE entretit='"+titulo+"'",null);
					if(e.moveToFirst()){
						do{
							String sql= "INSERT INTO Relacion (id_rutina,id_ejercicio) VALUES ("+e.getInt(0)+","+r.getInt(0) +")";
							WDB.execSQL(sql);
							Log.e("Body",sql);
						}while(r.moveToNext());
						Toast.makeText(EjerBody.this,"La Rutina agregada a 'Mis Rutinas'",Toast.LENGTH_LONG).show();
					}
				}
			}	
		}
		RDB.close();
		WDB.close();		
	}

	private void  CargaDatos(String grupo){
		
		String Grupo = grupo;
		Cursor c = null;
		EjerArray.clear();
			
		SQLiteDatabase RDB = bodyDB.getReadableDatabase();
	
		if(RDB!=null){
			
			if(grupo!="rutina"){c = RDB.rawQuery("SELECT ejeid,ejetit,ejeimg,ejeseries,ejerep,ejepeso,ejetime,ejegrupo FROM Ejercicios WHERE ejegrupo ='"+ Grupo +"'",null);}
			else{c = RDB.rawQuery("SELECT * FROM Rutina",null);}
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
				item.grupoeje = c.getString(7);
				item.serieactu = 0;
				
				EjerArray.add(item);

			}while(c.moveToNext());
		}			
		RDB.close();	
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
		public String grupoeje;
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
			
			return 0;
		}

		public View getView(int position,View convertView,ViewGroup parent) {
			
			View view = null;
			
			if(convertView== null){
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(R.layout.itembodylist, null);
			}else{
				view = convertView;				
			}	
			
			ImageView img = (ImageView) view.findViewById(R.id.bodyimg);
			img.setImageDrawable(context.getResources().getDrawable(EjerArray.get(position).iconoEjer));
						
			TextView tituloEjer = (TextView) view.findViewById(R.id.bodytxt);
			tituloEjer.setText(EjerArray.get(position).titEjer);			
						
			/*TextView series = (TextView) view.findViewById(R.id.LblSeries);
			series.setText(EjerArray.get(position).serieactu+"/" + EjerArray.get(position).numSeries.toString());
						
			TextView repeticiones = (TextView) view.findViewById(R.id.LblRepet);
			repeticiones.setText(EjerArray.get(position).numRepet.toString());
						
			TextView peso = (TextView) view.findViewById(R.id.LblPeso);
			if(EjerArray.get(position).pesoEjer==0){peso.setText("Sin peso");}				
			else{peso.setText(EjerArray.get(position).pesoEjer.toString()+"Kg");}
			
			
			TextView tiempo = (TextView) view.findViewById(R.id.Lbltiempo);
			tiempo.setText(EjerArray.get(position).tiempoEjer.toString()+"s");*/
		
			return view;
		}		
	}
}
