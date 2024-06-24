package com.example.sepienteescalerasbeta;

import com.example.sepienteescalerasbeta.Celdas.Celda;
import com.example.sepienteescalerasbeta.Dado.Dado;
import com.example.sepienteescalerasbeta.Ficha.Ficha;
import com.example.sepienteescalerasbeta.GuardadoDatos.GuardadoDatos;
import com.example.sepienteescalerasbeta.Jugador.Jugador;
import com.example.sepienteescalerasbeta.Partida.Partida;
import com.example.sepienteescalerasbeta.Tablero.Tablero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Esta clase representa la funcionalidad principal del juego.
 */
public class Juego {
    private static Scanner scanner = new Scanner(System.in);
    private static GuardadoDatos guardadoDatos = GuardadoDatos.obtenerInstancia();
    private static List<Jugador> jugadores = new ArrayList<>();
    private static Partida partida;
    private static Tablero tablero;

    /**
     * Metodo principal para iniciar el juego.
     *
     * @param args argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        boolean jugando = true;
        while (jugando) {
            System.out.println("Menu Principal:");
            System.out.println("1. Iniciar Partida");
            System.out.println("2. Ver Puntajes");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    iniciarPartida();
                    break;
                case 2:
                    verPuntajes();
                    break;
                case 3:
                    jugando = false;
                    System.out.println("Saliendo del juego...");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }
    }

    /**
     * Fucnion que inicia una nueva partida.
     */
    private static void iniciarPartida() {
        System.out.print("Ingrese el gametag del jugador 1: ");
        String gametag1 = scanner.nextLine();

        System.out.println("¿Cuantos jugadores? (1 o 2): ");
        int numJugadores = scanner.nextInt();
        scanner.nextLine();

        Jugador jugador1 = obtenerOAgregarJugador(gametag1);

        Jugador jugador2;
        if (numJugadores == 1) {
            jugador2 = obtenerOAgregarJugador("NPC");
        } else {
            System.out.print("Ingrese el gametag del jugador 2: ");
            String gametag2 = scanner.nextLine();
            jugador2 = obtenerOAgregarJugador(gametag2);
        }

        seleccionarFicha(jugador1);
        if (!jugador2.getGametag().equals("NPC")) {
            seleccionarFicha(jugador2);
        } else {
            Ficha fichaNPC = new Ficha();
            fichaNPC.setTipo("Peon");
            fichaNPC.setColor("Rojo");
            System.out.println("NPC ha seleccionado una ficha Peon de color Rojo");
        }

        tablero = new Tablero(5, 5);

        jugar(jugador1, jugador2);
    }

    /**
     * Obtiene un jugador existente o agrega uno nuevo si no existe.
     *
     * @param gametag el gametag del jugador.
     * @return el jugador correspondiente al gametag.
     */
    private static Jugador obtenerOAgregarJugador(String gametag) {
        for (Jugador jugador : jugadores) {
            if (jugador.getGametag().equals(gametag)) {
                return jugador;
            }
        }
        Jugador nuevoJugador = new Jugador();
        nuevoJugador.setGametag(gametag);
        jugadores.add(nuevoJugador);
        return nuevoJugador;
    }

    /**
     * Permite al jugador seleccionar una ficha.
     *
     * @param jugador el jugador que selecciona la ficha.
     */
    private static void seleccionarFicha(Jugador jugador) {
        System.out.println(jugador.getGametag() + ", elige el tipo de ficha (1. Circular, 2. Peon): ");
        int tipoFicha = scanner.nextInt();
        scanner.nextLine();
        String tipo = (tipoFicha == 1) ? "Circular" : "Peon";

        System.out.println("Elige el color de ficha (1. Rojo, 2. Azul, 3. Amarillo): ");
        int colorFicha = scanner.nextInt();
        scanner.nextLine();
        String color;
        switch (colorFicha) {
            case 1: color = "Rojo"; break;
            case 2: color = "Azul"; break;
            case 3: color = "Amarillo"; break;
            default: color = "Rojo"; break;
        }

        Ficha ficha = new Ficha();
        ficha.setTipo(tipo);
        ficha.setColor(color);
        System.out.println(jugador.getGametag() + " ha seleccionado una ficha " + tipo + " de color " + color);
    }

    /**
     * Ejecuta la lógica del juego entre dos jugadores.
     *
     * @param jugador1 el primer jugador.
     * @param jugador2 el segundo jugador.
     */
    private static void jugar(Jugador jugador1, Jugador jugador2) {
        boolean juegoTerminado = false;
        Jugador[] turnoJugadores = {jugador1, jugador2};
        int turno = 0;
        int[][] posiciones = { {0, 0}, {0, 0} };
        int[] puntajes = {0, 0};
        Dado dado = new Dado();

        int[][] serpientes = {{3, 2, 1, 0}, {4, 3, 2, 1}};
        int[][] escaleras = {{0, 1, 2, 3}, {1, 2, 3, 4}};

        while (!juegoTerminado) {
            Jugador jugadorActual = turnoJugadores[turno % 2];
            int[] posicionActual = posiciones[turno % 2];

            if (!jugadorActual.getGametag().equals("NPC")) {
                System.out.println("Turno de " + jugadorActual.getGametag() + ":");
                System.out.println("1. Tirar dado");
                System.out.println("2. Salir de la partida");
                System.out.print("Seleccione una opcion: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();

                if (opcion == 2) {
                    System.out.println(jugadorActual.getGametag() + " ha salido de la partida.");
                    juegoTerminado = true;
                    break;
                }

                if (opcion != 1) {
                    System.out.println("Opcion no valida, pierde el turno.");
                    turno++;
                    continue;
                }
            }

            System.out.println(jugadorActual.getGametag() + " lanza el dado...");
            int resultadoDado = dado.tirar();
            System.out.println("Salio el numero " + resultadoDado);

            posicionActual[1] += resultadoDado;
            while (posicionActual[1] >= 5) {
                posicionActual[0] += 1;
                posicionActual[1] -= 5;
            }

            if (posicionActual[0] >= 5) {
                posicionActual[0] = 4;
                posicionActual[1] = 4;
            }

            Celda celdaActual = tablero.getCelda(posicionActual[0], posicionActual[1]);
            System.out.println("Se movio a la posicion (" + (posicionActual[0] + 1) + ", " + (posicionActual[1] + 1) + ")");

            if (esCabezaSerpiente(posicionActual, serpientes)) {
                int[] colaPosicion = obtenerColaSerpiente(posicionActual, serpientes);
                System.out.println("¡Oh no! " + jugadorActual.getGametag() + " cayo en una serpiente. Retrocede a (" + (colaPosicion[0] + 1) + ", " + (colaPosicion[1] + 1) + ")");
                posicionActual[0] = colaPosicion[0];
                posicionActual[1] = colaPosicion[1];
                puntajes[turno % 2] -= 100;
            } else if (esInicioEscalera(posicionActual, escaleras)) {
                int[] finalEscaleraPosicion = obtenerFinalEscalera(posicionActual, escaleras);
                System.out.println("¡Genial! " + jugadorActual.getGametag() + " subio por una escalera. Avanza a (" + (finalEscaleraPosicion[0] + 1) + ", " + (finalEscaleraPosicion[1] + 1) + ")");
                posicionActual[0] = finalEscaleraPosicion[0];
                posicionActual[1] = finalEscaleraPosicion[1];
                puntajes[turno % 2] += 200;
            } else {
                puntajes[turno % 2] += 50;
            }

            if (posicionActual[0] == 4 && posicionActual[1] == 4) {
                System.out.println("¡Felicidades! " + jugadorActual.getGametag() + " ha ganado el juego.");
                jugadorActual.agregarVictorias();
                turnoJugadores[(turno + 1) % 2].agregarDerrotas();
                juegoTerminado = true;
                break;
            }

            turno++;
        }

        jugador1.setPuntaje(jugador1.getPuntaje() + puntajes[0]);
        jugador2.setPuntaje(jugador2.getPuntaje() + puntajes[1]);
    }

    /**
     * Verifica si la posicion actual es la cabeza de una serpiente.
     *
     * @param posicion la posicion actual.
     * @param serpientes la matriz de serpientes.
     * @return true si es la cabeza de una serpiente, false en caso contrario.
     */
    private static boolean esCabezaSerpiente(int[] posicion, int[][] serpientes) {
        for (int[] serpiente : serpientes) {
            if (posicion[0] == serpiente[0] && posicion[1] == serpiente[1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtiene la posicion de la cola de la serpiente.
     *
     * @param posicion la posicion actual.
     * @param serpientes la matriz de serpientes.
     * @return la posicion de la cola de la serpiente.
     */
    private static int[] obtenerColaSerpiente(int[] posicion, int[][] serpientes) {
        for (int[] serpiente : serpientes) {
            if (posicion[0] == serpiente[0] && posicion[1] == serpiente[1]) {
                return new int[] {serpiente[2], serpiente[3]};
            }
        }
        return posicion;
    }

    /**
     * Verifica si la posicion actual es el inicio de una escalera.
     *
     * @param posicion la posicion actual.
     * @param escaleras la matriz de escaleras.
     * @return true si es el inicio de una escalera, false en caso contrario.
     */
    private static boolean esInicioEscalera(int[] posicion, int[][] escaleras) {
        for (int[] escalera : escaleras) {
            if (posicion[0] == escalera[0] && posicion[1] == escalera[1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtiene la posicion final de la escalera.
     *
     * @param posicion la posicion actual.
     * @param escaleras la matriz de escaleras.
     * @return la posicion final de la escalera.
     */
    private static int[] obtenerFinalEscalera(int[] posicion, int[][] escaleras) {
        for (int[] escalera : escaleras) {
            if (posicion[0] == escalera[0] && posicion[1] == escalera[1]) {
                return new int[] {escalera[2], escalera[3]};
            }
        }
        return posicion;
    }

    /**
     * Muestra los puntajes, victorias y derrotas de los jugadores.
     */
    private static void verPuntajes() {
        Collections.sort(jugadores, new Comparator<Jugador>() {
            @Override
            public int compare(Jugador j1, Jugador j2) {
                return Integer.compare(j2.getPuntaje(), j1.getPuntaje());
            }
        });

        for (Jugador jugador : jugadores) {
            jugador.mostrarPuntajes();
        }
    }
}
