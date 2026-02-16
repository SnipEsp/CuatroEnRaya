package org.iesalandalus.programacion.cuatroenraya.modelo;

public record Jugador(String nombre, Ficha colorFichas) {

    public Jugador {
        validarNombre(nombre);
        validarColorFichas(colorFichas);
    }

    private static void validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar en blanco.");
        }
    }

    private static void validarColorFichas(Ficha colorFichas) {
        if (colorFichas == null) {
            throw new IllegalArgumentException("El color de las fichas no puede ser nulo.");
        }
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", nombre, colorFichas);
    }
}