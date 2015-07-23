package com.example.herras.ejercicio7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Herras on 14/07/2015.
 */
public class DBAcces {
    UsuariosSQLiteHelper usrdb ;
    SQLiteDatabase db;
    static int version = 1;

    public DBAcces(Context context, String dbName){
        usrdb = new UsuariosSQLiteHelper(context,dbName,null,version);
    }

    public void insertData(String tableName,ContentValues values){
        db = usrdb.getWritableDatabase();
        db.insert(tableName,null,values);
        db.close();
    }

    public void updateDate(String tableName,ContentValues values,String condition){
        db = usrdb.getWritableDatabase();
        db.update(tableName, values, condition, null);
        db.close();
    }

    public void deleteDate(String tableName,String condition){
        db = usrdb.getWritableDatabase();
        db.delete(tableName, condition, null);
        db.close();
    }

    public Cursor getCursor(String tableName,String[] campos,String where,String[] args){
        db = usrdb.getReadableDatabase();
        return  db.query(tableName,campos,where,args,null,null,null,null);

    }
    public Cursor getCursor(String tableName,String[] campos){
        db = usrdb.getReadableDatabase();
        return db.query(tableName,campos,null,null,null,null,null,null);
    }
}
