package proyects.herras.faltapanv2.bbdd;

import android.content.Context;
import android.util.Log;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import proyects.herras.faltapanv2.contractor.ContractorTableValues;

/**
 * Created by Herras on 24/07/2015.
 */
public class DBStructureBuilder {

    private static String CREATE = "CREATE TABLE ";
    private static String TABLE_NAME = "TABLE_NAME";

    public DBStructureBuilder(Context context){
       // Log.d("FaltaPan", "DBStructureBulder,llamando al contructor");
        DBAcces dbAcces = new DBAcces(context);
        dbAcces.insertData(creaTabla(new ContractorTableValues.TablaLista().setEstructura()));
        dbAcces.insertData(creaTabla(new ContractorTableValues.TablaProducto().setEstructura()));
        dbAcces.insertData(creaTabla(new ContractorTableValues.TablaListaProducto().setEstructura()));
        dbAcces.insertData(creaTabla(new ContractorTableValues.TablaFamilia().setEstructura()));
        dbAcces.insertData(creaTabla(new ContractorTableValues.TablaTienda().setEstructura()));
    }

    public String creaTabla(LinkedHashMap<String, String> estructura){

        String query_crea_tabla = "";
        Iterator it = estructura.entrySet().iterator();

        while (it.hasNext()){
            Map.Entry e = (Map.Entry) it.next();
            if(e.getKey().equals(TABLE_NAME)){
                query_crea_tabla += CREATE + e.getValue() + " (";
            }else{
                query_crea_tabla += e.getValue();
                if(it.hasNext()){
                    query_crea_tabla += ", ";
                }else{
                    query_crea_tabla += "); ";
                }
            }
        }
        Log.d("FaltaPan", "DBStructureBulder,Procesando estructura,se genera la query:" + query_crea_tabla );
         return query_crea_tabla;
    }

}
