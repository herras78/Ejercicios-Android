package com.example.herras.ejercicio3;

/**
 * Created by Herras on 04/07/2015.
 */
public class Titular {
    private String titulo;
    private String subtitulo;

    public Titular(String tit, String sub){
        this.titulo = tit;
        this.subtitulo = sub;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }
}
