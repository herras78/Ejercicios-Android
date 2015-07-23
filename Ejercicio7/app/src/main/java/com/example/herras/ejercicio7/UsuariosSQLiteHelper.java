package com.example.herras.ejercicio7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Herras on 13/07/2015.
 */
public class UsuariosSQLiteHelper extends SQLiteOpenHelper{

    String sqlCreate = "CREATE TABLE Usuarios (codigo INTEGER, nombre TEXT)";

    public UsuariosSQLiteHelper(Context context,String nombre,SQLiteDatabase.CursorFactory factory, int version){
        super(context,nombre,factory,version);
    }
    public void onCreate(SQLiteDatabase db){
        //db.execSQL();
        db.execSQL(sqlCreate);
    }

    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva){
        db.execSQL("DROP TABLE IF EXIST Usuarios");
        db.execSQL(sqlCreate);
    }
}
