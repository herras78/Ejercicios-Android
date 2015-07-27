package proyects.herras.faltapanv2.activities;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import proyects.herras.faltapanv2.R;
import proyects.herras.faltapanv2.bbdd.DBAcces;
import proyects.herras.faltapanv2.bbdd.DBDataLoader;
import proyects.herras.faltapanv2.bbdd.DBStructureBuilder;
import proyects.herras.faltapanv2.contractor.ContractorTableValues;
import proyects.herras.faltapanv2.sharedpreferences.SPApp;


public class MainScreen extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView txtTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        deployDB();

        bindControls();
        prepareControls();


        Cursor c = new DBAcces(this).getCursor("SELECT * FROM " + ContractorTableValues.TablaLista.TABLE_NAME);
        c.moveToFirst();
        txtTest.setText(c.getString(1));
    }

    public void bindControls(){
        toolbar = (Toolbar)findViewById(R.id.mainscreenbar);
        txtTest = (TextView)findViewById(R.id.text_test);
    }
    public void prepareControls(){
        setSupportActionBar(toolbar);
    }

    public void deployDB(){
        if(new SPApp(this.getApplicationContext()).isFirst()){
            //Log.d("FaltaPan", "MainScreen,Comprobando isFirst");
            new DBStructureBuilder(this);
            new DBDataLoader(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
