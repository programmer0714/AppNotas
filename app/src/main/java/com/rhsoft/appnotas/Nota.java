package com.rhsoft.appnotas;

public class Nota {

    private String titulo;
    private String contenido;
    private String fecha;

    public Nota(String titulo, String contenido, String fecha) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha;
    }

    public String getTitulo() { return titulo; }
    public String getContenido() { return contenido; }
    public String getFecha() { return fecha; }

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setContenido(String contenido) { this.contenido = contenido; }
    public void setFecha(String fecha) { this.fecha = fecha; }
}