package com.example.herras.ejercicio3;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.herras.ejercicio3.Adapters.AdaptadorTitulares;
import com.example.herras.ejercicio3.Decorators.DividerItemDecoration;

import java.util.ArrayList;

public class Ejercicio3 extends Activity {

    private RecyclerView recView;
    private ArrayList<Titular> datos;
    private AdaptadorTitulares adaptador;
    private Button insertar;
    private Button eliminar;
    private Button mover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio3);

        cargaDatos();
        instanciaControles();
        preparaAdaptador();
        preparaControles();
    }

    public void cargaDatos(){
        datos = new ArrayList<Titular>();
            for(int i=0;i<50;i++){
                datos.add(new Titular("Titulo"+i,"SubTitulo"+i));
            }
    }

    public void instanciaControles(){
        recView = (RecyclerView) findViewById(R.id.RecView);
        recView.setHasFixedSize(true);
        insertar = (Button)findViewById(R.id.BtnInsert);
        eliminar = (Button)findViewById(R.id.BtnElim);
        mover = (Button)findViewById(R.id.BtnMover);
    }

    public void preparaAdaptador(){
        adaptador = new AdaptadorTitulares(datos);
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("DemoRecView", "Pulsado el elemento " + recView.getChildPosition(v));
            }
        });
    }

    public void preparaControles(){
        Context cont = this;
        recView.setAdapter(adaptador);
        recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //recView.setLayoutManager((new GridLayoutManager(this,3)));
        recView.addItemDecoration(new DividerItemDecoration(cont,DividerItemDecoration.VERTICAL_LIST));
        recView.setItemAnimator(new DefaultItemAnimator());
        eliminar.setOnClickListener(prepararBotones(eliminar));
        mover.setOnClickListener(prepararBotones(mover));
        insertar.setOnClickListener(prepararBotones(insertar));
    }

    public View.OnClickListener prepararBotones(final Button b){
        return new View.OnClickListener(){
            public void onClick(View v) {
                switch (b.getId()){
                    case R.id.BtnInsert:
                        datos.add(1, new Titular("Nuevo titular", "Subtitulo nuevo titular"));
                        adaptador.notifyItemInserted(1);
                        break;
                    case  R.id.BtnElim:
                        datos.remove(1);
                        adaptador.notifyItemRemoved(1);
                        break;
                    case R.id.BtnMover:
                        Titular aux = datos.get(1);
                        datos.set(1,datos.get(2));
                        datos.set(2,aux);
                        adaptador.notifyItemMoved(1, 2);
                }
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ejercicio3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
