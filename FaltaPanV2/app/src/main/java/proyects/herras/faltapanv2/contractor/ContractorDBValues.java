package proyects.herras.faltapanv2.contractor;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Herras on 24/07/2015.
 */
public class ContractorDBValues {
    public static final String DB_NAME = "DBFALTAPAN";
    public static final int VERSION  = 1;
    public static final SQLiteDatabase.CursorFactory FACTORY = null;

    public static String getDbName(){
        return DB_NAME;
    }

    public static int getVersion(){return VERSION; }

    public static SQLiteDatabase.CursorFactory getFactory(){return FACTORY; }

}
