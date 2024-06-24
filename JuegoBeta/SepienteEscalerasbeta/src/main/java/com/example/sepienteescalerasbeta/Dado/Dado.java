package com.example.sepienteescalerasbeta.Dado;

import java.util.Random;

public class Dado {
    private int max = 7;
    private int min = 1;

    /**
     * La funcion tirar sirve para generar un numero al azar entre 1 y 6
     * @return retorna el numero que ha salido del random
     */
    public int tirar(){
        Random r = new Random();
        int total = r.nextInt(max - min) + min;

        return total;
    }
}
