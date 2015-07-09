package com.example.herras.ejercicio4;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.herras.ejercicio4.Interfaces.OnLoginListener;
import com.example.herras.ejercicio4.customControls.ControlLogin;

public class MainActivity extends Activity {
    private ControlLogin ctrLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asociaControles();
        preparaControles();

    }

    public void asociaControles(){
        ctrLogin = (ControlLogin)findViewById(R.id.CtlLogin);
    }

    public void preparaControles(){
        ctrLogin.setOnLoginListener(new OnLoginListener() {
            @Override
            public void onLogin(String usuario, String password) {
                if(usuario.equals("demo") && password.equals("demo")){
                    ctrLogin.setMensaje("Login Correcto.");
                }else{
                    ctrLogin.setMensaje("Datos Incorrectos.");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
