package proyects.herras.faltapanv2.bbdd;

import android.content.Context;

import proyects.herras.faltapanv2.contractor.ContractorTableValues;

/**
 * Created by Herras on 24/07/2015.
 */
public class DB_Structure_Drop {
    private final String DROP_IF = "DROP TABLE IF EXISTS ";
    private DBAcces dbAcces;

   public DB_Structure_Drop(Context context){
        dbAcces = new DBAcces(context);
        BorrarTabla(new ContractorTableValues().getTablas());
   }

    public void BorrarTabla(String[] tablas)
    {
        for(String dropTable: tablas){
            dbAcces.deleteDate(DROP_IF + dropTable);
        }
    }
}
