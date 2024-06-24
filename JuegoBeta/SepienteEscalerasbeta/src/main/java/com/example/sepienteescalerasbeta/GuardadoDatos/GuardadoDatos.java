package com.example.sepienteescalerasbeta.GuardadoDatos;

import java.util.HashMap;
import java.util.Map;

public class GuardadoDatos {
    private static GuardadoDatos instancia;
    private Map<String, Object> datosGuardados;

    private GuardadoDatos() {
        datosGuardados = new HashMap<>();
    }

    /**
     * La funcion sirve para inicializar una instancia si esta no se ha creado
     * @return retorna una instancia para el guarddado de datos
     */
    public static GuardadoDatos obtenerInstancia() {
        if (instancia == null) {
            instancia = new GuardadoDatos();
        }
        return instancia;
    }

    /**
     * Funcion para almacenar datos de una partida
     * @param clave: es el identificador con el que se guarda la partida
     * @param dato: son todos los datos de la partida
     */
    public void guardarDato(String clave, Object dato) {
        datosGuardados.put(clave, dato);
    }

    /**
     * Funcion para obtener los datos de una partida
     * @param clave: identificador de la partida
     * @return Retorna los datos que se han almacenado en dicha clave
     */
    public Object obtenerDato(String clave) {
        return datosGuardados.get(clave);
    }

}



