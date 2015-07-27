package proyects.herras.faltapanv2.support;

/**
 * Created by Herras on 27/07/2015.
 */
public class Familia{
    private String nombre; //Define nombre de la familia
    private int imagen; //R.id de la imagen asociada a la familia.

    public Familia(String nombre,int imgen){
        this.nombre = nombre;
        this.imagen = imgen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
