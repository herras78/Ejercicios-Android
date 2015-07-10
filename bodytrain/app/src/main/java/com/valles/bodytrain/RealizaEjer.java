package com.valles.bodytrain;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class RealizaEjer extends Activity{
	
	private BodyDB  AccesoDB = new BodyDB(this,"BDBodytrain",null,19);
	private TextView lblPeso;
	private TextView lblRepet;
	private int seractual;
	private int varpeso;
	
	private TextView ejerTit;
	private ImageView icoejer;
	private TextView ejerser; 
	private CheckBox Chk1;
	private CheckBox Chk2;
	private CheckBox Chk3;
	private CheckBox Chk4;
	private TextView repcont;
	private ProgressBar barcont;  
	private TextView ejerpes;
	private TextView ejerrep;
	private ToggleButton start;
    private Bundle bundle; 
    
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.realizaejer);
        bundle = getIntent().getExtras();
        Context context = this;
        seractual=bundle.getInt("seractu");
        
        ejerTit = (TextView) findViewById(R.id.lbltitreaeje);
        icoejer= (ImageView) findViewById(R.id.imgreaeje);
        ejerser = (TextView) findViewById(R.id.lblserreaeje);
         
        Chk1 = (CheckBox) findViewById(R.id.chkeje1);
        Chk2 = (CheckBox) findViewById(R.id.chkeje2);
        Chk3 = (CheckBox) findViewById(R.id.chkeje3);
        Chk4 = (CheckBox) findViewById(R.id.chkeje4);
        Chk1.setClickable(false);
        Chk2.setClickable(false);
        Chk3.setClickable(false);
        Chk4.setClickable(false);
     
        repcont = (TextView) findViewById(R.id.lblrepcont);
        barcont = (ProgressBar) findViewById(R.id.barrepcont);
         
        ejerpes = (TextView) findViewById(R.id.lblpesvar);       
        ejerrep = (TextView) findViewById(R.id.lblrepvar);
        start = (ToggleButton)findViewById(R.id.btnstarteje);
                                     
        ejerTit.setText( bundle.getString("titejer"));
  
        icoejer.setImageDrawable(context.getResources().getDrawable(bundle.getInt("imgejer")));
      
        ejerser.setText("Serie: "+ seractual +"/"+bundle.getInt("serejer")); 
        if(bundle.getInt("serejer")==3)Chk4.setVisibility(View.INVISIBLE);
        else if(bundle.getInt("serejer")==2){Chk4.setVisibility(View.INVISIBLE);Chk3.setVisibility(View.INVISIBLE);}
        else if(bundle.getInt("serejer")==1){Chk4.setVisibility(View.INVISIBLE);Chk3.setVisibility(View.INVISIBLE);Chk2.setVisibility(View.INVISIBLE);}
        check();
        
        ejerpes.setText( bundle.getInt("pesoejer")+"Kg");
        varpeso = bundle.getInt("pesoejer");
        ejerrep.setText( bundle.getInt("repejer")+"");
        barcont.setMax(Integer.parseInt((String) ejerrep.getText()));
        
        check();
        
        start.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	start.setClickable(false);
            	contador(Integer.parseInt((String) ejerrep.getText()));    	 	
            }
        });
               
        //lblRepet = (TextView)findViewById(R.id.lblrepvar);
        ejerrep.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		PopSeek(false);
        	}  
        });
        
        //lblPeso = (TextView)findViewById(R.id.lblpesvar);
        ejerpes.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {  	
        		PopSeek(true);
        	}  
        });
    
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        	
        	back();
            
           return true;
        }
        return false;
    }
	
	public void back(){
		Intent intent = new Intent(RealizaEjer.this, EjerList.class);
  		 
        Bundle bundle = new Bundle();
        bundle.putBoolean("argrut", false);
        intent.putExtras(bundle);
 
        startActivity(intent);	
	}
	
	public void contador(int repet){
		
		new CountDownTimer((repet+4)*2000, 2000) {  	
			int cont=0;
			int revcont=3;
			
    		 public void onTick(long millisUntilFinished) {   			
    			 cont++;
    			 
    			 if(cont<4){
    				 if(cont != 0)Toast.makeText(RealizaEjer.this,"Comienza en "+ revcont,Toast.LENGTH_SHORT).show();
    				 revcont--;
    			 }else{
    				 
    				 if((cont-3)<10)repcont.setText( "00"+(cont-3) );
    				 else if(cont-3<100)repcont.setText( "0"+(cont-3));
	    			 barcont.setProgress((cont-3));
    			 }
    		 }
    		 public void onFinish() {
    			seractual++;
			
    			if(seractual< bundle.getInt("serejer")){
    				Toast.makeText(RealizaEjer.this,"Serie terminada!!",Toast.LENGTH_LONG).show();
    				check();
    				repcont.setText("000");
        			barcont.setProgress((0));
        			start.setClickable(true);
        			upDate();
    			}else{
    				Toast.makeText(RealizaEjer.this,"Ejercicio terminado!!",Toast.LENGTH_LONG).show();
    				check();
    				upDate();
    				back();
    			}			
    		 }
    	}.start();      
	}
	
	public void check(){
		
		if(seractual==1){Chk1.setChecked(true);ejerser.setText("Serie: "+ seractual +"/"+ bundle.getInt("serejer"));}
		else if(seractual==2){Chk2.setChecked(true);ejerser.setText("Serie: "+ seractual +"/"+ bundle.getInt("serejer"));}
		else if(seractual==3){Chk3.setChecked(true);ejerser.setText("Serie: "+ seractual +"/"+ bundle.getInt("serejer"));}
		else if(seractual==4){Chk4.setChecked(true);ejerser.setText("Serie: "+ seractual +"/"+ bundle.getInt("serejer"));}
	}
	
	public void upDate(){
		
		SQLiteDatabase WDB = AccesoDB.getWritableDatabase();
		if(WDB!=null){						
			
			WDB.execSQL("UPDATE Rutina SET seriescatual="+ seractual +",pesoserie"+ seractual +"="+ varpeso +",repserie"+ seractual +"="+ ejerrep.getText() +" WHERE ejeid="+ bundle.getInt("idejer"));	
		}
		WDB.close();
	}

	public void PopSeek(boolean valor){
		
		final boolean Valor = valor;
		final AlertDialog.Builder seekBar = new AlertDialog.Builder(this);
		final SeekBar seek = new SeekBar(this);
		
		seek.setMax(100);
		
		if(Valor==true){
			seekBar.setIcon(R.drawable.pesoico);
			seekBar.setTitle("Indique Peso");
			seek.setProgress(varpeso);}
		else {
			seekBar.setIcon(R.drawable.pesoico);
			seekBar.setTitle("Indique Repeiciones");
			seek.setProgress( Integer.parseInt((String) ejerrep.getText()));}
		
		seekBar.setView(seek);
		
		seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
				if(Valor==true){
					varpeso = progress;
					ejerpes.setText( varpeso + "Kg");				
				}else {
					ejerrep.setText(progress + "");
					barcont.setMax(Integer.parseInt((String) ejerrep.getText()));
				}
			}
			
			public void onStartTrackingTouch(SeekBar arg0) {
	 
			}
			
			public void onStopTrackingTouch(SeekBar seekBar) {
			
			}
		});
		
		seekBar.setPositiveButton("Confirmar",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			} 
		});	
		seekBar.create();
		seekBar.show();
	}
}



