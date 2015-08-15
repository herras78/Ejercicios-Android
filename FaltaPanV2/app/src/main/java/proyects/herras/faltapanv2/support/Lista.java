package proyects.herras.faltapanv2.support;

/**
 * Created by Herras on 24/07/2015.
 */
public class Lista {
    private String nombre; //Define nombre de la lista
    private String fechaCreacion; //Fecha de creacion
    private String fechaModificacion; // Fecha ultima modificacion
    private String fechaEjecucion; // Fecha para realizar la compra.
    private int numElementos; // numero de elementos que contiene la lista.
    private int numElementosComprados; // numero de elementos que contiene la lista.
    private String tienda; // Tienda en la que debe realizarse la compra.
    private String estado; // En curso "R", Terminada "T" , Pendiente "P" , Inactiva "I" , Eliminada "E"
    private int porcentajeCompletado; //En caso de que la compra no este completada, indica el porcentaje completado.
    private int imagen; //R.id de la imagen que se quiere insertar.
    private int listId;

    public Lista(String nombre,String fechaCreacion,String fechaModificacion,String fechaEjecucion,int numElementos,int numElementosComprados,String tienda, String estado,int porcentajeCompletado,int imagen,int listId){
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.fechaEjecucion = fechaEjecucion;
        this.numElementos = numElementos;
        this.numElementosComprados = numElementosComprados;
        this.tienda = tienda;
        this.estado = estado;
        this.porcentajeCompletado = porcentajeCompletado;//Este dato se precalcuara en el adaptador.
        this.imagen = imagen;
        this.listId= listId;

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

    public String getFechaEjecucion() {
        return fechaEjecucion;
    }

    public void setFechaEjecucion(String fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

    public int getNumElementos() {
        return numElementos;
    }

    public void setNumElementos(int numElementos) {
        this.numElementos = numElementos;
    }

    public int getNumElementosComprados() {
        return numElementosComprados;
    }

    public void setNumElementosComprados(int numElementosComprados) {
        this.numElementosComprados = numElementosComprados;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPorcentajeCompletado() {
        return porcentajeCompletado;
    }

    public void setPorcentajeCompletado(int porcentajeCompletado) {
        this.porcentajeCompletado = porcentajeCompletado;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }
}
