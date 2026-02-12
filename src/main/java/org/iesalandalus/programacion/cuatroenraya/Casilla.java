package org.iesalandalus.programacion.cuatroenraya;


public class Casilla() {
    private Ficha ficha = null;

    public Ficha casilla(Ficha ficha) {
        this.ficha = ficha;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public boolean estaOcupada() {
        return ficha != null;
    }

    @Override
    public String toString() {
        return String.format("Casilla (ficha=%s)", ficha);
    }
}
