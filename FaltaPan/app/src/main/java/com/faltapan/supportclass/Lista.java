package com.faltapan.supportclass;

public class Lista {
	
	private String nombre;
   	private String fecha;
    private String elementos;
    private String tienda;
    private int imagen;
 
    public Lista(String name, String date,String elements, String shop ,int img){
    	nombre = name;
    	fecha = date;
    	elementos = elements;
    	tienda = shop;
    	imagen = img;
	
    }
    
    public int getImagen() {
		return imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public String getFecha() {
		return fecha;
	}

	public String getElementos() {
		return elementos;
	}

	public String getTienda() {
		return tienda;
	}
 

}
