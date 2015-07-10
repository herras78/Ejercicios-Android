package com.valles.bodytrain;

import java.util.ArrayList;

import com.valles.bodytrain.EntreList.EntreItemList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class RutEdit extends Activity {
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.rutedit);
	        
	        LinearLayout Portada = (LinearLayout) findViewById(R.id.portediteje);
	        Portada.setBackgroundColor(Color.WHITE);
	        	        
	        
	        final ImageButton btnCreaEje = (ImageButton) findViewById(R.id.btncreaeje);
	        
	      
	        btnCreaEje.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	            	Intent intent = new Intent(RutEdit.this, CreaEjer.class);
	            	startActivity(intent);
	            }
	        });
	        
	       

	 }
}