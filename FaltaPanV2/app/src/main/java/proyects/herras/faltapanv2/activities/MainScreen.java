package proyects.herras.faltapanv2.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

import proyects.herras.faltapanv2.R;
import proyects.herras.faltapanv2.adapters.ListRecyclerViewAdapter;
import proyects.herras.faltapanv2.bbdd.DBAcces;
import proyects.herras.faltapanv2.bbdd.DBDataLoader;
import proyects.herras.faltapanv2.bbdd.DBStructureBuilder;
import proyects.herras.faltapanv2.contractor.ContractorTableValues;
import proyects.herras.faltapanv2.sharedpreferences.SPApp;
import proyects.herras.faltapanv2.support.ListTools;
import proyects.herras.faltapanv2.support.Lista;


public class MainScreen extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout ctlLayout;
    private RecyclerView  listRecyclerView;
    private ListRecyclerViewAdapter listRecyclerViewAdapter;
    private ArrayList<Lista> datos;
    private FloatingActionButton addListBtn;
    private SPApp spApp;

    private DBAcces dba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        deployDB();

        instanceControls();
        prepareControls();
    }

    public void instanceControls(){
        addListBtn = (FloatingActionButton)findViewById(R.id.add_list_btn);
        toolbar = (Toolbar)findViewById(R.id.mainscreenbar);
        ctlLayout = (CollapsingToolbarLayout)findViewById(R.id.ctlLayout);

        listRecyclerView = (RecyclerView) findViewById(R.id.list_recycler);

        datos = new ArrayList<Lista>();
        dba = new DBAcces(this);
    }

    public void prepareControls() {
        setSupportActionBar(toolbar);
        ctlLayout.setTitle("Mis Listas");//Generar recurso
        addListBtn.setOnClickListener(getOnClickListener(addListBtn));
        prepareRecycler();
    }

    public void prepareRecycler(){
        listRecyclerView.setAdapter(getAdaper());
        listRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public ListRecyclerViewAdapter getAdaper(){
        getData();
        listRecyclerViewAdapter = new ListRecyclerViewAdapter(datos);
        listRecyclerViewAdapter.setOnClickListener(getOnClickListener(null));
        listRecyclerViewAdapter.setOnLongClickListener(getOnLongClickListener());
        return listRecyclerViewAdapter;
    }

    public void getData(){
        datos.clear();
        Cursor c = dba.getCursor(ContractorTableValues.TablaLista.TABLE_NAME,ContractorTableValues.TablaLista.getCabeceras());
        if(c.moveToFirst()){
            for (int i = 0; i < c.getCount(); i++) {
                /*Lista(String nombre,String fechaCreacion,String fechaModificacion,String fechaEjecucion,int numElementos,int numElementosComprados,String tienda, String estado,int porcentajeCompletado,int imagen,int listId)
                Cabeceras c: NOMBRE,FECHA_CREACION,FECHA_MODIFICACION,FECHA_EJECUCION,NUM_ELEMENTOS,NUM_ELEMENTOSCOMPRADOS,NOMBRE_TIENDA,ESTADO,PORCENTAJE_COMPLETADO,REF_IMAGEN,_ID*/
                datos.add(new Lista(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getInt(4),c.getInt(5),c.getString(6),c.getString(7),c.getInt(8),c.getInt(9),c.getInt(10)));
                if (i < c.getCount()-1) {
                    c.moveToNext();
                }
            }
        }
        c.close();
    }

    public View.OnClickListener getOnClickListener(View v) {
        //Logica para cada btn
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(view.getId()== R.id.add_list_btn){
                    startActivity(new Intent(MainScreen.this,AddListCard.class));
                    overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
                }else{
                    keepListSelected(Integer.parseInt(((TextView) view.findViewById(R.id.list_id)).getText().toString()));
                    startActivity(new Intent(MainScreen.this, ProductScreen.class));
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
                }
            }
        };
    }

    public View.OnLongClickListener getOnLongClickListener(){
        return new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
                int listId = Integer.parseInt(((TextView) view.findViewById(R.id.list_id)).getText().toString());
                int position = Integer.parseInt(((TextView) view.findViewById(R.id.list_position)).getText().toString());
                keepListSelected(listId);
                PopListOpt(position);
                return false;
            }
        };
    }

    public void PopListOpt(final int pos){

        final AlertDialog.Builder grouplist = new AlertDialog.Builder(this);
        final AlertDialog alert = grouplist.create();
        View botones = LayoutInflater.from(this).inflate(R.layout.pop_list_opt,(ViewGroup) findViewById(R.id.dialog_list_opt));

        final FloatingActionButton edit = (FloatingActionButton) botones.findViewById(R.id.edit_list_btn);
        final FloatingActionButton delte = (FloatingActionButton)botones.findViewById(R.id.delete_list_btn);
        final FloatingActionButton cancel = (FloatingActionButton)botones.findViewById(R.id.cancel_list_btn);

        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*Crea un Bundle con los datos para precargarlos*/
                startActivity(new Intent(MainScreen.this,AddListCard.class));
                overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
            }
        });

        delte.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new ListTools().deleteList(dba,spApp.getListID());
                getData();
                listRecyclerViewAdapter.notifyItemRemoved(pos);
                alert.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert.dismiss();
            }
        });

        //grouplist.setIcon(R.drawable.pesoico);
        //grouplist.setTitle("¿Que desea hacer?");
        alert.setView(botones);
        alert.show();
    }

    public void keepListSelected(int listId){
        Log.d("FaltaPan", "keepListSelected:"+ listId);
         Cursor c = dba.getCursor(ContractorTableValues.TablaLista.getQueryListToProductFields(listId));
        c.moveToFirst();
        spApp = new SPApp(this);
        spApp.setSelectedList(c.getString(0), c.getInt(1), c.getInt(2));
        c.close();
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
