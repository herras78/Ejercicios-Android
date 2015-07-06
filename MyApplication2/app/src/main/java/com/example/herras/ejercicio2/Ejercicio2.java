package com.example.herras.ejercicio2;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


public class Ejercicio2 extends ActionBarActivity {

    private Button tipo1;
    private Button tipo2;
    private Button tipo3;
    private TextView textType;
    private TextInputLayout txtInputLayout;
    private TextView txtInput;
    private TextView lblEtiquetaList;
    public String[] datos = new String[]{"Elemento1","Elemento2","Elemento3","Elemento4","Elemento5","Elemento6"};
    public Titular[] TitDatos = new Titular[10];/*{
            new Titular("Titulo1","Subtitulo largo 1"),
            new Titular("Titulo2","Subtitulo largo 2"),
            new Titular("Titulo3","Subtitulo largo 3"),
            new Titular("Titulo4","Subtitulo largo 4"),
            new Titular("Titulo5","Subtitulo largo 5"),
    };*/
    private Spinner spOpciones;
    private ArrayAdapter<String> adaptador;
    private AdaptadorTitulares titAdapter;
    private ListView lstOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio2);

        instanciarControles();
        cargarDatos();
        preparaAdaptador();
        preparaControles();
    }

    public void instanciarControles(){
        tipo1 = (Button)findViewById(R.id.BtnTipo1);
        tipo2 = (Button)findViewById(R.id.BtnTipo2);
        tipo3 = (Button)findViewById(R.id.BtnTipo3);
        textType = (TextView)findViewById(R.id.LblEtiquetaSpin);
        txtInputLayout = (TextInputLayout)findViewById(R.id.TiLayout);
        txtInput = (TextView)findViewById(R.id.TxtInput);
        lblEtiquetaList = (TextView)findViewById(R.id.LblEtiquetaList);
        spOpciones = (Spinner)findViewById(R.id.SpOpciones);
        lstOpciones = (ListView)findViewById(R.id.LstOpciones);
            }

    public void cargarDatos(){
        for(int i=0;i<TitDatos.length;i++){
            TitDatos[i] = new Titular("Titulo" + i,"Subtitulo largo " + i);
        }
    }

    public void preparaAdaptador(){
        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,datos);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        titAdapter = new AdaptadorTitulares(this,TitDatos);
    }

    public void preparaControles(){
        tipo1.setOnClickListener(preparaBoton(tipo1));
        tipo2.setOnClickListener(preparaBoton(tipo2));
        tipo3.setOnClickListener(preparaBoton(tipo3));

        txtInputLayout.setErrorEnabled(true);

        spOpciones.setAdapter(adaptador);
        spOpciones.setOnItemSelectedListener(preparaSpinner());

        lstOpciones.addHeaderView((View) getLayoutInflater().inflate(R.layout.list_header, null));
        lstOpciones.setAdapter(titAdapter);
        lstOpciones.setOnItemClickListener(onItemListClick());
    }

    public View.OnClickListener preparaBoton(final View btn){
        return new View.OnClickListener() {
            public void onClick(View arg0)
            {
                if(btn.getId() == R.id.BtnTipo1){
                    textType.setText(textType.getText().toString() + " 123");
                    //textType.setBackgroundColor(Color.BLUE);
                    Editable str = (Editable)textType.getText();
                    str.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 3, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    textType.setText(str);
                }else if(btn.getId() == R.id.BtnTipo2){
                    textType.setTypeface(Typeface.SANS_SERIF);
                    textType.setText(textType.getText().toString() + " 456");
                }else if(btn.getId() == R.id.BtnTipo3){
                    String num = txtInput.getText().toString();
                    if(num.isEmpty() || Integer.parseInt(num)%2 != 0){
                        txtInputLayout.setError("Error:No es un numero par!!");
                    }else {
                        txtInputLayout.setError(null);
                    }
                }
            }
        };
    }

    public AdapterView.OnItemClickListener onItemListClick(){
        return new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> a, View v,int position,long id){
               // String itemSeleccionado = ((Titular)a.getItemAtPosition(position)).getTitulo(); Opcion 1 recuperar el objeto Titular del adaptador, y cojer uno de los textos.
               String itemSeleccionado = ((TextView)v.findViewById(R.id.lblTitulo)).getText().toString(); // Opcion 2 recuperar el texto desde una de las vistas localizandola por su id.
                lblEtiquetaList.setText("Item seleccionado:" + itemSeleccionado);
            }
        };
    }

    public AdapterView.OnItemSelectedListener preparaSpinner(){
        return new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                        android.view.View v, int position, long id) {
                textType.setText("Seleccionado: " +
                        parent.getItemAtPosition(position));
            }

            public void onNothingSelected(AdapterView<?> parent) {
                textType.setText("");
            }
        };
    }

    class AdaptadorTitulares extends ArrayAdapter<Titular> {

        public AdaptadorTitulares(Context context,Titular[] datos){
            super(context,R.layout.listener_titular,datos);
        }

        public View getView(int position, View convertView,ViewGroup parent){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listener_titular, null);

            TextView lblTitulo = (TextView)item.findViewById(R.id.lblTitulo);
            lblTitulo.setText(TitDatos[position].getTitulo());

            TextView lblSubTitlo = (TextView)item.findViewById(R.id.lblSubTitulo);
            lblSubTitlo.setText(TitDatos[position].getSubtitulo());

            return (item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ejercicio2, menu);
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
