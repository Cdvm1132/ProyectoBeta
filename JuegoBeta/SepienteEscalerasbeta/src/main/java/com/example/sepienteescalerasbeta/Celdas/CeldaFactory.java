package com.example.sepienteescalerasbeta.Celdas;

public class CeldaFactory {
    /**
     * Funcion para crear clas casillas del tablero
     * @param tipo: Indica el tipo de casilla que se generara
     * @param x: Es la cooedenada en la fila de la matriz
     * @param y: Es la coordenada en la columna de la matriz
     * @return La funcion retorna el tipo de casilla que se esta creando
     */
    public static Celda crearCelda(String tipo, int x, int y) {
        switch (tipo) {
            case "Normal":
                return new com.example.sepienteescalerasbeta.Celdas.CeldaNormal(x, y);
            case "CabezaSerpiente":
                return new CeldaCabezaSerpiente(x, y);
            case "ColaSerpiente":
                return new CeldaColaSerpiente(x, y);
            case "InicioEscalera":
                return new com.example.sepienteescalerasbeta.Celdas.CeldaInicioEscalera(x, y);
            case "FinalEscalera":
                return new com.example.sepienteescalerasbeta.Celdas.CeldaFinalEscalera(x, y);
            case "Inicio":
                return new com.example.sepienteescalerasbeta.Celdas.CeldaInicio(x, y);
            case "Final":
                return new com.example.sepienteescalerasbeta.Celdas.CeldaFinal(x, y);
        }
        return null;
    }
}