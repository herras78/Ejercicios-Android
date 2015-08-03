package proyects.herras.faltapanv2.activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import proyects.herras.faltapanv2.R;
import proyects.herras.faltapanv2.bbdd.DBAcces;
import proyects.herras.faltapanv2.contractor.ContractorTableValues;
import proyects.herras.faltapanv2.sharedpreferences.SPApp;

public class AddProductCard extends Activity {

    private EditText productName;
    private EditText productPrice;
    private EditText productBrand;
    private EditText productCuantity;
    private TextView aceptar;
    private TextView cancelar;
    private Spinner spinnerCuantityUnit;

    private SPApp spApp;
    private DBAcces dba;

    private SelectedProduct selectedProduct;

    private static String[] UNITYS = new String[]{
            //Genera recurso y ordenalo alfabeticamente....
            "Latas",
            "Botellas",
            "Bolsas",
            "Pack",
            "Unidades",
            "Kilos",
            "Gramos",
            "Litros",
            "Centilitros",
            "Mililitros"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product_screen);

        prepareControls();
        setListener();
    }

    public void prepareControls(){
        productName = (EditText)findViewById(R.id.new_product_name);
        productPrice = (EditText)findViewById(R.id.edit_price);
        productBrand = (EditText)findViewById(R.id.edit_brand);
        productCuantity = (EditText)findViewById(R.id.edit_cuantity);

        selectedProduct = new SelectedProduct("",0,"",0,"",0);

        aceptar = (TextView)findViewById(R.id.new_product_acept);
        cancelar = (TextView)findViewById(R.id.new_product_cancel);

        dba = new DBAcces(this);
        spApp = new SPApp(this);
        prepareSppiner();
    }

    public void setListener(){
        aceptar.setOnClickListener(getOnClickListener(aceptar));
        cancelar.setOnClickListener(getOnClickListener(cancelar));
        spinnerCuantityUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                selectedProduct.setCuantityUnit(parent.getItemAtPosition(position).toString());
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void prepareSppiner(){
        spinnerCuantityUnit = (Spinner) findViewById(R.id.sppiner_cuantity_unit);
        spinnerCuantityUnit.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, UNITYS));
    }

    public void insertProductLogic(){
       if(keepInsertedProduct()){
           CreateProductIfNotExist();//inserta el producto en la tabla producto si no existe.
           if(productAlreadyInList()){//verificar si el producto esta ya en la lista, si no  esta lo inserta  y lo actualiza si lo esta.
               dba.updateDate(selectedProduct.updateProductInDBQuery());
           } else {
               dba.insertData(selectedProduct.insertProductInDBQuery());
           }
           Salir();
       };
    }

    public boolean keepInsertedProduct(){
        if(productName.getText().toString().equals("")){
            //Genera Recurso...
            Log.d("FaltaPan","En keepInsertedProduct,Nombre vacio.");
            Snackbar.make((View)aceptar, "Por favor, indica un nombre.", Snackbar.LENGTH_LONG).show();
            return false;
        }else {
            Log.d("FaltaPan", "En keepInsertedProduct,guardando datos");
            selectedProduct.setProductName(productName.getText().toString());

            if(!productCuantity.getText().toString().equals("")){
                selectedProduct.setCuantity(Integer.parseInt(productCuantity.getText().toString()));
            }else{selectedProduct.setCuantity(0);}

            if(!productPrice.getText().toString().equals("")){
                selectedProduct.setPrice(Integer.parseInt(productPrice.getText().toString()));
            }else{selectedProduct.setPrice(0);}

            if(!productBrand.getText().toString().equals("")){
                selectedProduct.setBrand(productBrand.getText().toString());
            }else{selectedProduct.setBrand("");}

            //La unidad se setea en el Spinner
            return true;
        }
    }

    public void CreateProductIfNotExist(){
           Cursor c = dba.getCursor(selectedProduct.getNameQuery());
           if (!c.moveToFirst()) {
               Log.d("FaltaPan", "El producto no existe");
                c.close();
               dba.insertData(ContractorTableValues.TablaProducto.TABLE_NAME, selectedProduct.getProductValues());
               //dba.insertData(selectedProduct.getProductValues());// ATENCION recive un String no un ContentValues,Cambialo
                c = dba.getCursor(selectedProduct.getNameQuery());
               c.moveToFirst();
               selectedProduct.setId(c.getInt(1)) ;
               Log.d("FaltaPan", "El producto " +c.getString(0)+" fue insertado correctamente con ID:"+c.getInt(1));
                c.close();
           }else{
               selectedProduct.setId(c.getInt(1));
               Log.d("FaltaPan", "El producto " + c.getInt(0) + " existe");
           }
    }

    public boolean productAlreadyInList(){
        return dba.getCursor(selectedProduct.productAlreadyInListQuery()).moveToFirst();
    }

    public View.OnClickListener getOnClickListener(View v) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.new_product_acept:
                        insertProductLogic();
                        break;
                        case R.id.new_product_cancel:
                            Salir();
                            break;
                }
            }
        };
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Salir();
            return true;
        }
        return false;
    }

    public void Salir(){
        Intent intent = new Intent(AddProductCard.this, ProductScreen.class);
        startActivity(intent);
    }

    private class SelectedProduct {
        private String productName;
        private int cuantity;
        private String cuantityUnit;
        private int price;
        private String brand;
        private int id;

        public SelectedProduct(String productName,int cuantity,String cuantityUnit,int price,String brand,int id){
            this.productName = productName;
            this.cuantity = cuantity;
            this.cuantityUnit = cuantityUnit;
            this.price = price;
            this.brand = brand;
            this.id = id;
        }
        public SelectedProduct(){}

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public void setCuantity(int cuantity) {
            this.cuantity = cuantity;
        }

        public void setCuantityUnit(String cuantityUnit) {
            this.cuantityUnit = cuantityUnit;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNameQuery(){
            String query = "SELECT "+ ContractorTableValues.TablaProducto.NOMBRE
                    +","+ContractorTableValues.TablaProducto._ID
                    +" FROM "+ ContractorTableValues.TablaProducto.TABLE_NAME
                    +" WHERE "+ ContractorTableValues.TablaProducto.NOMBRE +"='"+ productName +"'";
            Log.d("FaltaPan","Recuperando datos de producto:"+query);
            return query;
        }

        public String insertProductInDBQuery(){//convertir en contentValues
            String query = "INSERT INTO "+ ContractorTableValues.TablaListaProducto.TABLE_NAME
                    +"("+ContractorTableValues.TablaListaProducto.ID_LISTA
                    +","+ContractorTableValues.TablaListaProducto.ID_PRODUCTO
                    +","+ContractorTableValues.TablaListaProducto.NUMERO_ELEMENTOS
                    +","+ContractorTableValues.TablaListaProducto.UNIDAD_MEDIDA
                    +","+ContractorTableValues.TablaListaProducto.PRECIO
                    +","+ContractorTableValues.TablaListaProducto.MARCA
                    +") VALUES "
                    +"("+spApp.getListID()
                    +","+ selectedProduct.id
                    +","+ selectedProduct.cuantity
                    +",'"+ selectedProduct.cuantityUnit +"'"
                    +","+ selectedProduct.price
                    +",'"+ selectedProduct.brand +"')";
            Log.d("FaltaPan","Insertando producto en TablaFamiliaProducto:"+query);
            return query;
        }

        public String updateProductInDBQuery(){//convertir en contentValues
            String query = "UPDATE "+ ContractorTableValues.TablaListaProducto.TABLE_NAME
                    +" SET "+ContractorTableValues.TablaListaProducto.NUMERO_ELEMENTOS
                    +"="+ selectedProduct.cuantity
                    +","+ ContractorTableValues.TablaListaProducto.UNIDAD_MEDIDA
                    +"='"+ selectedProduct.cuantityUnit +"'"
                    +","+ ContractorTableValues.TablaListaProducto.PRECIO
                    +"="+selectedProduct.price
                    +","+ContractorTableValues.TablaListaProducto.MARCA
                    +"='"+ selectedProduct.brand +"'"
                    +" WHERE "
                    +ContractorTableValues.TablaListaProducto.ID_LISTA
                    +"="+ spApp.getListID()
                    +" AND "+ ContractorTableValues.TablaListaProducto.ID_PRODUCTO
                    +"="+ selectedProduct.id +";)";
            Log.d("FaltaPan","Actualizando producto en TablaFamiliaProducto:"+query);
            return query;
        }

        public String productAlreadyInListQuery(){
            String query ="SELECT "
                    +ContractorTableValues.TablaListaProducto.ID_LISTA
                    +","+ContractorTableValues.TablaListaProducto.ID_PRODUCTO
                    +" FROM "+ContractorTableValues.TablaListaProducto.TABLE_NAME
                    +" WHERE "+ContractorTableValues.TablaListaProducto.ID_LISTA
                    +"="+spApp.getListID()
                    +" AND "+ContractorTableValues.TablaListaProducto.ID_PRODUCTO
                    +"="+ selectedProduct.id ;
            return query;
        }

        public ContentValues getProductValues(){
            ContentValues cv = new ContentValues();
            cv.put(ContractorTableValues.TablaProducto.NOMBRE,productName);
            cv.put(ContractorTableValues.TablaProducto.FECHA_CREACION,(new SimpleDateFormat("dd/MM/yyyy")).format(new Date()));
            //Falta Definir Familia desde la UI para los productos insertados a mano.
            cv.put(ContractorTableValues.TablaProducto.FAMILIA,"1");
            return cv;
        }
    }
}
