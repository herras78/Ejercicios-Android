package com.example.herras.ejercicio2;

/**
 * Created by Herras on 04/07/2015.
 */
public class Titular {
    private String titulo;
    private String subtitulo;

    public Titular(String tit,String sub){
        titulo = tit;
        subtitulo = sub;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }
}
