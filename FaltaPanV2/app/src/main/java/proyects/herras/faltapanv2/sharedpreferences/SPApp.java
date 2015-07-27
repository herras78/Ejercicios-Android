package proyects.herras.faltapanv2.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Herras on 25/07/2015.
 */
public class SPApp  {
    private SharedPreferences appPref ;
    private SharedPreferences.Editor editor;

    public SPApp(Context context){
        this.appPref = context.getSharedPreferences("AppPreferences", context.MODE_PRIVATE);
        this.editor = appPref.edit();
    }

    public boolean isFirst(){
        if(appPref.getBoolean("IsFirst",true)){
            editor.putBoolean("IsFirst",false);
            editor.commit();
            return true;
        }else {
            return false;
        }
    }
}
