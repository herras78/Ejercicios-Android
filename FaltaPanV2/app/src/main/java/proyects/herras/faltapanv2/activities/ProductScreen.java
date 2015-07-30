package proyects.herras.faltapanv2.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import proyects.herras.faltapanv2.R;
import proyects.herras.faltapanv2.adapters.ProductRecyclerViewAdapter;
import proyects.herras.faltapanv2.bbdd.DBAcces;
import proyects.herras.faltapanv2.support.Producto;

/**
 * Created by Herras on 30/07/2015.
 */
public class ProductScreen extends AppCompatActivity {
    private Toolbar toolbar;
    private CollapsingToolbarLayout ctlLayout;
    private RecyclerView productRecyclerView;
    private ProductRecyclerViewAdapter productRecyclerViewAdapter;
    private ArrayList<Producto> datos;
    private FloatingActionButton addProductBtn;
    private DBAcces dba;
    private TextView titList;
    private String listName;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_screen);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        instanceControls();
        prepareControls();
    }

    public void instanceControls(){
        addProductBtn = (FloatingActionButton)findViewById(R.id.add_product_btn);
        toolbar = (Toolbar)findViewById(R.id.product_screen_toolbar);
        ctlLayout = (CollapsingToolbarLayout)findViewById(R.id.ctlProductLayout);
        titList = (TextView)findViewById(R.id.product_screen_tit);
        productRecyclerView = (RecyclerView) findViewById(R.id.product_recycler);

        datos = new ArrayList<Producto>();
        dba = new DBAcces(this);
    }

    public void prepareControls() {
        setSupportActionBar(toolbar);
        listName = ((Bundle)this.getIntent().getExtras()).getString("LISTA");
        ctlLayout.setTitle("Productos");
        titList.setText(listName);
        //Generar recurso
        addProductBtn.setOnClickListener(getOnClickListener(addProductBtn));

        prepareRecycler();
    }

    public void prepareRecycler(){
        productRecyclerView.setAdapter(getAdaper());
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        productRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public ProductRecyclerViewAdapter getAdaper(){
        getData();
        productRecyclerViewAdapter = new ProductRecyclerViewAdapter(datos);
        productRecyclerViewAdapter.setOnClickListener(getOnClickListener(null));
        return productRecyclerViewAdapter;
    }

    public void getData(){
        datos.clear();
        String query = "SELECT TP.NOMBRE,TP.FECHA_CREACION,TP.FECHA_CREACION FEC_MODIFICACION,TLP.ESTADO_PRODUCTO ,TLP.PRECIO,TLP.MARCA,TLP.NUMERO_ELEMENTOS,TLP.UNIDAD_MEDIDA,TF.NOMBRE FAMILIA\n" +
                " FROM T_PRODUCTO TP\n" +
                "JOIN T_A_LISTA_PRODUCTO TLP\n" +
                " ON TLP.ID_PRODUCTO = TP._id\n" +
                "JOIN T_LISTA TL\n" +
                " ON TLP.ID_LISTA = TL._id\n" +
                "JOIN T_FAMILIA TF\n" +
                " ON TF._id = TP.FAMILIA\n" +
                "Where TL.NOMBRE = '"+ listName +"';";
        //Cursor c = dba.getCursor(ContractorTableValues.TablaProducto.TABLE_NAME,ContractorTableValues.TablaLista.getCabeceras());
        Cursor c = dba.getCursor(query);
        if(c.moveToFirst()){
            for (int i = 0; i < c.getCount(); i++) {
                /*Producto(String nombre, String fechaCreacion, String fechaModificacion,String status, int price,String marca, int cuantity,String cuantityUnitunit,String familia)
                Cabeceras c: NOMBRE,FECHA_CREACION,FECHA_MODIFICACION,FAMILIA,TIENDA,REF_IMAGEN*/
                datos.add(new Producto(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getInt(4),c.getString(5),c.getInt(6),c.getString(7),c.getString(8)));
                if (i < c.getCount()-1) {
                    c.moveToNext();
                }
            }
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            back();
            return true;
        }
        return false;
    }

    public View.OnClickListener getOnClickListener(View v) {
        //Logica para cada btn
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if(view.getId()== R.id.add_list_btn){
                    Intent i = new Intent(MainScreen.this,AddListCard.class);
                    startActivity(i);
                }else{
                    String lista = ((TextView)view.findViewById(R.id.product_tit)).getText().toString();                                               ;
                    Bundle b = new Bundle();
                    b.putString("LISTA",lista);
                   /* Intent intent = new Intent(MainScreen.this,ProductScreen.class);
                    intent.putExtras(b);
                    startActivity(intent);
                }*/
            }
        };
    }

    public void back(){
        Intent intent = new Intent(ProductScreen.this,MainScreen.class);
        startActivity(intent);
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
