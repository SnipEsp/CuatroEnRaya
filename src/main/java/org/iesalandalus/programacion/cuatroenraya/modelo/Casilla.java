package org.iesalandalus.programacion.cuatroenraya.modelo;

public class Casilla {

    private Ficha ficha;

    // Constructor por defecto: ficha empieza en null
    public Casilla() {
        this.ficha = null;
    }

    public Ficha getFicha() {
        return ficha;
    }

    // Setter que comprueba que la casilla no esté ocupada
    public void setFicha(Ficha ficha) {
        if (this.ficha != null) {
            throw new IllegalArgumentException("La casilla ya está ocupada");
        }
        this.ficha = ficha;
    }

    public boolean estaOcupada() {
        return ficha != null;
    }

    @Override
    public String toString() {
        String inicial = (ficha == null) ? " " : ficha.toString().substring(0, 1);
        return String.format("%s", inicial);
    }
}