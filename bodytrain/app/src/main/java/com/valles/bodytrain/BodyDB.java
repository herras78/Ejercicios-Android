package com.valles.bodytrain;

import com.valles.bodytrain.EntreList.EntreItemList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BodyDB extends SQLiteOpenHelper {

	String sqlCreaEntre = "CREATE TABLE Entrenamientos (entreid INTEGER PRIMARY KEY, entretit TEXT,entreimg INTEGER,entreint TEXT,entretime INTEGER,entrenum INTEGER,entretipo BOOLEAN DEFAULT 'false')";
	String sqlCreaEje = "CREATE TABLE Ejercicios (ejeid INTEGER PRIMARY KEY,ejetit TEXT,ejeimg INTEGER,ejeseries INTEGERT,ejerep INTEGER,ejepeso TEXT,ejetime INTEGER,ejegrupo TEXT)";
	String sqlCreaImageeje = "CREATE TABLE Imagenejer (imageid INTEGER PRIMARY KEY,imagename INTEGER)";
	String sqlCreaImageentre = "CREATE TABLE Imagenentre (imageid INTEGER PRIMARY KEY,imagename INTEGER)";
	String sqlCreaRelacion = "CREATE TABLE Relacion (id_rutina INTEGER ,id_ejercicio INTEGER)";
	String sqlCreaRutina = "CREATE TABLE Rutina (ejeid INTEGER ,ejetit TEXT,ejeimg INTEGER,ejeseries INTEGERT,seriescatual INTEGER,pesoserie1 INTEGER DEFAULT 0,repserie1 INTEGER DEFAULT 0,pesoserie2 INTEGER DEFAULT 0,repserie2 INTEGER DEFAULT 0,pesoserie3 INTEGER DEFAULT 0,repserie3 INTEGER DEFAULT 0,timerepet INTEGER,ejegrupo TEXT)";
	String sqlHistorial = "CREATE TABLE Historial(histid INTEGER PRIMARY KEY,histdate INTEGER,histtit TEXT,histimg INTEGER,histseries INTEGERT,histseriescatual INTEGER,histpesoserie1 INTEGER DEFAULT 0,histrepserie1 INTEGER DEFAULT 0,histpesoserie2 INTEGER DEFAULT 0,histrepserie2 INTEGER DEFAULT 0,histpesoserie3 INTEGER DEFAULT 0,histrepserie3 INTEGER DEFAULT 0,histtimerepet INTEGER,histejegrupo TEXT,histruttit TEXT DEFAULT 'Rutina Libre')";
	String sqlEstado = "CREATE TABLE Estado(est_id INTEGER,est_titulo TEXT DEFAULT 'Sin Titulo',est_rutina TEXT DEFAULT 'false',est_entrada text DEFAULT 'false' )";
		
	/*
	 * est_rutina  true:hay una rutina activa ;false:no hay rutina activa
	 * est_entrada true:se entro desde mis rutinas;false:se entro desde rutinas guiadas 
	 */
	
	
	public BodyDB(Context context, String name, CursorFactory factory,int version) {
		super(context, name, factory, version);	
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("DROP TABLE IF EXISTS Entrenamientos");
		db.execSQL("DROP TABLE IF EXISTS Ejercicios");
		db.execSQL("DROP TABLE IF EXISTS Imagenentre");
		db.execSQL("DROP TABLE IF EXISTS Imagenejer");
		db.execSQL("DROP TABLE IF EXISTS Relacion");
		db.execSQL("DROP TABLE IF EXISTS Rutina");
		db.execSQL("DROP TABLE IF EXISTS Historial");
		db.execSQL("DROP TABLE IF EXISTS Estado");
		
		db.execSQL(sqlCreaEntre);
		db.execSQL(sqlCreaEje);
		db.execSQL(sqlCreaImageentre);
		db.execSQL(sqlCreaImageeje);
		db.execSQL(sqlCreaRelacion);
		db.execSQL(sqlCreaRutina);
		db.execSQL(sqlHistorial);
		db.execSQL(sqlEstado);
		LoadData(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 
	
		db.execSQL("DROP TABLE IF EXISTS Entrenamientos");
		db.execSQL("DROP TABLE IF EXISTS Ejercicios");
		db.execSQL("DROP TABLE IF EXISTS Imagenentre");
		db.execSQL("DROP TABLE IF EXISTS Imagenejer");
		db.execSQL("DROP TABLE IF EXISTS Relacion");
		db.execSQL("DROP TABLE IF EXISTS Rutina");
		db.execSQL("DROP TABLE IF EXISTS Historial");
		db.execSQL("DROP TABLE IF EXISTS Estado");
		db.execSQL("DROP TABLE IF EXISTS Imagenes");
		
		
		db.execSQL(sqlCreaEntre);
		db.execSQL(sqlCreaEje);
		db.execSQL(sqlCreaImageentre);
		db.execSQL(sqlCreaImageeje);
		db.execSQL(sqlCreaRelacion);
		db.execSQL(sqlCreaRutina);
		db.execSQL(sqlHistorial);
		db.execSQL(sqlEstado);
		
		LoadData(db);
	}
	
	
	public void LoadData(SQLiteDatabase db){
		Integer[] Images = new Integer[]{2130837511,2130837528,2130837508,2130837532,2130837509,2130837523,2130837524,2130837504,2130837505,2130837532,2130837525,2130837535,2130837533,2130837538,2130837537};	
		
		//carga de imagenes 
		for(int i=0;i<Images.length;i++){
			db.execSQL("INSERT INTO Imagenejer(imagename) VALUES("+Images[i]+");");
		}
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
