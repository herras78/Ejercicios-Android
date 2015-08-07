package proyects.herras.faltapanv2.support;

import android.database.Cursor;

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


    public void setListSize(DBAcces dba,int listId,int size){
        String query = "UPDATE "+ ContractorTableValues.TablaLista.TABLE_NAME
                +" SET "+ ContractorTableValues.TablaLista.NUM_ELEMENTOS
                +"="+ size
                +" WHERE "+ ContractorTableValues.TablaLista._ID
                +"="+ listId;
        dba.updateDate(query);
    }

    public void setListBuyedSize(DBAcces dba,int listId,int size){
        String query = "UPDATE "+ ContractorTableValues.TablaLista.TABLE_NAME
                +" SET "+ ContractorTableValues.TablaLista.NUM_ELEMENTOS_COMPRADOS
                +"="+ size
                +" WHERE "+ ContractorTableValues.TablaLista._ID
                +"="+ listId;
        dba.updateDate(query);
    }
}

