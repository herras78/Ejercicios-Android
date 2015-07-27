package com.faltapan.bd;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import android.util.Log;

public class CreacionDeBD {
	
	
	public String CrearTabla(LinkedHashMap<String, String> campos)
	{
		String query_crear_tabla = "" ;

		Iterator it = campos.entrySet().iterator();
		while (it.hasNext()) 
		{

			Map.Entry e = (Map.Entry)it.next();
			if(e.getKey().equals("TABLE_NAME"))
			{
				query_crear_tabla = "CREATE TABLE " + e.getValue() + " (";
			}else
			{
				query_crear_tabla += e.getValue() ;
				if(it.hasNext())
				{
					query_crear_tabla += ", ";
				}
				else
				{
					query_crear_tabla += "); ";
				}
			}	
		}
		
		return query_crear_tabla;		
	}

	public String[] BorrarTabla(String[] tablas)
	{
		String[] queries = new String[tablas.length];
		
		for(int i = 0 ; i < tablas.length;i++)
		{
			queries[i]= "DROP TABLE IF EXISTS "+ tablas[i];
		}
		
		return queries;
	}
}
