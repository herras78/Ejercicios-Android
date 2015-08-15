package proyects.herras.faltapanv2.contractor;

import android.provider.BaseColumns;
import android.util.Log;

import java.util.LinkedHashMap;

import proyects.herras.faltapanv2.R;

/**
 * Created by Herras on 24/07/2015.
 */
public class ContractorTableValues {

    private String[] tablas = new String[]{
            TablaLista.TABLE_NAME,
            TablaProducto.TABLE_NAME,
            TablaListaProducto.TABLE_NAME,
            TablaFamilia.TABLE_NAME,
            TablaTienda.TABLE_NAME
    };

    public String[] getTablas() {
        return tablas;
    }

    public static class TablaLista implements BaseColumns
    {
        public static final String TABLE_NAME = "T_LISTA";

        public static final String NOMBRE = "NOMBRE";
        public static final String FECHA_CREACION = "FECHA_CREACION";
        public static final String FECHA_MODIFICACION = "FECHA_MODIFICACION";
        public static final String FECHA_EJECUCION = "FECHA_EJECUCION";
        public static final String NUM_ELEMENTOS = "NUM_ELEMENTOS";//Numero de productos en la lista.
        public static final String NUM_ELEMENTOS_COMPRADOS = "NUM_ELEMENTOS_COMPRADOS"; //Numero de productos comprados en la lista.
        public static final String NOMBRE_TIENDA = "NOMBRE_TIENDA";
        public static final String ESTADO = "ESTADO";
        public static final String PORCENTAJE_COMPLETADO = "PORCENTAJE_COMPLETADO";
        public static final String REF_IMAGEN = "REF_IMAGEN";

        public static final String PARAM_ID = "INTEGER PRIMARY KEY AUTOINCREMENT";
        public static final String PARAM_NOMBRE = "TEXT NOT NULL";
        public static final String PARAM_FECHA_CREACION = "TEXT NOT NULL";
        public static final String PARAM_FECHA_MODIFICACION = "TEXT";
        public static final String PARAM_FECHA_EJECUCION = "TEXT";
        public static final String PARAM_NUM_ELEMENTOS = "INTEGER";
        public static final String PARAM_NUM_ELEMENTOS_COMPRADOS = "INTEGER";
        public static final String PARAM_NOMBRE_TIENDA = "TEXT";
        public static final String PARAM_ESTADO = "TEXT";
        public static final String PARAM_PORCENTAJE_COMPLETADO = "INTEGER";//Precarlcular en el adaptador
        public static final String PARAM_REF_IMAGEN = "INTEGER";

        public static final String CONSTRAINT_FK_LISTA_TIENDA = "CONSTRAINT FK_LISTA_TIENDA FOREIGN KEY("+NOMBRE_TIENDA+") REFERENCES T_LISTA("+ TablaTienda._ID +")";

        public LinkedHashMap<String, String> setEstructura(){
            LinkedHashMap<String, String> hm_estructura = new LinkedHashMap<String, String>();

            hm_estructura.put("TABLE_NAME", TABLE_NAME);
            hm_estructura.put("ID", TablaLista._ID +  " "  + PARAM_ID);
            hm_estructura.put("NOMBRE", NOMBRE+  " "  +PARAM_NOMBRE);
            hm_estructura.put("FECHA_CREACION", FECHA_CREACION +  " "  + PARAM_FECHA_CREACION);
            hm_estructura.put("FECHA_MODIFICACION", FECHA_MODIFICACION +  " "  + PARAM_FECHA_MODIFICACION);
            hm_estructura.put("FECHA_EJECUCION", FECHA_EJECUCION +  " "  + PARAM_FECHA_EJECUCION);
            hm_estructura.put("NUM_ELEMENTOS", NUM_ELEMENTOS +  " " + PARAM_NUM_ELEMENTOS);
            hm_estructura.put("NUM_ELEMENTOS_COMPRADOS", NUM_ELEMENTOS_COMPRADOS +  " " + PARAM_NUM_ELEMENTOS_COMPRADOS);
            hm_estructura.put("NOMBRE_TIENDA", NOMBRE_TIENDA + " " + PARAM_NOMBRE_TIENDA);
            hm_estructura.put("ESTADO", ESTADO + " " + PARAM_ESTADO);
            hm_estructura.put("PORCENTAJE_COMPLETADO", PORCENTAJE_COMPLETADO + " " + PARAM_PORCENTAJE_COMPLETADO);
            hm_estructura.put("REF_IMAGEN", REF_IMAGEN + " " + PARAM_REF_IMAGEN);
            hm_estructura.put("CONSTRAINT_FK_LISTA_TIENDA", CONSTRAINT_FK_LISTA_TIENDA);
            //Log.d("FaltaPan", "DBManager,Devolviendo estructura de Tabla Lista");
            return hm_estructura;
        }

       public static String[] getCabeceras()
        {
            String[] cabeceras = new String[]{
                    NOMBRE,
                    FECHA_CREACION,
                    FECHA_MODIFICACION,
                    FECHA_EJECUCION,
                    NUM_ELEMENTOS,
                    NUM_ELEMENTOS_COMPRADOS,
                    NOMBRE_TIENDA,
                    ESTADO,
                    PORCENTAJE_COMPLETADO,
                    REF_IMAGEN,
                    _ID
            };

            return cabeceras;

        }

        public static String getQueryListToProductFields(int listId){
            return "SELECT "+ NOMBRE +","+ REF_IMAGEN +","+ _ID  +" FROM "+ TABLE_NAME +" WHERE "+ _ID +"='"+ listId +"'";
        }

        public static String getTableName() {
            return TABLE_NAME;
        }

        public static String getNombre() {
            return NOMBRE;
        }

        public static String getFechaCreacion() {
            return FECHA_CREACION;
        }

        public static String getFechaModificacion() {
            return FECHA_MODIFICACION;
        }

        public static String getFechaEjecucion() {
            return FECHA_EJECUCION;
        }

        public static String getNumElementos() {
            return NUM_ELEMENTOS;
        }

        public static String getNombreTienda() {
            return NOMBRE_TIENDA;
        }

        public static String getESTADO() {
            return ESTADO;
        }

        public static String getPorcentajeCompletado() {
            return PORCENTAJE_COMPLETADO;
        }

        public static String getRefImagen() {
            return REF_IMAGEN;
        }
    }

    public static class TablaProducto implements BaseColumns
    {
        public static final String TABLE_NAME = "T_PRODUCTO";

        public static final String NOMBRE = "NOMBRE";
        public static final String FECHA_CREACION = "FECHA_CREACION";
        public static final String FECHA_MODIFICACION = "FECHA_MODIFICACION";//no se usa de momento
        public static final String FAMILIA = "FAMILIA";//Falta agregar en la UI
        public static final String TIENDA = "TIENDA";//No se usa nunca
        public static final String REF_IMAGEN = "REF_IMAGEN";//De momento no se usa

        public static final String PARAM_ID = "INTEGER PRIMARY KEY AUTOINCREMENT";
        public static final String PARAM_NOMBRE = "TEXT NOT NULL";
        public static final String PARAM_FECHA_CREACION = "TEXT";
        public static final String PARAM_FECHA_MODIFICACION = "TEXT";
        public static final String PARAM_FAMILIA = "TEXT";
        public static final String PARAM_TIENDA = "TEXT";
        public static final String PARAM_REF_IMAGEN = "INTEGER";

        public static final String CONSTRAINT_FK_PRODUCTO_FAMILIA = "CONSTRAINT FK_PRODUCTO_FAMILIA FOREIGN KEY("+FAMILIA+") REFERENCES T_LISTA("+ TablaFamilia._ID +")";
        public static final String CONSTRAINT_FK_PRODUCTO_TIENDA = "CONSTRAINT FK_PRODUCTO_TIENDA FOREIGN KEY("+TIENDA+") REFERENCES T_LISTA("+ TablaTienda._ID +")";

        public LinkedHashMap<String, String> setEstructura(){
            LinkedHashMap<String, String> hm_estructura = new LinkedHashMap<String, String>();

            hm_estructura.put("TABLE_NAME", TABLE_NAME);
            hm_estructura.put("ID", TablaProducto._ID + " " + PARAM_ID);
            hm_estructura.put("NOMBRE", NOMBRE + " " + PARAM_NOMBRE);
            hm_estructura.put("FECHA_CREACION", FECHA_CREACION + " " + PARAM_FECHA_CREACION);
            hm_estructura.put("FECHA_MODIFICACION", FECHA_MODIFICACION + " " + PARAM_FECHA_MODIFICACION);
            hm_estructura.put("FAMILIA", FAMILIA + " " + PARAM_FAMILIA);
            hm_estructura.put("TIENDA", TIENDA + " " + PARAM_TIENDA);
            hm_estructura.put("REF_IMAGEN", REF_IMAGEN + " " + PARAM_REF_IMAGEN);
            hm_estructura.put("CONSTRAINT_FK_PRODUCTO_FAMILIA", CONSTRAINT_FK_PRODUCTO_FAMILIA);
            hm_estructura.put("CONSTRAINT_FK_PRODUCTO_TIENDA", CONSTRAINT_FK_PRODUCTO_TIENDA);

           // Log.d("FaltaPan", "DBManager,Devolviendo estructura de Tabla Producto");
            return hm_estructura;
        }

        public static String[] getCabeceras()
        {
            String[] cabeceras = new String[]{
                    NOMBRE,
                    FECHA_CREACION,
                    FECHA_MODIFICACION,
                    FAMILIA,
                    TIENDA,
                    REF_IMAGEN
            };

            return cabeceras;
        }

        public static String getTableName() {
            return TABLE_NAME;
        }

        public static String getNombre() {
            return NOMBRE;
        }

        public static String getFechaCreacion() {
            return FECHA_CREACION;
        }

        public static String getFechaModificacion() {
            return FECHA_MODIFICACION;
        }

        public static String getFamilia() {
            return FAMILIA;
        }

        public static String getTienda() {
            return TIENDA;
        }

        public static String getRefImagen() {
            return REF_IMAGEN;
        }
    }

    public static class TablaListaProducto implements BaseColumns
    {
        public static final String TABLE_NAME = "T_A_LISTA_PRODUCTO";

        public static final String ID_LISTA = "ID_LISTA";
        public static final String ID_PRODUCTO = "ID_PRODUCTO";

        public static final String ESTADO_PRODUCTO = "ESTADO_PRODUCTO"; // Estado del producto Pendiente"P", Comprado"C",Subrayado"S",Descartado"D",Agotado"A"
        public static final String NUMERO_ELEMENTOS = "NUMERO_ELEMENTOS"; //este dato esta en T_Lista
         public static final String UNIDAD_MEDIDA = "UNIDAD_MEDIDA"; //Unidad de medida que usa el producto(kg,gr,l,cl,bolsa,lata,botella,pack)
        public static final String PRECIO = "PRECIO";//Precio del producto en el momento de la compra.
        public static final String MARCA = "MARCA";
        public static final String ORDEN_PRODUCTO = "ORDEN_PRODUCTO";//Indica el orden del producto dentro de la lista.

        public static final String PARAM_ESTADO_PRODUCTO = "TEXT";
        public static final String PARAM_NUMERO_ELEMENTOS = "DECIMAL(6,5)";
        public static final String PARAM_UNIDAD_MEDIDA = "TEXT";
        public static final String PARAM_PRECIO = "DECIMAL(6,5)";
        public static final String PARAM_MARCA = "TEXT";
        public static final String PARAM_ORDEN_PRODUCTO = "INTEGER";

        public static final String CONSTRAINT_PK_ID_LISTA_PRODUCTO = "CONSTRAINT PK_ID_LISTA_PRODUCTO PRIMARY KEY("+ID_LISTA+", "+ID_PRODUCTO+")";
        public static final String CONSTRAINT_FK_LISTAPRODUCTO_LISTA = "CONSTRAINT FK_LISTAPRODUCTO_LISTA FOREIGN KEY("+ID_LISTA+") REFERENCES T_LISTA("+ TablaLista._ID +")";
        public static final String CONSTRAINT_FK_LISTAPRODUCTO_PRODUCTO = "CONSTRAINT FK_LISTAPRODUCTO_PRODUCTO FOREIGN KEY("+ID_PRODUCTO+") REFERENCES T_PRODUCTO("+ TablaProducto._ID +")";

        public LinkedHashMap<String, String> setEstructura()
        {
            LinkedHashMap<String, String> hm_estructura = new LinkedHashMap<String, String>();

            hm_estructura.put("TABLE_NAME", TABLE_NAME);

            hm_estructura.put("ID_LISTA", ID_LISTA );
            hm_estructura.put("ID_PRODUCTO", ID_PRODUCTO);

            hm_estructura.put("ESTADO_PRODUCTO", ESTADO_PRODUCTO + " " + PARAM_ESTADO_PRODUCTO);
            hm_estructura.put("NUMERO_ELEMENTOS", NUMERO_ELEMENTOS + " " + PARAM_NUMERO_ELEMENTOS);
            hm_estructura.put("UNIDAD_MEDIDA", UNIDAD_MEDIDA + " " + PARAM_UNIDAD_MEDIDA);
            hm_estructura.put("PRECIO", PRECIO + " " + PARAM_PRECIO);
            hm_estructura.put("MARCA", MARCA + " " + PARAM_MARCA);
            hm_estructura.put("ORDEN_PRODUCTO", ORDEN_PRODUCTO + " " + PARAM_ORDEN_PRODUCTO);

            hm_estructura.put("CONSTRAINT_PK_ID_LISTA_PRODUCTO", CONSTRAINT_PK_ID_LISTA_PRODUCTO);
            hm_estructura.put("CONSTRAINT_FK_LISTAPRODUCTO_LISTA", CONSTRAINT_FK_LISTAPRODUCTO_LISTA);
            hm_estructura.put("CONSTRAINT_FK_LISTAPRODUCTO_PRODUCTO", CONSTRAINT_FK_LISTAPRODUCTO_PRODUCTO);
            //Log.d("FaltaPan", "DBManager,Devolviendo estructura de Tabla Lista Producto");
            return hm_estructura;
        }

        public static String[] getCabeceras()
        {
            String[] cabeceras = new String[]{
                    ID_LISTA,
                    ID_PRODUCTO,
                    ESTADO_PRODUCTO,
                    NUMERO_ELEMENTOS,
                    UNIDAD_MEDIDA,
                    PRECIO,
                    MARCA,
                    ORDEN_PRODUCTO
            };

            return cabeceras;
        }

        public static String getTableName() {
            return TABLE_NAME;
        }

        public static String getIdLista() {
            return ID_LISTA;
        }

        public static String getIdProducto() {
            return ID_PRODUCTO;
        }

        public static String getEstadoProducto() {
            return ESTADO_PRODUCTO;
        }

        public static String getNumeroElementos() {
            return NUMERO_ELEMENTOS;
        }

        public static String getUnidadMedida() {
            return UNIDAD_MEDIDA;
        }

        public static String getPRECIO() {
            return PRECIO;
        }

        public static String getMARCA() {
            return MARCA;
        }

        public static String getOrdenProducto() {
            return ORDEN_PRODUCTO;
        }
    }

    public static class TablaFamilia implements BaseColumns
    {
        public static final String TABLE_NAME = "T_FAMILIA";

        public static final String NOMBRE = "NOMBRE";
        public static final String FECHA_CREACION = "FECHA_CREACION";
        public static final String FECHA_MODIFICACION = "FECHA_MODIFICACION";
        public static final String REF_IMAGEN = "REF_IMAGEN";

        public static final String PARAM_ID = "INTEGER PRIMARY KEY AUTOINCREMENT";
        public static final String PARAM_NOMBRE = "TEXT NOT NULL";
        public static final String PARAM_FECHA_CREACION = "TEXT";
        public static final String PARAM_FECHA_MODIFICACION = "TEXT";
        public static final String PARAM_REF_IMAGEN = "INTEGER";

        public LinkedHashMap<String, String> setEstructura(){
            LinkedHashMap<String, String> hm_estructura = new LinkedHashMap<String, String>();

            hm_estructura.put("TABLE_NAME", TABLE_NAME);
            hm_estructura.put("ID", TablaProducto._ID + " " + PARAM_ID);
            hm_estructura.put("NOMBRE", NOMBRE + " " + PARAM_NOMBRE);
            hm_estructura.put("FECHA_CREACION", FECHA_CREACION + " " + PARAM_FECHA_CREACION);
            hm_estructura.put("FECHA_MODIFICACION", FECHA_MODIFICACION + " " + PARAM_FECHA_MODIFICACION);
            hm_estructura.put("REF_IMAGEN", REF_IMAGEN + " " + PARAM_REF_IMAGEN);
            // Log.d("FaltaPan", "DBManager,Devolviendo estructura de Tabla Producto");
            return hm_estructura;
        }

        public static String[] getCabeceras()
        {
            String[] cabeceras = new String[]{
                    NOMBRE,
                    FECHA_CREACION,
                    FECHA_MODIFICACION,
                    REF_IMAGEN
            };

            return cabeceras;
        }

        public static String getTableName() {
            return TABLE_NAME;
        }

        public static String getNombre() {
            return NOMBRE;
        }

        public static String getFechaCreacion() {
            return FECHA_CREACION;
        }

        public static String getFechaModificacion() {
            return FECHA_MODIFICACION;
        }

        public static String getRefImagen() {
            return REF_IMAGEN;
        }
    }

    public static class TablaTienda implements BaseColumns
    {
        public static final String TABLE_NAME = "T_TIENDA";

        public static final String NOMBRE = "NOMBRE";
        public static final String FECHA_CREACION = "FECHA_CREACION";
        public static final String FECHA_MODIFICACION = "FECHA_MODIFICACION";
        public static final String REF_IMAGEN = "REF_IMAGEN";

        public static final String PARAM_ID = "INTEGER PRIMARY KEY AUTOINCREMENT";
        public static final String PARAM_NOMBRE = "TEXT NOT NULL";
        public static final String PARAM_FECHA_CREACION = "TEXT";
        public static final String PARAM_FECHA_MODIFICACION = "TEXT";
        public static final String PARAM_REF_IMAGEN = "INTEGER";

        public LinkedHashMap<String, String> setEstructura(){
            LinkedHashMap<String, String> hm_estructura = new LinkedHashMap<String, String>();

            hm_estructura.put("TABLE_NAME", TABLE_NAME);
            hm_estructura.put("ID", TablaProducto._ID + " " + PARAM_ID);
            hm_estructura.put("NOMBRE", NOMBRE + " " + PARAM_NOMBRE);
            hm_estructura.put("FECHA_CREACION", FECHA_CREACION + " " + PARAM_FECHA_CREACION);
            hm_estructura.put("FECHA_MODIFICACION", FECHA_MODIFICACION + " " + PARAM_FECHA_MODIFICACION);
            hm_estructura.put("REF_IMAGEN", REF_IMAGEN + " " + PARAM_REF_IMAGEN);
            //Log.d("FaltaPan", "DBManager,Devolviendo estructura de Tabla Producto");
            return hm_estructura;
        }

        public static String[] getCabeceras()
        {
            String[] cabeceras = new String[]{
                    NOMBRE,
                    FECHA_CREACION,
                    FECHA_MODIFICACION,
                    REF_IMAGEN
            };

            return cabeceras;
        }

        public static String getTableName() {
            return TABLE_NAME;
        }

        public static String getNombre() {
            return NOMBRE;
        }

        public static String getFechaCreacion() {
            return FECHA_CREACION;
        }

        public static String getFechaModificacion() {
            return FECHA_MODIFICACION;
        }

        public static String getRefImagen() {
            return REF_IMAGEN;
        }
    }
}
