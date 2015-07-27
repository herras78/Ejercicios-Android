package proyects.herras.faltapanv2.support;

import android.widget.TabHost;

/**
 * Created by Herras on 27/07/2015.
 */
public class Tienda {
    private String nombre; //Define nombre de la tienda
    private int imagen; //R.id de la imagen que se quiere insertar.

    public Tienda(String nombre,int imagen){
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
