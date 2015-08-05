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
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import proyects.herras.faltapanv2.R;
import proyects.herras.faltapanv2.adapters.ProductRecyclerViewAdapter;
import proyects.herras.faltapanv2.bbdd.DBAcces;
import proyects.herras.faltapanv2.contractor.ContractorTableValues;
import proyects.herras.faltapanv2.sharedpreferences.SPApp;
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
    private ImageView toolbarImg;
    private SPApp spApp;


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
        toolbarImg = (ImageView)findViewById(R.id.imgProductToolbar);

        spApp = new SPApp(this);
        datos = new ArrayList<Producto>();
        dba = new DBAcces(this);
    }

    public void prepareControls() {
        setSupportActionBar(toolbar);
        listName = spApp.getListName();
        toolbarImg.setImageResource(spApp.getListImgRef());
        ctlLayout.setTitle("Productos");
        titList.setText(listName);
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
        //Parametrizar la query y sacala de aqui.
        String query = "SELECT TP.NOMBRE,TP.FECHA_CREACION,TP.FECHA_CREACION FEC_MODIFICACION,TLP.ESTADO_PRODUCTO ,TLP.PRECIO,TLP.MARCA,TLP.NUMERO_ELEMENTOS,TLP.UNIDAD_MEDIDA,TLP.ID_LISTA,TLP.ID_PRODUCTO,TF.NOMBRE FAMILIA\n" +
                " FROM T_PRODUCTO TP\n" +
                "JOIN T_A_LISTA_PRODUCTO TLP\n" +
                " ON TLP.ID_PRODUCTO = TP._id\n" +
                "JOIN T_LISTA TL\n" +
                " ON TLP.ID_LISTA = TL._id\n" +
                "JOIN T_FAMILIA TF\n" +
                " ON TF._id = TP.FAMILIA\n" +
                "WHERE TL.NOMBRE = '"+ listName +"';";

        Cursor c = dba.getCursor(query);
       // Log.d("FaltaPan", "Cargando datos de Lista:" + query);
        if(c.moveToFirst()){
            for (int i = 0; i < c.getCount(); i++) {
                datos.add(new Producto(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getInt(4),c.getString(5),c.getInt(6),c.getString(7),c.getInt(8),c.getInt(9),c.getString(10)));
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
                if (view.getId() == R.id.add_product_btn) {
                    Intent i = new Intent(ProductScreen.this, AddProductCard.class);
                    startActivity(i);
                } else {
                   /* Logica para definir las funciones de clic en los productItems*/
                    int prodId = Integer.parseInt(((TextView) view.findViewById(R.id.product_id)).getText().toString());
                    int position = 0;
                    //Es necesario controlar la posicion del producto.
                    switch (getProductStatus(view,prodId)) {
                        case "P":
                            setProductStatus("C", prodId, position);
                            break;
                        case "C":
                            setProductStatus("P", prodId,position);
                            break;
                        case "S":
                        case "D":
                        case "A":
                            break;
                    }
                }
            }
        };
    }

    public String getProductStatus(View view,int prodId){
        String query = "SELECT "+ ContractorTableValues.TablaListaProducto.ESTADO_PRODUCTO
                +" FROM "+ ContractorTableValues.TablaListaProducto.TABLE_NAME
                +" WHERE "+ ContractorTableValues.TablaListaProducto.ID_LISTA
                +"="+ spApp.getListID()
                +" AND "+ ContractorTableValues.TablaListaProducto.ID_PRODUCTO
                +"="+ prodId +";";

        Cursor c = dba.getCursor(query);
        c.moveToFirst();

        return c.getString(0);
    }

    public void setProductStatus(String newStatus,int prodId, int position){
        String query = "UPDATE "+ ContractorTableValues.TablaListaProducto.TABLE_NAME
                +" SET "+ ContractorTableValues.TablaListaProducto.ESTADO_PRODUCTO
                +"='"+ newStatus +"'"
                +" WHERE "+ ContractorTableValues.TablaListaProducto.ID_LISTA
                +"="+ spApp.getListID()
                +" AND "+ ContractorTableValues.TablaListaProducto.ID_PRODUCTO
                +"="+ prodId +";";
        dba.updateDate(query);
        getData();
        productRecyclerViewAdapter.notifyItemChanged(position);
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
