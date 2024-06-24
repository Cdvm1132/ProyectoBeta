package com.example.sepienteescalerasbeta.Jugador;

public class Jugador implements com.example.sepienteescalerasbeta.Jugador.Puntaje {
    protected String gametag;
    private int victorias;
    private int derrotas;
    private int puntaje;

    public Jugador() {
    }


    public void setGametag(String gametag) {
        this.gametag = gametag;
    }


    public String getGametag() {
        return gametag;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }


    /**
     * Esta funcion sirve para agregar una victoria al jugador
     */
    @Override
    public void agregarVictorias() {
        this.victorias++;
    }

    /**
     * Esta funcion sirve para agregar derrotas al jugador
     */
    @Override
    public void agregarDerrotas() {
        this.derrotas++;
    }

    /**
     * Esta funcion sirve para reiniciar las derrotas y victorias al jugador
     */
    @Override
    public void Reiniciar() {
        this.victorias = 0;
        this.derrotas = 0;
    }

    /**
     * Funcion para mostrar los datos de cada jugador
     */
    @Override
    public void mostrarPuntajes() {
        System.out.println("Gametag: " + this.gametag + ", Victorias: " + this.victorias + ", Derrotas: " + this.derrotas + ", Puntaje: " + this.puntaje);
    }
}
