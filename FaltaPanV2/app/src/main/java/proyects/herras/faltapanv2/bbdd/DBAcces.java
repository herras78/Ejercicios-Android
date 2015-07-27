package proyects.herras.faltapanv2.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Herras on 24/07/2015.
 */
public class DBAcces {
    DB_Manager dbManager;
    SQLiteDatabase db;

    public DBAcces(Context contexto){
        dbManager = new DB_Manager(contexto);
    }

    public void insertData(String tableName,ContentValues values){
        db = dbManager.getWritableDatabase();
        db.insert(tableName,null,values);
        db.close();
    }

    public void insertData(String query){
        db = dbManager.getWritableDatabase();
        Log.d("FaltaPan", "DBAcces,Ejecutando :" + query);
        db.execSQL(query);
        db.close();
    }

    public void updateDate(String tableName,ContentValues values,String condition){
        db = dbManager.getWritableDatabase();
        db.update(tableName, values, condition, null);
        db.close();
    }

    public void updateDate(String query){
        db = dbManager.getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    public void deleteDate(String tableName,String condition){
        db = dbManager.getWritableDatabase();
        db.delete(tableName, condition, null);
        db.close();
    }

    public void deleteDate(String query){
        db = dbManager.getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    public Cursor getCursor(String tableName,String[] campos,String where,String[] args){
        db = dbManager.getReadableDatabase();
        return  db.query(tableName,campos,where,args,null,null,null,null);
    }

    public Cursor getCursor(String tableName,String[] campos){
        db = dbManager.getReadableDatabase();
        return db.query(tableName,campos,null,null,null,null,null,null);
    }

    public Cursor getCursor(String query){
        db = dbManager.getReadableDatabase();
        return db.rawQuery(query,null);
    }




}
