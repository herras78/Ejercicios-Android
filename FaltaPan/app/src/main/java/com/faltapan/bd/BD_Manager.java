package com.faltapan.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.faltapan.activities.R;
import com.faltapan.staticdata.Contractor;
import com.faltapan.staticdata.Contractor.TablaFamilia;
import com.faltapan.staticdata.Contractor.TablaFamiliaProducto;
import com.faltapan.staticdata.Contractor.TablaLista;
import com.faltapan.staticdata.Contractor.TablaListaProducto;
import com.faltapan.staticdata.Contractor.TablaProducto;

public class BD_Manager extends SQLiteOpenHelper{
	
	
	static Contractor contractor = new Contractor();
    
    public BD_Manager(Context contexto) 
    {	
        super(contexto, contractor.getDbName(), null, contractor.getVersion());
    }
 
    public BD_Manager(Context contexto, String nombre, CursorFactory factory, int version) 
    {
        super(contexto, nombre, factory, version);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) 
    {   	
    	
    	creaTablasInsertaDatos(db);
          
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) 
    {   
    	Log.i("FALTAPAN","En OnUpgrade DB_Manager");   	
    	
    	String[] queries_borrado = new CreacionDeBD().BorrarTabla(new Contractor().getTableNames());
    	
    	for(String query:queries_borrado)
    	{
    		db.execSQL(query);
    	}
        
    	creaTablasInsertaDatos(db);
    }
    
    public void creaTablasInsertaDatos(SQLiteDatabase db){
    	
    	db.execSQL(new CreacionDeBD().CrearTabla(new TablaLista().setEstructura()));
        db.execSQL(new CreacionDeBD().CrearTabla(new TablaProducto().setEstructura()));
        db.execSQL(new CreacionDeBD().CrearTabla(new TablaFamilia().setEstructura()));
        db.execSQL(new CreacionDeBD().CrearTabla(new TablaListaProducto().setEstructura()));
        db.execSQL(new CreacionDeBD().CrearTabla(new TablaFamiliaProducto().setEstructura()));
    	
    	db.execSQL("INSERT INTO T_LISTA VALUES(10,'Lunes','05/06','10 Productos','Carrefour'," + R.drawable.ic_faltapan_i + ")");
    	db.execSQL("INSERT INTO T_LISTA VALUES(11,'Compra Martes','05/04/2013','5 Productos','Dia'," + R.drawable.ic_faltapan_ii + ")");
    	db.execSQL("INSERT INTO T_LISTA VALUES(12,'Cena Domingo','05/04/2013','8 Productos','Dia'," + R.drawable.ic_faltapan_iii + ")");
    	db.execSQL("INSERT INTO T_LISTA VALUES(13,'Material Escolar','05/04/2013','3 Productos','Dia'," + R.drawable.ic_faltapan_i + ")");
    	db.execSQL("INSERT INTO T_LISTA VALUES(14,'Lista Cumpleaños','05/04/2013','2 Productos','Dia'," + R.drawable.ic_faltapan_ii + ")");
    	db.execSQL("INSERT INTO T_LISTA VALUES(15,'Cena Domingo','05/04/2013','3 Productos','Dia'," + R.drawable.ic_faltapan_iii + ")");
       
    	
    	db.execSQL("INSERT INTO T_PRODUCTO VALUES(10,'LIMON','05/06','FRUTA'," + R.drawable.ic_faltapan_i + ")");
    	db.execSQL("INSERT INTO T_PRODUCTO VALUES(11,'CACA','05/04/2013','MANJAR'," + R.drawable.ic_faltapan_i + ")");
    	db.execSQL("INSERT INTO T_PRODUCTO VALUES(12,'PUTILLAS','05/04/2013','LUJO'," + R.drawable.ic_faltapan_i + ")");
    	db.execSQL("INSERT INTO T_PRODUCTO VALUES(13,'MANZANA','05/04/2013','FRUTA'," + R.drawable.ic_faltapan_i + ")");
    	db.execSQL("INSERT INTO T_PRODUCTO VALUES(14,'MERLUZA','05/04/2013','PESCADO'," + R.drawable.ic_faltapan_i + ")");
    	db.execSQL("INSERT INTO T_PRODUCTO VALUES(15,'FRESAS','05/04/2013','FRUTA'," + R.drawable.ic_faltapan_i + ")");
    	
    	db.execSQL("INSERT INTO T_A_LISTAPRODUCTO VALUES(10,11)");
    	db.execSQL("INSERT INTO T_A_LISTAPRODUCTO VALUES(10,15)");
    	db.execSQL("INSERT INTO T_A_LISTAPRODUCTO VALUES(12,10)");
    	db.execSQL("INSERT INTO T_A_LISTAPRODUCTO VALUES(13,14)");
    	db.execSQL("INSERT INTO T_A_LISTAPRODUCTO VALUES(14,15)");
    	db.execSQL("INSERT INTO T_A_LISTAPRODUCTO VALUES(15,13)");
    }

}
