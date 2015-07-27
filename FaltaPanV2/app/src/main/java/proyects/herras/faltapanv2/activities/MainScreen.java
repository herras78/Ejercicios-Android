package proyects.herras.faltapanv2.activities;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import java.util.ArrayList;

import proyects.herras.faltapanv2.R;
import proyects.herras.faltapanv2.adapters.ListRecyclerViewAdapter;
import proyects.herras.faltapanv2.bbdd.DBAcces;
import proyects.herras.faltapanv2.bbdd.DBDataLoader;
import proyects.herras.faltapanv2.bbdd.DBStructureBuilder;
import proyects.herras.faltapanv2.contractor.ContractorTableValues;
import proyects.herras.faltapanv2.sharedpreferences.SPApp;
import proyects.herras.faltapanv2.support.Lista;


public class MainScreen extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView  listRecyclerView;
    private ListRecyclerViewAdapter listRecyclerViewAdapter;
    private ArrayList<Lista> datos;

    private DBAcces dba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        deployDB();

        instanceControls();
        prepareControls();
        prepareRecycler();


       /* Cursor c = new DBAcces(this).getCursor("SELECT * FROM " + ContractorTableValues.TablaProducto.TABLE_NAME);
        c.moveToFirst();
        txtTest.setText(c.getString(1)+","+c.getString(8)+","+c.getString(9));*/
    }

    public void instanceControls(){
        toolbar = (Toolbar)findViewById(R.id.mainscreenbar);
        listRecyclerView = (RecyclerView) findViewById(R.id.list_recycler);

        datos = new ArrayList<Lista>();
        dba = new DBAcces(this);


    }
    public void prepareControls(){
        setSupportActionBar(toolbar);
    }

    public void prepareRecycler(){
        listRecyclerView.setAdapter(getAdaper());
        listRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public ListRecyclerViewAdapter getAdaper(){
        getData();
        listRecyclerViewAdapter = new ListRecyclerViewAdapter(datos);
        listRecyclerViewAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return listRecyclerViewAdapter;
    }

    public void getData(){
        datos.clear();
        Cursor c = dba.getCursor(ContractorTableValues.TablaLista.TABLE_NAME,ContractorTableValues.TablaLista.getCabeceras());
        if(c.moveToFirst()){
            for (int i = 0; i < c.getCount(); i++) {
                /*Lista(String nombre,String fechaCreacion,String fechaModificacion,String fechaEjecucion,int numElementos,String tienda, String estado,int porcentajeCompletado,int imagen)
                Cabeceras c: NOMBRE,FECHA_CREACION,FECHA_MODIFICACION,FECHA_EJECUCION,NUM_ELEMENTOS,NOMBRE_TIENDA,ESTADO,PORCENTAJE_COMPLETADO,REF_IMAGEN*/
                datos.add(new Lista(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getInt(4),c.getString(5),c.getString(6),c.getInt(7),c.getInt(8)));
                if (i < c.getCount()-1) {
                    c.moveToNext();
                }
            }
        }
    }

    public void deployDB(){
        if(new SPApp(this.getApplicationContext()).isFirst()){
            //Log.d("FaltaPan", "MainScreen,Comprobando isFirst");
            new DBStructureBuilder(this);
            new DBDataLoader(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
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
