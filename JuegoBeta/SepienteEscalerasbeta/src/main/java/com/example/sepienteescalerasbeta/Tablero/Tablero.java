package com.example.sepienteescalerasbeta.Tablero;

import com.example.sepienteescalerasbeta.Celdas.Celda;
import com.example.sepienteescalerasbeta.Celdas.CeldaFactory;
import com.example.sepienteescalerasbeta.Dado.Dado;

public class Tablero {
    private int filas;
    private int columnas;
    private Celda[][] casillas;
    Dado dado = new Dado();


    public Tablero() {
    }

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.casillas = new Celda[filas][columnas];
        inicializarCasillas();
    }

    /**
     * La funcion es principalmente para crear la matriz del juego
     */
    private void inicializarCasillas() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (i == 0 && j == 0) {
                    casillas[i][j] = CeldaFactory.crearCelda("Inicio", i, j);
                } else if (i == filas - 1 && j == columnas - 1) {
                    casillas[i][j] = CeldaFactory.crearCelda("Final", i, j);
                } else {
                    casillas[i][j] = CeldaFactory.crearCelda("Normal", i, j);
                }
            }
        }
    }

    /**
     * La funcion sirve para llamaar a una casilla
     * @param x: coordenada en las fila de la matriz del tablero
     * @param y: coordenada en las columnas de la matriz del tablero
     * @return retorna la casilla que se llamo
     */
    public Celda getCelda(int x, int y) {
        return casillas[x][y];
    }
}
