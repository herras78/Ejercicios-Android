package com.example.herras.ejercicio4.customControls;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.herras.ejercicio4.Interfaces.OnLoginListener;
import com.example.herras.ejercicio4.R;

/**
 * Created by Herras on 09/07/2015.
 */
public class ControlLogin extends LinearLayout {
    private EditText txtUsuario;
    private EditText txtPassword;
    private Button btnLogin;
    private TextView lblMensaje;
    private OnLoginListener listener;

    public ControlLogin(Context context){
        super(context);
        inicializar();
    }

    public ControlLogin(Context context,AttributeSet attrs){
        super(context,attrs);
        inicializar();

        TypedArray a = getContext().obtainStyledAttributes(attrs,R.styleable.ControlLogin);

        String textoBoton = a.getString(R.styleable.ControlLogin_login_text);

        btnLogin.setText(textoBoton);
        a.recycle();
    }

    private void inicializar() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        li.inflate(R.layout.control_login, this, true);

        txtUsuario = (EditText) findViewById(R.id.TxtUsuario);
        txtPassword = (EditText) findViewById(R.id.TxtPassword);
        btnLogin = (Button) findViewById(R.id.BtnAceptar);
        lblMensaje = (TextView) findViewById(R.id.LblMensaje);

        asignarEventos();
    }

    private void asignarEventos(){
        btnLogin.setOnClickListener(new OnClickListener(){
           public void onClick(View v){
               listener.onLogin(txtUsuario.getText().toString(),txtPassword.getText().toString());
           }
        });
    }

    public void setOnLoginListener(OnLoginListener l){
        listener = l;
    }

    public void setMensaje(String msg){
        lblMensaje.setText(msg);
    }
}
