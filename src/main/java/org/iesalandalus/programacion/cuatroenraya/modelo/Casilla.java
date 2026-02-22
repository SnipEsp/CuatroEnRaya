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
        if (ficha == null) {
            throw new NullPointerException("No se puede poner una ficha nula.");
        }
        if (estaOcupada()) {
            throw new CuatroEnRayaExcepcion("La casilla ya contiene una ficha.");
        }
        this.ficha = ficha;
    }

    public boolean estaOcupada() {
        return ficha != null;
    }

    @Override
    public String toString() {
        return (ficha == null) ? " " : ficha.toString(); // Ficha debería devolver "A" o "V"
    }
}
