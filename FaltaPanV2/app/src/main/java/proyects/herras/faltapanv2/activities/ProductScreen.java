package proyects.herras.faltapanv2.activities;

import android.app.ActionBar;
import android.app.AlertDialog;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import proyects.herras.faltapanv2.R;
import proyects.herras.faltapanv2.adapters.ProductRecyclerViewAdapter;
import proyects.herras.faltapanv2.bbdd.DBAcces;
import proyects.herras.faltapanv2.contractor.ContractorTableValues;
import proyects.herras.faltapanv2.sharedpreferences.SPApp;
import proyects.herras.faltapanv2.support.ListTools;
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
    private ImageView backBtn;
    private SPApp spApp;
    private ListTools actualList;


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
        /*backBtn = (ImageView)findViewById(R.id.product_back_btn);*/

        spApp = new SPApp(this);
        datos = new ArrayList<Producto>();
        dba = new DBAcces(this);
    }

    public void prepareControls() {
        setSupportActionBar(toolbar);
        listName = spApp.getListName();
        toolbarImg.setImageResource(spApp.getListImgRef());
        ctlLayout.setTitle("0/5 Productos 0,00€");
        titList.setText(listName);
        addProductBtn.setOnClickListener(getOnClickListener(addProductBtn));
        actualList = new ListTools();


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
        productRecyclerViewAdapter.setOnLongClickListener(getOnLongClickListener());
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
                "WHERE TL._id = "+ spApp.getListID()+";";

        Cursor c = dba.getCursor(query);
        Log.d("FaltaPan", "Cargando datos de Lista:" + query);
        if(c.moveToFirst()){
            Log.d("FaltaPan", "MoveToFisrt true" );
            for (int i = 0; i < c.getCount(); i++) {
                datos.add(new Producto(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getFloat(4),c.getString(5),c.getFloat(6),c.getString(7),c.getInt(8),c.getInt(9),c.getString(10)));
                if (i < c.getCount()-1) {
                    Log.d("FaltaPan", "Producto Nº"+ i );
                    c.moveToNext();
                }
            }
        }else{Log.d("FaltaPan", "MoveToFisrt false" );}
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

                //Mejor con un Switch, e incluye el boton back
                if (view.getId() == R.id.add_product_btn) {
                    startActivity(new Intent(ProductScreen.this, AddProductCard.class));
                    overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
                }/*else if(view.getId() == R.id.product_back_btn){
                    back();
                } */else {
                    int prodId = Integer.parseInt(((TextView) view.findViewById(R.id.product_id)).getText().toString());
                    int position = Integer.parseInt(((TextView) view.findViewById(R.id.product_position)).getText().toString());

                    switch (getProductStatus(view, prodId)) {
                        case "P":
                            setProductStatus("C", prodId, position);
                            actualList.setListBuyedSize(dba, spApp.getListID());
                            break;
                        case "C":
                            setProductStatus("P", prodId, position);
                            actualList.setListBuyedSize(dba, spApp.getListID());
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

    public View.OnLongClickListener getOnLongClickListener(){
        return new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                int prodId = Integer.parseInt(((TextView) v.findViewById(R.id.product_id)).getText().toString());
                int position = Integer.parseInt(((TextView) v.findViewById(R.id.product_position)).getText().toString());
                PopListOpt(prodId,position);
                return false;
            }
        };
    }

    public void PopListOpt(final int prodId,final int position){

        final AlertDialog.Builder grouplist = new AlertDialog.Builder(this);
        final AlertDialog alert = grouplist.create();
        View botones = LayoutInflater.from(this).inflate(R.layout.pop_product_opt,(ViewGroup) findViewById(R.id.dialog_product_opt));

        final FloatingActionButton edit = (FloatingActionButton) botones.findViewById(R.id.edit_product_btn);
        final FloatingActionButton delte = (FloatingActionButton)botones.findViewById(R.id.delete_product_btn);
        final FloatingActionButton cancel = (FloatingActionButton)botones.findViewById(R.id.cancel_product_btn);

        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Genera Bundle con los datos del producto para recuperarlos en addproduct y permitir su edicion.
                startActivity(new Intent(ProductScreen.this, AddProductCard.class));
                overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
            }
        });

        delte.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                actualList.deleteProductOnList(dba, spApp.getListID(), prodId);
                actualList.setListSize(dba, spApp.getListID());
                actualList.setListBuyedSize(dba, spApp.getListID());
                getData();
                productRecyclerViewAdapter.notifyItemRemoved(position);
                alert.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert.dismiss();
            }
        });

       /* WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(alert.getWindow().getAttributes());
        lp.width = 350;
        lp.height = 1150;
        lp.x=-700;
        lp.y=180;*/

        alert.setView(botones);
        alert.show();
       // alert.getWindow().setAttributes(lp);
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
        startActivity(new Intent(ProductScreen.this,MainScreen.class));
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
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
