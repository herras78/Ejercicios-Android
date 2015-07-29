package proyects.herras.faltapanv2.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.text.SimpleDateFormat;

import proyects.herras.faltapanv2.R;
import proyects.herras.faltapanv2.contractor.ContractorTableValues;

/* Created by Herras on 26/07/2015.
*/
public class DBDataLoader {

    Context context;
     int[] rawRefs = new int[]{
             R.raw.tiendas,
             R.raw.familias,
             R.raw.lista,
             R.raw.productos,
             R.raw.lista_producto
     };

    public DBDataLoader(Context context){
        this.context = context;
        readRaw(rawRefs);
    }

    private void readRaw(int[] rawItemList){
        for(int rawItem:rawItemList) {
            try {
                InputStream isRaw = context.getResources().openRawResource(rawItem);
                BufferedReader br = new BufferedReader(new InputStreamReader(isRaw));
                String line = "";

                while((line = br.readLine()) !=  null ){
                    insertSplitedLine(line.split(";"),rawItem);
                }

                isRaw.close();

            } catch (Exception ex) {
                Log.e("FaltaPan", "Error al leer fichero desde recurso raw");
            }
        }
    }

     private void insertSplitedLine(String[] splitedLine,int rawRef){
         String tableName = getTableName(rawRef);
         String[] tableheaders = getHeaders(rawRef);
         ContentValues contentValues = new ContentValues();

         for(int i=0;i<splitedLine.length;i++){
             if(tableheaders[i].equals(ContractorTableValues.TablaLista.getRefImagen())){
                 contentValues.put(tableheaders[i], context.getResources().getIdentifier(splitedLine[i].trim(), "drawable", context.getPackageName()));
                // Log.d("FaltaPan",tableheaders[i]+":"+splitedLine[i].trim()+":"+ context.getResources().getIdentifier(splitedLine[i].trim(),"drawable", context.getPackageName()));
             }else if(tableheaders[i].equals(ContractorTableValues.TablaLista.getFechaCreacion())){
                 contentValues.put(tableheaders[i], (new SimpleDateFormat("dd/MM/yyyy")).format(new Date()));
             }else{
                 contentValues.put(tableheaders[i], splitedLine[i]);
                 //Log.d("FaltaPan", tableheaders[i] + ":" + splitedLine[i].trim());
             };
         }

         new DBAcces(context).insertData(tableName,contentValues);
     }

    private String getTableName(int rawRef){
        switch (rawRef){
            case R.raw.lista:
                return ContractorTableValues.TablaLista.TABLE_NAME;
            case R.raw.productos:
                return ContractorTableValues.TablaProducto.TABLE_NAME;
            case R.raw.lista_producto:
                return ContractorTableValues.TablaListaProducto.TABLE_NAME;
            case R.raw.familias:
                return ContractorTableValues.TablaFamilia.TABLE_NAME;
            case R.raw.tiendas:
                return ContractorTableValues.TablaTienda.TABLE_NAME;
            default:
                return null;
        }
    }

    private String[] getHeaders(int rawRef){
        switch (rawRef){
            case R.raw.lista:
                return ContractorTableValues.TablaLista.getCabeceras();
            case R.raw.productos:
                return ContractorTableValues.TablaProducto.getCabeceras();
            case R.raw.lista_producto:
                return ContractorTableValues.TablaListaProducto.getCabeceras();
            case R.raw.familias:
                return ContractorTableValues.TablaFamilia.getCabeceras();
            case R.raw.tiendas:
                return ContractorTableValues.TablaTienda.getCabeceras();
            default:
                return null;
        }
    }
}
