package com.valles.bodytrain;

import com.valles.bodytrain.EntreList.EntreItemList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.graphics.Color;

public class Main extends Activity {
	
	BodyDB  bodyDB = new BodyDB(this,"BDBodytrain",null,19);
	
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);              
        
        final LinearLayout Rutguiada = (LinearLayout)findViewById(R.id.guirut);
        final LinearLayout MisRut = (LinearLayout)findViewById(R.id.misrut);
        final LinearLayout Historial = (LinearLayout)findViewById(R.id.btnhistorial);
        final ImageButton EjerEdit = (ImageButton)findViewById(R.id.btnedit);
     
        Rutguiada.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {          	
            	CheckEstado(true);
            }
        });
        
        MisRut.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	CheckEstado(false);        		
            }
        }); 
        
        Historial.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(Main.this, HistoriList.class);
            	
                startActivity(intent);      		
            }
        }); 
        
        EjerEdit.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(Main.this,RutEdit.class);
            	startActivity(intent);
            }
        });       
    }
    
    public void CheckEstado(boolean b){
    	
		Cursor c=null;
		SQLiteDatabase WDB = bodyDB.getReadableDatabase();       		
		c = WDB.rawQuery("SELECT * FROM Estado",null);
		c.moveToFirst();
		WDB.close();	
		if(c.getString(2).charAt(0)=='t'){
			//Falta preguntarle que desea hacer, continuar con el entrenamiento o comenzar uno nuevo
			Intent intent = new Intent(Main.this, EjerList.class);     
            startActivity(intent);
		}else{
			if(b==true){
            	Intent intent = new Intent(Main.this, EntreList.class);
            	startActivity(intent);
			}else{
				Intent intent = new Intent(Main.this, MisRutinas.class);
        		startActivity(intent);
			}
		}
	}
}