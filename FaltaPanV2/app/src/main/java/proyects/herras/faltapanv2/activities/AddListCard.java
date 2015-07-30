package proyects.herras.faltapanv2.activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import proyects.herras.faltapanv2.R;
import proyects.herras.faltapanv2.bbdd.DBAcces;
import proyects.herras.faltapanv2.contractor.ContractorTableValues;

public class AddListCard extends Activity {
    private String[] tiendas;
    private String[] imgTiendas;

    private EditText nombre;
    private TextView aceptar;
    private TextView cancelar;
    private ImageView img;

    private String nombreSeleccionado = "";
    private String tiendaSeleccionada = "";
    private int imagenSeleccionada;

    private DBAcces dba;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_list_screen);

        prepareControls();
        setListener();
    }

    public void prepareControls(){
        nombre = (EditText)findViewById(R.id.name_new_list);
        aceptar = (TextView)findViewById(R.id.new_card_acept);
        cancelar = (TextView)findViewById(R.id.new_card_cancel);
        img = (ImageView)findViewById(R.id.img_new_list);
        dba = new DBAcces(this);
        prepareSppiner();
    }

    public void setListener(){
        aceptar.setOnClickListener(getOnClickListener(aceptar));
        cancelar.setOnClickListener(getOnClickListener(cancelar));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
                imagenSeleccionada = Integer.parseInt(imgTiendas[position]);
                tiendaSeleccionada = parent.getItemAtPosition(position).toString();
                Log.d("FaltaPan",tiendaSeleccionada +","+imagenSeleccionada);
                img.setImageResource(imagenSeleccionada);
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void prepareSppiner(){
        spinner = (Spinner)findViewById(R.id.shop_spinner);
        spinner.setAdapter(getAdaper());
    }

    public ArrayAdapter<String> getAdaper(){
        getData();
        return new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tiendas);
    }

    public void getData(){
        Log.d("FaltaPan", ContractorTableValues.TablaTienda.TABLE_NAME + "," + ContractorTableValues.TablaTienda.getCabeceras().toString());
        Cursor c = dba.getCursor("SELECT NOMBRE,FECHA_CREACION,FECHA_MODIFICACION,REF_IMAGEN FROM T_TIENDA ORDER BY NOMBRE DESC");
        String unsplitList = "";
        String unsplitImg = "";
        if(c.moveToFirst()){
            for (int i = 0; i < c.getCount(); i++) {
                unsplitList += c.getString(0);
                unsplitImg += c.getInt(3);
                if (i < c.getCount()-1) {
                    c.moveToNext();
                    unsplitList += ";";
                    unsplitImg += ";";
                }
            }
        }
        tiendas = unsplitList.split(";");
        imgTiendas = unsplitImg.split(";");
    }

    public View.OnClickListener getOnClickListener(View v) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.new_card_acept:
                        dba.insertData(ContractorTableValues.TablaLista.TABLE_NAME,getValues());
                    case R.id.new_card_cancel:
                }
                Salir();
            }
        };
    }

    public ContentValues getValues(){
        ContentValues cv = new ContentValues();
        if(nombre.getText().toString().equals("")){
            cv.put(ContractorTableValues.TablaLista.getNombre(), tiendaSeleccionada);
            //Log.e("FaltaPan", "Nombre Vacio :'" + nombre.getText().toString() + "'," + tiendaSeleccionada + "," + imagenSeleccionada);
        }else{
            cv.put(ContractorTableValues.TablaLista.getNombre(),nombre.getText().toString());
            cv.put(ContractorTableValues.TablaLista.getNombreTienda(), tiendaSeleccionada);
            //Log.e("FaltaPan", "Nombre Relleno:'" + nombre.getText().toString() + "'," + tiendaSeleccionada + "," + imagenSeleccionada);
        }

        cv.put(ContractorTableValues.TablaLista.getFechaCreacion(),(new SimpleDateFormat("dd/MM/yyyy")).format(new Date()));
        cv.put(ContractorTableValues.TablaLista.getRefImagen(), imagenSeleccionada);

        return cv;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Salir();
            return true;
        }
        return false;
    }

    public void Salir(){
        Intent intent = new Intent(AddListCard.this, MainScreen.class);
        startActivity(intent);
    }
}
