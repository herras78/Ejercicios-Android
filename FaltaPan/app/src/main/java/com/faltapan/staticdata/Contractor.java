package com.faltapan.staticdata;

import java.util.HashMap;
import java.util.LinkedHashMap;

import android.provider.BaseColumns;
import android.util.Log;

public class Contractor
{
	public static final String DB_NAME = "DB_FALTAPAN.db";
	public static final  int VERSION  = 20;
	
	public String[] getTableNames(){
		String[] tablas = {new TablaListaProducto().TABLE_NAME,new TablaFamiliaProducto().TABLE_NAME,
				new TablaLista().TABLE_NAME,new TablaProducto().TABLE_NAME,new TablaFamilia().TABLE_NAME};
		return tablas;
	}
	
	public static String getDbName(){
		return DB_NAME;
	}

	public static int getVersion(){
		return VERSION;
	}

	public static class TablaLista implements BaseColumns
	{
		public static final String TABLE_NAME = "T_LISTA";
		
		public static final String NOMBRE = "NOMBRE";
		public static final String FECHA_CREACION = "FECHA_CREACION";
		public static final String NUM_ELEMENTOS = "NUM_ELEMENTOS";
		public static final String NOMBRE_TIENDA = "NOMBRE_TIENDA";
		public static final String REF_IMAGEN = "REF_IMAGEN";
		
		public static final String PARAM_ID = "INTEGER PRIMARY KEY AUTOINCREMENT";
		public static final String PARAM_NOMBRE = "TEXT NOT NULL";
		public static final String PARAM_FECHA_CREACION = "INTEGER NOT NULL";
		public static final String PARAM_NUM_ELEMENTOS = "INTEGER";
		public static final String PARAM_NOMBRE_TIENDA = "TEXT";
		public static final String PARAM_REF_IMAGEN = "INTEGER";
		
		public LinkedHashMap<String, String> setEstructura(){
			LinkedHashMap<String, String> hm_estructura = new LinkedHashMap<String, String>();
			
			hm_estructura.put("TABLE_NAME", TABLE_NAME);
			hm_estructura.put("ID", TablaLista._ID +  " "  + PARAM_ID);
			hm_estructura.put("NOMBRE", NOMBRE+  " "  +PARAM_NOMBRE);
			hm_estructura.put("FECHA_CREACION", FECHA_CREACION +  " "  + PARAM_FECHA_CREACION);
			hm_estructura.put("NUM_ELEMENTOS", NUM_ELEMENTOS +  " " + PARAM_NUM_ELEMENTOS);
			hm_estructura.put("NOMBRE_TIENDA", NOMBRE_TIENDA + " " + PARAM_NOMBRE_TIENDA);
			hm_estructura.put("REF_IMAGEN", REF_IMAGEN + " " + PARAM_REF_IMAGEN);
			
			return hm_estructura;			
		}
		
		public String[] nomCabeceras()
		{
			String[] nombre_cabeceras = new String[]{NOMBRE, NUM_ELEMENTOS};
			
			return nombre_cabeceras;	
		}
		
		public static String getTableName() {
			return TABLE_NAME;
		}

		public static String getNombre() {
			return NOMBRE;
		}

		public static String getFechaCreacion() {
			return FECHA_CREACION;
		}

		public static String getNumElementos() {
			return NUM_ELEMENTOS;
		}

		public static String getNombreTienda() {
			return NOMBRE_TIENDA;
		}
	}
	
	public static class TablaProducto implements BaseColumns
	{

		public static final String TABLE_NAME = "T_PRODUCTO";

		public static final String NOMBRE = "NOMBRE";
		public static final String FECHA_CREACION = "FECHA_CREACION";
		public static final String FAMILIA = "FAMILIA";
		public static final String REF_IMAGEN = "REF_IMAGEN";
		
		public static final String PARAM_ID = "INTEGER PRIMARY KEY AUTOINCREMENT";
		public static final String PARAM_NOMBRE = "TEXT NOT NULL";
		public static final String PARAM_FECHA_CREACION = "INTEGER";
		public static final String PARAM_FAMILIA = "TEXT";
		public static final String PARAM_REF_IMAGEN = "INTEGER";
		
		public LinkedHashMap<String, String> setEstructura(){
			LinkedHashMap<String, String> hm_estructura = new LinkedHashMap<String, String>();
			
			hm_estructura.put("TABLE_NAME", TABLE_NAME);
			hm_estructura.put("ID", TablaProducto._ID + " " + PARAM_ID);
			hm_estructura.put("NOMBRE", NOMBRE + " " + PARAM_NOMBRE);
			hm_estructura.put("FECHA_CREACION", FECHA_CREACION + " " + PARAM_FECHA_CREACION);
			hm_estructura.put("FAMILIA", FAMILIA + " " + PARAM_FAMILIA);
			hm_estructura.put("REF_IMAGEN", REF_IMAGEN + " " + PARAM_REF_IMAGEN);
			
			return hm_estructura;			
		}
		public static String getTableName() {
			return TABLE_NAME;
		}

		public static String getNombre() {
			return NOMBRE;
		}

		public static String getFechaCreacion() {
			return FECHA_CREACION;
		}

		public static String getFamilia() {
			return FAMILIA;
		}

		public static String getRefImagen() {
			return REF_IMAGEN;
		}	
	}
	
	public static class TablaFamilia implements BaseColumns	
	{
		public static final String TABLE_NAME = "T_FAMILIA";
		
		public static final String NOMBRE = "NOMBRE";
		public static final String REF_IMAGEN = "REF_IMAGEN";
		
		public static final String PARAM_ID = "INTEGER PRIMARY KEY AUTOINCREMENT";
		public static final String PARAM_NOMBRE = "TEXT NOT NULL";
		public static final String PARAM_REF_IMAGEN = "INTEGER";
		
		public LinkedHashMap<String, String> setEstructura()
		{
			LinkedHashMap<String, String> hm_estructura = new LinkedHashMap<String, String>();
			
			hm_estructura.put("TABLE_NAME", TABLE_NAME);
			hm_estructura.put("ID", TablaFamilia._ID + " " + PARAM_ID);
			hm_estructura.put("NOMBRE", NOMBRE + " " + PARAM_NOMBRE);
			hm_estructura.put("REF_IMAGEN", REF_IMAGEN + " " + PARAM_REF_IMAGEN);
			return hm_estructura;		
		}
		
		public static String getTableName() {
			return TABLE_NAME;
		}

		public static String getNombre() {
			return NOMBRE;
		}

		public static String getRefImagen() {
			return REF_IMAGEN;
		}
	}
	
	
	public static class TablaListaProducto implements BaseColumns
	{
		public static final String TABLE_NAME = "T_A_LISTAPRODUCTO";
		
		public static final String ID_LISTA = "ID_LISTA";
		public static final String ID_PRODUCTO = "ID_PRODUCTO";		
		
		public static final String CONSTRAINT_PK_ID_LISTA_PRODUCTO = "CONSTRAINT PK_ID_LISTA_PRODUCTO PRIMARY KEY(ID_LISTA, ID_PRODUCTO)";
		public static final String CONSTRAINT_FK_LISTAPRODUCTO_LISTA = "CONSTRAINT FK_LISTAPRODUCTO_LISTA FOREIGN KEY(ID_LISTA) REFERENCES T_LISTA("+ TablaLista._ID +")";
		public static final String CONSTRAINT_FK_LISTAPRODUCTO_PRODUCTO = "CONSTRAINT FK_LISTAPRODUCTO_PRODUCTO FOREIGN KEY(ID_PRODUCTO) REFERENCES T_PRODUCTO("+ TablaProducto._ID +")";
		
		public LinkedHashMap<String, String> setEstructura()
		{
			LinkedHashMap<String, String> hm_estructura = new LinkedHashMap<String, String>();
			
			hm_estructura.put("TABLE_NAME", TABLE_NAME);			
			hm_estructura.put("ID_LISTA", ID_LISTA );
			hm_estructura.put("ID_PRODUCTO", ID_PRODUCTO);
			hm_estructura.put("CONSTRAINT_PK_ID_LISTA_PRODUCTO", CONSTRAINT_PK_ID_LISTA_PRODUCTO);
			hm_estructura.put("CONSTRAINT_FK_LISTAPRODUCTO_LISTA", CONSTRAINT_FK_LISTAPRODUCTO_LISTA);
			hm_estructura.put("CONSTRAINT_FK_LISTAPRODUCTO_PRODUCTO", CONSTRAINT_FK_LISTAPRODUCTO_PRODUCTO);
			
			return hm_estructura;	
		}
	}
	
	public static class TablaFamiliaProducto implements BaseColumns
	{
		public static final String TABLE_NAME = "T_A_FAMILIAPRODUCTO";
		
		public static final String ID_FAMILIA = "ID_FAMILIA";
		public static final String ID_PRODUCTO = "ID_PRODUCTO";
		
		public static final String CONSTRAINT_PK_ID_FAMILIA_PRODUCTO = "CONSTRAINT PK_ID_FAMILIA_PRODUCTO PRIMARY KEY(ID_FAMILIA, ID_PRODUCTO)";
		public static final String CONSTRAINT_FK_FAMILIAPRODUCTO_FAMILIA = "CONSTRAINT FK_FAMILIAPRODUCTO_FAMILIA FOREIGN KEY(ID_FAMILIA) REFERENCES T_FAMILIA("+ TablaFamilia._ID +")";
		public static final String CONSTRAINT_FK_FAMILIAPRODUCTO_PRODUCTO = "CONSTRAINT FK_FAMILIAPRODUCTO_PRODUCTO FOREIGN KEY(ID_PRODUCTO) REFERENCES T_PRODUCTO("+ TablaProducto._ID +")";
		
		public LinkedHashMap<String, String> setEstructura()
		{
			LinkedHashMap<String, String> hm_estructura = new LinkedHashMap<String, String>();
			
			hm_estructura.put("TABLE_NAME", TABLE_NAME);
			hm_estructura.put("ID_FAMILIA", ID_FAMILIA );
			hm_estructura.put("ID_PRODUCTO", ID_PRODUCTO);
			hm_estructura.put("CONSTRAINT_PK_ID_FAMILIA_PRODUCTO", CONSTRAINT_PK_ID_FAMILIA_PRODUCTO);
			hm_estructura.put("CONSTRAINT_FK_FAMILIAPRODUCTO_FAMILIA", CONSTRAINT_FK_FAMILIAPRODUCTO_FAMILIA);
			hm_estructura.put("CONSTRAINT_FK_FAMILIAPRODUCTO_PRODUCTO", CONSTRAINT_FK_FAMILIAPRODUCTO_PRODUCTO);
			
			return hm_estructura;			
		}
	}	
}

