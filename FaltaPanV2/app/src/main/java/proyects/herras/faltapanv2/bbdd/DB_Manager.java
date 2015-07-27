package proyects.herras.faltapanv2.bbdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import proyects.herras.faltapanv2.contractor.ContractorDBValues;

/**
 * Created by Herras on 24/07/2015.
 */
public class DB_Manager extends SQLiteOpenHelper{
    private Context context;

    static ContractorDBValues contractor = new ContractorDBValues();

    public DB_Manager(Context contexto)
    {
        super(contexto, contractor.getDbName(), contractor.getFactory(), contractor.getVersion());
        context = contexto;
        //Log.d("FaltaPan", "DB_Manager,llamando al contructor");
    }

    public void onCreate(SQLiteDatabase db){
        //Log.d("FaltaPan", "DB_Manager,Ejecutando onCreate");

    }

    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva){
        //Log.d("FaltaPan", "DB_Manager,Ejecutando onUpdate");
        new DB_Structure_Drop(context);
        new DBStructureBuilder(context);
    }
}
