package com.valles.bodytrain.db;

import com.valles.bodytrain.R;
import com.valles.bodytrain.EntreList.EntreItemList;
import com.valles.bodytrain.R.drawable;
import com.valles.bodytrain.db.BDTContract.EjercicioTable;
import com.valles.bodytrain.db.BDTContract.EntrenamientosTable;
import com.valles.bodytrain.db.BDTContract.HistorialTable;
import com.valles.bodytrain.db.BDTContract.RelacionTable;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BodyDB extends SQLiteOpenHelper {
	
	private static final int DB_VERSION = 19;

	private static final String sqlCreaEntre = "CREATE TABLE "+ EntrenamientosTable.TABLE_NAME +" ("
			+ EntrenamientosTable.ID_ENTRE +" INTEGER PRIMARY KEY,"+ EntrenamientosTable.TIT_ENTRE +" TEXT,"
			+ EntrenamientosTable.IMG_ENTRE +" INTEGER,"+ EntrenamientosTable.INT_ENTRE +" TEXT,"
			+ EntrenamientosTable.TIME_ENTRE +" INTEGER,"+ EntrenamientosTable.NUM_ENTRE+" INTEGER,"
			+ EntrenamientosTable.TIPO_ENTRE +" BOOLEAN DEFAULT 'false')";
	private static final String sqlCreaEje = "CREATE TABLE "+ EjercicioTable.TABLE_NAME +" ("
			+ EjercicioTable.ID_EJER +" INTEGER PRIMARY KEY,"+ EjercicioTable.TIT_EJER +" TEXT,"
			+ EjercicioTable.IMG_EJER +" INTEGER,"+ EjercicioTable.SERIES_EJER +" INTEGERT,"
			+ EjercicioTable.REP_EJER +" INTEGER,"+ EjercicioTable.PESO_EJER +" TEXT,"
			+ EjercicioTable.TIME_EJER +" INTEGER,"+ EjercicioTable.GRUPO_EJER +" TEXT)";
	private static final String sqlCreaRelacion = "CREATE TABLE "+ RelacionTable.TABLE_NAME +" ("
			+ RelacionTable.ID_RUTINA +" INTEGER ,"+ RelacionTable.ID_EJERCICIO +" INTEGER)";
	private static final String sqlHistorial = "CREATE TABLE "+ HistorialTable.TABLE_NAME +"("
			+ HistorialTable.ID_HIST +" INTEGER PRIMARY KEY,"+ HistorialTable.DATE_HIST +" INTEGER,"
			+ HistorialTable.TIT_HIST +" TEXT,"+ HistorialTable.IMG_HIST +" INTEGER,"
			+ HistorialTable.SERIES_HIST +" INTEGERT,"+ HistorialTable.ACTUAL_SERIES_HIST +" INTEGER,"
			+ HistorialTable.SERIE1_PESO_HIST +" INTEGER DEFAULT 0,"+ HistorialTable.SERIE1_REP_HIST +" INTEGER DEFAULT 0,"
			+ HistorialTable.SERIE2_PESO_HIST +" INTEGER DEFAULT 0,"+ HistorialTable.SERIE2_REP_HIST +" INTEGER DEFAULT 0,"
			+ HistorialTable.SERIE3_PESO_HIST +" INTEGER DEFAULT 0,"+ HistorialTable.SERIE3_REP_HIST +" INTEGER DEFAULT 0,"
			+ HistorialTable.REPET_TIME_HIST +" INTEGER,"+ HistorialTable.GRUPO_EJE_HIST +" TEXT,"
			+ HistorialTable.TIT_ENTRE_HIST +" TEXT DEFAULT 'Rutina Libre')";
	private static final String sqlCreaRutina = "CREATE TABLE Rutina (ejeid INTEGER ,ejetit TEXT,ejeimg INTEGER,ejeseries INTEGERT,seriescatual INTEGER,pesoserie1 INTEGER DEFAULT 0,repserie1 INTEGER DEFAULT 0,pesoserie2 INTEGER DEFAULT 0,repserie2 INTEGER DEFAULT 0,pesoserie3 INTEGER DEFAULT 0,repserie3 INTEGER DEFAULT 0,timerepet INTEGER,ejegrupo TEXT)";
	private static final String sqlEstado = "CREATE TABLE Estado(est_id INTEGER,est_titulo TEXT DEFAULT 'Sin Titulo',est_rutina TEXT DEFAULT 'false',est_entrada text DEFAULT 'false' )";		
	/*
	 * est_rutina  true:hay una rutina activa ;false:no hay rutina activa
	 * est_entrada true:se entro desde mis rutinas;false:se entro desde rutinas guiadas 
	 */
	private static final String[] crearTablas = {sqlCreaEntre,sqlCreaEje,sqlCreaRelacion,sqlCreaRutina,sqlHistorial,sqlEstado};
	
	//private static final String sqlCreaImageeje = "CREATE TABLE Imagenejer (imageid INTEGER PRIMARY KEY,imagename INTEGER)";
	//private static final String sqlCreaImageentre = "CREATE TABLE Imagenentre (imageid INTEGER PRIMARY KEY,imagename INTEGER)";
	
	public BodyDB(Context context) {
		super(context,BDTContract.DB_NAME, null, DB_VERSION);	
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		DropTablas( db);
		
		for(String tabla:crearTablas){
			db.execSQL(tabla);
		}
	
		LoadData(db);
	}

	public void DropTablas(SQLiteDatabase db){
		
		final String[] tablaDrop = {"Entrenamientos","Ejercicios","Imagenentre","Imagenejer","Relacion","Rutina","Historial","Estado"};
		
		for(String tabla:tablaDrop){
			db.execSQL("DROP TABLE IF EXISTS " + tabla);
		}
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 	
		DropTablas( db);
		
		for(String tabla:crearTablas){
			db.execSQL(tabla);
		}
		
		LoadData(db);
	}
	
	
	public void LoadData(SQLiteDatabase db){
		//Inicializando estado, <<<CAMBIAR ESTO PARA TRABAJAR CON SYSTEMPREFERENCES>>>
		db.execSQL("INSERT INTO Estado (est_id,est_titulo,est_rutina,est_entrada) VALUES(0,'Sin titulo','false','false')");
		//Eentrenamientos
		db.execSQL("INSERT INTO Entrenamientos(entretit,entreimg,entreint,entretime,entrenum) VALUES('Estiramientos Superior'," + R.drawable.eje5 + ",'medio',400,5);");
		db.execSQL("INSERT INTO Entrenamientos(entretit,entreimg,entreint,entretime,entrenum) VALUES('Estiramientos Inferior'," + R.drawable.eje3 + ",'bajo',240,5);");
		db.execSQL("INSERT INTO Entrenamientos(entretit,entreimg,entreint,entretime,entrenum) VALUES('Tronco Superior Basico'," + R.drawable.eje1 + ",'bajo',300,5);");
		db.execSQL("INSERT INTO Entrenamientos(entretit,entreimg,entreint,entretime,entrenum) VALUES('Tronco Inferior Basico'," + R.drawable.eje4 + ",'medio',700,5);");
		db.execSQL("INSERT INTO Entrenamientos(entretit,entreimg,entreint,entretime,entrenum) VALUES('Espalda,Dorsales y Hombros'," + R.drawable.eje2 + ",'alto',600,3);");
		db.execSQL("INSERT INTO Entrenamientos(entretit,entreimg,entreint,entretime,entrenum) VALUES('Biceps,Triceps y Pectoral'," + R.drawable.brazosbiceps + ",'media',600,3);");
		
		//Estiramientos 
		db.execSQL("INSERT INTO Ejercicios(ejetit,ejeimg,ejeseries,ejerep,ejepeso,ejetime,ejegrupo) VALUES('Cuello II'," + R.drawable.cuello + ",2,10,0,10,'cuello');");
		db.execSQL("INSERT INTO Ejercicios(ejetit,ejeimg,ejeseries,ejerep,ejepeso,ejetime,ejegrupo) VALUES('Cuello I'," + R.drawable.cuello + ",3,10,0,10,'cuello');");
		db.execSQL("INSERT INTO Ejercicios(ejetit,ejeimg,ejeseries,ejerep,ejepeso,ejetime,ejegrupo) VALUES('Pectoral I'," + R.drawable.pecho + ",10,1,0,10,'pectoral');");
		db.execSQL("INSERT INTO Ejercicios(ejetit,ejeimg,ejeseries,ejerep,ejepeso,ejetime,ejegrupo) VALUES('Pectoral II'," + R.drawable.pecho + ",3,15,0,10,'pectoral');");
		db.execSQL("INSERT INTO Ejercicios(ejetit,ejeimg,ejeseries,ejerep,ejepeso,ejetime,ejegrupo) VALUES('Hombros I'," + R.drawable.hombros + ",1,25,0,10,'hombros');");
		db.execSQL("INSERT INTO Ejercicios(ejetit,ejeimg,ejeseries,ejerep,ejepeso,ejetime,ejegrupo) VALUES('Hombros II'," + R.drawable.hombros + ",1,15,0,10,'hombros');");
		db.execSQL("INSERT INTO Ejercicios(ejetit,ejeimg,ejeseries,ejerep,ejepeso,ejetime,ejegrupo) VALUES('Est. Abductores I'," + R.drawable.traserapiernas + ",2,1,0,10,'estiramiento');");		
		db.execSQL("INSERT INTO Ejercicios(ejetit,ejeimg,ejeseries,ejerep,ejepeso,ejetime,ejegrupo) VALUES('Est. ABS I'," + R.drawable.abdomen + ",3,12,0,10,'abs');");
		db.execSQL("INSERT INTO Ejercicios(ejetit,ejeimg,ejeseries,ejerep,ejepeso,ejetime,ejegrupo) VALUES('Est. ABS II'," + R.drawable.abdomenlateral + ",1,12,0,10,'abs');");
		db.execSQL("INSERT INTO Ejercicios(ejetit,ejeimg,ejeseries,ejerep,ejepeso,ejetime,ejegrupo) VALUES('Cuadriceps I'," + R.drawable.piernas + ",2,18,0,10,'cuadriceps');");
		db.execSQL("INSERT INTO Ejercicios(ejetit,ejeimg,ejeseries,ejerep,ejepeso,ejetime,ejegrupo) VALUES('Cuadriceps II'," + R.drawable.piernas + ",3,18,0,10,'cuadriceps');");
		db.execSQL("INSERT INTO Ejercicios(ejetit,ejeimg,ejeseries,ejerep,ejepeso,ejetime,ejegrupo) VALUES('Gemelos I'," + R.drawable.tobillos+ ",1,18,0,10,'gemelo');");
		db.execSQL("INSERT INTO Ejercicios(ejetit,ejeimg,ejeseries,ejerep,ejepeso,ejetime,ejegrupo) VALUES('Gemelos II'," + R.drawable.tobillos + ",3,20,0,10,'gemelo');");
		db.execSQL("INSERT INTO Ejercicios(ejetit,ejeimg,ejeseries,ejerep,ejepeso,ejetime,ejegrupo) VALUES('Est. Cuello I'," + R.drawable.eje6 + ",2,10,0,10,'estiramiento');");		
		
		//Carga de relaciones
		db.execSQL("INSERT INTO Relacion(id_rutina,id_ejercicio) VALUES(1,1);");
		db.execSQL("INSERT INTO Relacion(id_rutina,id_ejercicio) VALUES(1,4);");
		db.execSQL("INSERT INTO Relacion(id_rutina,id_ejercicio) VALUES(1,10);");
		db.execSQL("INSERT INTO Relacion(id_rutina,id_ejercicio) VALUES(1,11);");
		db.execSQL("INSERT INTO Relacion(id_rutina,id_ejercicio) VALUES(1,2);");
		db.execSQL("INSERT INTO Relacion(id_rutina,id_ejercicio) VALUES(2,3);");
		db.execSQL("INSERT INTO Relacion(id_rutina,id_ejercicio) VALUES(2,7);");
		db.execSQL("INSERT INTO Relacion(id_rutina,id_ejercicio) VALUES(2,13);");
		db.execSQL("INSERT INTO Relacion(id_rutina,id_ejercicio) VALUES(2,12);");
		db.execSQL("INSERT INTO Relacion(id_rutina,id_ejercicio) VALUES(2,6);");
		db.execSQL("INSERT INTO Relacion(id_rutina,id_ejercicio) VALUES(3,3);");
		db.execSQL("INSERT INTO Relacion(id_rutina,id_ejercicio) VALUES(3,4);");
		db.execSQL("INSERT INTO Relacion(id_rutina,id_ejercicio) VALUES(3,7);");
		db.execSQL("INSERT INTO Relacion(id_rutina,id_ejercicio) VALUES(3,9);");
		db.execSQL("INSERT INTO Relacion(id_rutina,id_ejercicio) VALUES(3,10);");
		db.execSQL("INSERT INTO Relacion(id_rutina,id_ejercicio) VALUES(4,11);");
		db.execSQL("INSERT INTO Relacion(id_rutina,id_ejercicio) VALUES(4,14);");
		db.execSQL("INSERT INTO Relacion(id_rutina,id_ejercicio) VALUES(4,13);");
		db.execSQL("INSERT INTO Relacion(id_rutina,id_ejercicio) VALUES(5,5);");
		db.execSQL("INSERT INTO Relacion(id_rutina,id_ejercicio) VALUES(5,8);");
		db.execSQL("INSERT INTO Relacion(id_rutina,id_ejercicio) VALUES(5,10);");
		db.execSQL("INSERT INTO Relacion(id_rutina,id_ejercicio) VALUES(6,14);");
		db.execSQL("INSERT INTO Relacion(id_rutina,id_ejercicio) VALUES(6,8);");
		db.execSQL("INSERT INTO Relacion(id_rutina,id_ejercicio) VALUES(6,9);");
		db.execSQL("INSERT INTO Relacion(id_rutina,id_ejercicio) VALUES(6,1);");
		db.execSQL("INSERT INTO Relacion(id_rutina,id_ejercicio) VALUES(6,1);");
	}
}
