package proyects.herras.faltapanv2.support;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Herras on 24/07/2015.
 */
public class Producto {
    private String nombre; //Define nombre de la lista.
    private String fechaCreacion; //Fecha de creacion.
    private String fechaModificacion; // Fecha ultima modificacion.
    private int precio; // precio unitario.
    //private int numElementos; este dato debe definirse a nivel de relacion Producto/Lista
    //private String estado; // Pendiente"P", Comprado"C",Subrayado"S",Descartado"D",Agotado"A" ;este dato debe definirse a nivel de relacion Producto/Lista
    private String marca; // marca de producto.
    private int volumen; // volumen del producto en Litros.
    private int peso; // peso del producto en Kg.
    private String familia; // familia a la que pertenece el producto.
    private String tienda; // Tienda en la que debe realizarse la compra.
    private int imagen; //R.id de la imagen que se quiere insertar.

    public Producto(String nombre) {
        this.nombre = nombre;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        this.fechaCreacion = sdf.format(new Date());
    }

    public Producto(String nombre, String fechaCreacion, String fechaModificacion, int precio, String marca, int volumen, int peso,String familia, String tienda, int imagen) {
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.precio = precio;
        this.marca = marca;
        this.volumen = volumen;
        this.peso = peso;
        this.familia = familia;
        this.tienda = tienda;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
