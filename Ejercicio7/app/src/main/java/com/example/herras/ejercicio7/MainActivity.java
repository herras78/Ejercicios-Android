package com.example.herras.ejercicio7;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.herras.ejercicio7.Adapters.AdaptadorRecyclerView;

import java.util.ArrayList;


public class MainActivity extends Activity {

    static final String DBNAME = "DBUsuarios";
    static final String TABLENAME = "Usuarios";
    static final String FIELDCODE = "codigo";
    static final String FIELDNAME = "nombre";

    private RecyclerView recView;
    private AdaptadorRecyclerView adapter;
    private ArrayList<Item> datos;

    private DBAcces dba;

    private TextView txtCodigo;
    private TextView txtNombre;
    private Button btnInsert;
    private Button btnUpdate;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instanciaControles();
        setButtonListeners();
        prepareRecycler();
    }

    public void instanciaControles(){
        dba = new DBAcces(this,DBNAME);
        txtCodigo = (TextView)findViewById(R.id.edit_codigo);
        txtNombre =(TextView)findViewById(R.id.edit_nombre);
        btnInsert =(Button)findViewById(R.id.btn_ins);
        btnUpdate =(Button)findViewById(R.id.btn_up);
        btnDelete =(Button)findViewById(R.id.btn_del);
    }

    public void setButtonListeners(){
        btnInsert.setOnClickListener(getButtonListener(btnInsert));
        btnUpdate.setOnClickListener(getButtonListener(btnUpdate));
        btnDelete.setOnClickListener(getButtonListener(btnDelete));
    }

    public View.OnClickListener getButtonListener(final Button b){
        return new View.OnClickListener() {
            public void onClick(View arg0)
            {
                    switch (b.getId()) {
                        case R.id.btn_ins:
                            dba.insertData(TABLENAME, getValues(true));
                            getData();
                            adapter.notifyItemInserted(datos.size() - 1);
                            break;
                        case R.id.btn_up:
                            dba.updateDate(TABLENAME, getValues(false), getConditions());
                            getData();
                            adapter.notifyItemChanged(0);
                            break;
                        case R.id.btn_del:
                            dba.deleteDate(TABLENAME, getConditions());
                            Item i= new Item(txtCodigo.getText().toString()+" - "+txtNombre.getText().toString());
                            Log.e("ERROR onClick",datos.indexOf(i)+" "+txtCodigo.getText().toString()+" - "+txtNombre.getText().toString());
                            getData();
                            adapter.notifyItemRemoved(datos.indexOf(i));
                            break;
                    }
                clearFields();
            }
        };
    }

    public ContentValues getValues(boolean insup){
        ContentValues val = new ContentValues();

        if(insup) {val.put(FIELDCODE, txtCodigo.getText().toString());}

        val.put(FIELDNAME,txtNombre.getText().toString());
        return val;
    }


    public String getConditions(){
        String condition = FIELDCODE +"="+ txtCodigo.getText().toString();
        return condition;
    }

    public void clearFields(){
        txtNombre.setText("");
        txtCodigo.setText("");
    }

    public void prepareRecycler(){
        recView = (RecyclerView)findViewById(R.id.RecView);
       // recView.setHasFixedSize(true);
        getAdapter();
        recView.setAdapter(adapter);
        recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recView.setItemAnimator(new DefaultItemAnimator());

    }

    public void getAdapter(){
        datos = new ArrayList<Item>();
        getData();
        adapter = new AdaptadorRecyclerView(datos);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void getData(){
        String[] campos = new String[]{FIELDCODE,FIELDNAME};
        Cursor c = dba.getCursor(TABLENAME,campos);
        datos.clear();
        if(c.moveToFirst()) {
            for (int i = 0; i < c.getCount(); i++) {
                datos.add(new Item(c.getString(0) + " - " + c.getString(1)));
                Log.e("ERROR getData",c.getString(0) + " - " + c.getString(1));
                if (i < c.getCount()-1) {
                    c.moveToNext();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
