package com.example.sepienteescalerasbeta.Ficha;

public class Ficha implements com.example.sepienteescalerasbeta.Ficha.FichaPrototype {
    private String color;
    private String tipo;

    public Ficha() {}

    /**
     * Funcion para indicar el color y el tipo de ficha
     * @param target
     */
    public Ficha(Ficha target) {
        if (target != null) {
            this.color = target.color;
            this.tipo = target.tipo;
        }
    }

    /**
     * La funcion clone implementada de la interfaz FichaPrototype es utilizada para crear una nueva ficha sin necesidad de crear mas lineas de codigo
     * @return retorna una nueva ficha
     */
    @Override
    public Ficha clone() {
        return new Ficha(this);
    }

    /**
     * La funcion es principalmente para indicar el olor de la ficha
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * La funcion es para crear una ficha con el parametro que se le indique
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    @Override
    public String toString() {
        return "Ficha{color='" + color + "', tipo='" + tipo + "'}";
    }
}
