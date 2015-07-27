	package com.faltapan.activities;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityPrincipal extends Activity {
	
	TextView a_txv_0;
	TextView a_txv_1;
	TextView a_txv_2;
	TextView a_txv_3;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_activity_principal);
		
		InicializaBoton(a_txv_0,R.id.A_TXV_ID0,null);
		InicializaBoton(a_txv_1,R.id.A_TXV_ID1,ListadoDeListas.class);
		InicializaBoton(a_txv_2,R.id.A_TXV_ID2,null);
		InicializaBoton(a_txv_3,R.id.A_TXV_ID3,null);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_principal, menu);
		return true;
	}
	
	public void InicializaBoton(TextView tv,int rid,final Class<?> cls)
	{
		tv = (TextView) findViewById(rid);	
		Typeface font = Typeface.createFromAsset(getAssets(), "rabiohead.ttf");
		tv.setTypeface(font);
		tv.setOnClickListener(GetOnClickListener(cls));
	}
	
	public OnClickListener GetOnClickListener(final Class<?> cls){
		OnClickListener onclicklistener = new OnClickListener() {
			public void onClick(View view) {
				if(cls!=null){
					IntentLouncher(cls);
				}
		    }
		};
		return onclicklistener ;
	}
	
	public void IntentLouncher(Class<?> cls){
		Intent intent = new Intent(ActivityPrincipal.this,cls);
		startActivity(intent);	
	}
	
}
