package org.iesalandalus.programacion.cuatroenraya.modelo;

public class Tablero {
    // Constantes según el diagrama y el enunciado
    public static final int FILAS = 6;
    public static final int COLUMNAS = 7;
    public static final int GANAR = 4;

    private Casilla[][] casillas;

    // + Tablero()
    public Tablero() {
        this.casillas = new Casilla[FILAS][COLUMNAS];
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                this.casillas[i][j] = new Casilla();
            }
        }
    }

    // + estaVacio(): boolean
    public boolean estaVacio() {
        for (int j = 0; j < COLUMNAS; j++) {
            if (!columnaVacia(j)) return false;
        }
        return true;
    }

    // - columnaVacia(columna: int): boolean
    private boolean columnaVacia(int columna) {
        return !casillas[0][columna].estaOcupada();
    }

    // + estaLleno(): boolean
    public boolean estaLleno() {
        for (int j = 0; j < COLUMNAS; j++) {
            if (!columnaLlena(j)) return false;
        }
        return true;
    }

    // - columnaLlena(columna: int): boolean
    private boolean columnaLlena(int columna) {
        return casillas[0][columna].estaOcupada(); // Si el techo tiene ficha, ya no cabe más
    }

    // + introducirFicha(columna: int, ficha: Ficha): boolean
    public boolean introducirFicha(int columna, Ficha ficha) throws NullPointerException {
        comprobarColumna(columna);
        comprobarFicha(ficha);
        if (columnaLlena(columna)) {
            throw new CuatroEnRayaExcepcion("Columna llena.");
        }

        int fila = getPrimeraFilaVacia(columna);
        casillas[fila][columna].setFicha(ficha);

        return comprobarTirada(fila, columna, ficha);
    }

    // - comprobarFicha(ficha: Ficha)
    private void comprobarFicha(Ficha ficha) {
        if (ficha == null) {
            throw new NullPointerException("La ficha no puede ser nula.");
        }
    }

    // - comprobarColumna(columna: int)
    private void comprobarColumna(int columna) {
        if (columna < 0 || columna >= COLUMNAS) {
            throw new IllegalArgumentException("Columna incorrecta."); // Cambiado mensaje y tipo
        }
    }

    // - getPrimeraFilaVacia(columna: int): int
    private int getPrimeraFilaVacia(int columna) {
        for (int i = FILAS - 1; i >= 0; i--) { // DEBE SER >= 0
            if (!casillas[i][columna].estaOcupada()) return i;
        }
        return -1;
    }

    // - comprobarTirada(fila: int, columna: int, ficha: Ficha): boolean
    private boolean comprobarTirada(int fila, int columna, Ficha ficha) {
        return comprobarHorizontal(fila, ficha) ||
                comprobarVertical(columna, ficha) ||
                comprobarDiagonalNE(fila, columna, ficha) ||
                comprobarDiagonalNO(fila, columna, ficha);
    }

    // - objetivoAlcanzado(fichasIgualesConsecutivas: int): boolean
    private boolean objetivoAlcanzado(int fichasIgualesConsecutivas) {
        return fichasIgualesConsecutivas >= GANAR;
    }

    // - comprobarHorizontal(fila: int, ficha: Ficha): boolean
    private boolean comprobarHorizontal(int fila, Ficha ficha) {
        int contador = 0;
        for (int j = 0; j < COLUMNAS; j++) {
            if (casillas[fila][j].getFicha() == ficha) {
                contador++;
                if (objetivoAlcanzado(contador)) return true;
            } else {
                contador = 0;
            }
        }
        return false;
    }

    // - comprobarVertical(columna: int, ficha: Ficha): boolean
    private boolean comprobarVertical(int columna, Ficha ficha) {
        int contador = 0;
        for (int i = 0; i < FILAS; i++) {
            if (casillas[i][columna].getFicha() == ficha) {
                contador++;
                if (objetivoAlcanzado(contador)) return true;
            } else {
                contador = 0;
            }
        }
        return false;
    }

    // - comprobarDiagonalNE(filaActual: int, columnaActual: int, ficha: Ficha): boolean
    private boolean comprobarDiagonalNE(int filaActual, int columnaActual, Ficha ficha) {
        int d = menor(filaActual, columnaActual);
        int f = filaActual - d;
        int c = columnaActual - d;
        int cont = 0;
        while (f < FILAS && c < COLUMNAS) {
            if (casillas[f][c].getFicha() == ficha) {
                cont++;
                if (objetivoAlcanzado(cont)) return true;
            } else {
                cont = 0;
            }
            f++;
            c++;
        }
        return false;
    }

    // - comprobarDiagonalNO(filaActual: int, columnaActual: int, ficha: Ficha): boolean
    private boolean comprobarDiagonalNO(int filaActual, int columnaActual, Ficha ficha) {
        int d = menor(filaActual, (COLUMNAS - 1) - columnaActual);
        int f = filaActual - d;
        int c = columnaActual + d;
        int cont = 0;
        while (f < FILAS && c >= 0) {
            if (casillas[f][c].getFicha() == ficha) {
                cont++;
                if (objetivoAlcanzado(cont)) return true;
            } else {
                cont = 0;
            }
            f++;
            c--;
        }
        return false;
    }

    // - menor(fila: int, columna: int): int
    private int menor(int fila, int columna) {
        return Math.min(fila, columna);
    }

    // + toString(): String
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int f = 0; f < FILAS; f++) { // De 0 a 5
            sb.append("|");
            for (int c = 0; c < COLUMNAS; c++) {
                sb.append(casillas[f][c].toString()); // Debe devolver "A", "V" o " "
            }
            sb.append("|\n");
        }
        sb.append(" -------\n");
        // Otra manera de poner esto más correcta
        //String barraHorizontal = "-".repeat(COLUMNAS);
        return sb.toString();
    }
}