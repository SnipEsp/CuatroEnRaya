package org.iesalandalus.programacion.cuatroenraya;

import org.iesalandalus.programacion.cuatroenraya.modelo.*;
import org.iesalandalus.programacion.cuatroenraya.vista.Consola;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Iniciando lectura de Jugador 1...");
            Jugador jugador1 = Consola.leerJugador();

            if (jugador1 == null) {
                System.out.println("Error: El método Consola.leerJugador() ha devuelto un nulo.");
                return;
            }

            System.out.println("Iniciando lectura de Jugador 2...");
            Ficha colorJ2 = (jugador1.colorFichas() == Ficha.AZUL) ? Ficha.VERDE : Ficha.AZUL;
            Jugador jugador2 = Consola.leerJugador(colorJ2);

            if (jugador2 == null) {
                System.out.println("Error: El método Consola.leerJugador(ficha) ha devuelto un nulo.");
                return;
            }

            System.out.println("Creando partida...");
            CuatroEnRaya juego = new CuatroEnRaya(jugador1, jugador2);
            juego.jugar();

        } catch (Exception e) {
            System.out.println("\n--- ¡EL PROGRAMA HA EXPLOTADO! ---");
            System.out.println("Tipo de error: " + e.getClass().getSimpleName());
            System.out.println("Mensaje: " + e.getMessage());
            e.printStackTrace(); // Esto te dirá la línea exacta
        }
    }
}