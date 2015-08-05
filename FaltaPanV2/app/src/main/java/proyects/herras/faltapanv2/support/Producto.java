package proyects.herras.faltapanv2.support;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Herras on 24/07/2015.
 */
public class Producto {
    private String title; //Define nombre de la lista.
    private String fechaCreacion; //Fecha de creacion.
    private String fechaModificacion; // Fecha ultima modificacion.
    private float price; // precio unitario.
    private float cuantity; //numero de productos
    private String cuantityUnit; //unidad de medida
    private String status; // Pendiente"P", Comprado"C",Subrayado"S",Descartado"D",Agotado"A" ;este dato debe definirse a nivel de relacion Producto/Lista
    private String brand; // marca de producto.
    private String family; // familia a la que pertenece el producto.
    private int listId;
    private int productId;
    //private String tienda; // Tienda en la que debe realizarse la compra.
    //private int imagen; //R.id de la imagen que se quiere insertar.

    public Producto(String nombre) {
        this.title = nombre;
        this.fechaCreacion = new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    public Producto(String nombre, String fechaCreacion, String fechaModificacion,String status, int price,String marca, int cuantity,String cuantityUnit,int listId,int productId,String familia) {
        this.title = nombre;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.status = status;
        this.price = price;
        this.brand = marca;
        this.cuantity = cuantity;
        this.cuantityUnit = cuantityUnit;
        this.listId = listId;
        this.productId = productId;
        this.family = familia;
       // this.tienda = tienda;
        //this.imagen = imagen;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getCuantity() {
        return cuantity;
    }

    public void setCuantity(int cuantity) {
        this.cuantity = cuantity;
    }

    public String getCuantityUnit() {
        return cuantityUnit;
    }

    public void setCuantityUnit(String cuantityUnit) {
        this.cuantityUnit = cuantityUnit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public int getListId() {return listId;}

    public void setListId(int listId) {
        this.listId = listId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productID) {
        this.productId = productId;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCuantity(float cuantity) {
        this.cuantity = cuantity;
    }
}
