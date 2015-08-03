package proyects.herras.faltapanv2.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Herras on 25/07/2015.
 */
public class SPApp  {
    private SharedPreferences appPref ;
    private SharedPreferences.Editor editor;

    private static String LIST_NAME = "LIST_NAME";
    private static String IMG_REF = "IMG_REF";
    private static String LIST_ID = "LIST_ID";

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

    public void setSelectedList(String listName,int imgRef, int listID){
        editor.putString(LIST_NAME,listName);
        editor.putInt(IMG_REF, imgRef);
        editor.putInt(LIST_ID, listID);
        editor.commit();
    }

    public String getListName(){
        return appPref.getString(LIST_NAME,"");
    }
    public int getListImgRef(){
        return appPref.getInt(IMG_REF, 0);
    }
    public int getListID(){ return appPref.getInt(LIST_ID, 0); }
}
