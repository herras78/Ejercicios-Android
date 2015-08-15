package proyects.herras.faltapanv2.support;

import android.database.Cursor;
import android.util.Log;

import proyects.herras.faltapanv2.bbdd.DBAcces;
import proyects.herras.faltapanv2.contractor.ContractorTableValues;

/**
 * Created by Guada on 06/08/2015.
 */
public class ListTools {

    public int getListSize(DBAcces dba,int listId){
        String query = "SELECT COUNT("+ ContractorTableValues.TablaListaProducto.ID_LISTA
                +") FROM "+ContractorTableValues.TablaListaProducto.TABLE_NAME
                +" WHERE "+ContractorTableValues.TablaListaProducto.ID_LISTA
                +"="+listId+";";

        Cursor c = dba.getCursor(query);
        c.moveToFirst();

        return c.getInt(0);
    }

    public int getListBuyedSize(DBAcces dba,int listId){
        String query = "SELECT COUNT("+ ContractorTableValues.TablaListaProducto.ID_LISTA
                +") FROM "+ ContractorTableValues.TablaListaProducto.TABLE_NAME
                +" WHERE "+ ContractorTableValues.TablaListaProducto.ID_LISTA
                +"="+listId
                +" AND "+ ContractorTableValues.TablaListaProducto.ESTADO_PRODUCTO
                +"= 'C'";

        Cursor c = dba.getCursor(query);
        c.moveToFirst();

        return c.getInt(0);
    }

    public void setListSize(DBAcces dba,int listId){
        String query = "UPDATE "+ ContractorTableValues.TablaLista.TABLE_NAME
                +" SET "+ ContractorTableValues.TablaLista.NUM_ELEMENTOS
                +"="+ getListSize(dba,listId)
                +" WHERE "+ ContractorTableValues.TablaLista._ID
                +"="+ listId;
        dba.updateDate(query);
    }

    public void setListBuyedSize(DBAcces dba,int listId){

        String query = "UPDATE "+ ContractorTableValues.TablaLista.TABLE_NAME
                +" SET "+ ContractorTableValues.TablaLista.NUM_ELEMENTOS_COMPRADOS
                +"="+  getListBuyedSize(dba, listId)
                +" WHERE "+ ContractorTableValues.TablaLista._ID
                +"="+ listId;
        dba.updateDate(query);
    }

    public void deleteProductOnList(DBAcces dba,int listId,int prodId){
        String query =("DELETE FROM "+ContractorTableValues.TablaListaProducto.TABLE_NAME
                +" WHERE "+ ContractorTableValues.TablaListaProducto.ID_LISTA
                +"="+ listId
                +" AND "+ ContractorTableValues.TablaListaProducto.ID_PRODUCTO
                +"="+ prodId +";");
        dba.deleteDate(query);
    }

    public void deleteList(DBAcces dba,int id){
        Log.d("FaltaPan", "deleteList:" + id);

        String queryListaProductos="DELETE FROM "+ ContractorTableValues.TablaListaProducto.TABLE_NAME
                +" WHERE "+ ContractorTableValues.TablaListaProducto.ID_LISTA
                +"="+id;
        String queryLista="DELETE FROM "+ ContractorTableValues.TablaLista.TABLE_NAME
                +" WHERE "+ ContractorTableValues.TablaListaProducto._ID
                +"="+id;

        Log.d("FaltaPan", "Ejecutando queryListaProductos" + queryListaProductos);
        Log.d("FaltaPan", "Ejecutando queryLista" + queryLista);
        dba.deleteDate(queryListaProductos);
        dba.deleteDate(queryLista);
    }
}

