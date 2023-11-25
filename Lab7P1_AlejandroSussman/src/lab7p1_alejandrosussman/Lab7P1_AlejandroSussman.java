
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab7p1_alejandrosussman;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author Gabriel
 */
public class Lab7P1_AlejandroSussman {

    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Fila 1 Asiento 12
        System.out.println("****MENU****");
        System.out.println("Ingrese una opcion: ");
        System.out.println("1. TRES EN RAYA ");
        System.out.println("2. PUNTOS DE SILLA ");
        int opcion_m = sc.nextInt();

        while (opcion_m != 3) {
            switch (opcion_m) {
                case 1:
                    System.out.println("---Bienvenido al Tres en Raya---");
                    System.out.println("Tablero Actual");
                    char[][] tablero = generarTablero();
                    print(tablero);
                    int turno = 1; //1: Jugador, 2: PC
                    int jugadas = 0; // Maximo 9 jugadas
                    boolean hay_ganador = false;
                    while (jugadas <= 9 && hay_ganador != true) {

                        if (turno == 1) {
                            System.out.println("Es el turno de X");
                            boolean esValido = false;
                            while (esValido == false) {
                                System.out.println("Ingrese la fila(0,1,2): ");
                                int fila = sc.nextInt();
                                System.out.println("Ingrese la columna(0,1,2): ");
                                int colm = sc.nextInt();

                                esValido = verificarPosicionValida(tablero, fila, colm);
                                if (esValido == false) {
                                    System.out.println("La posicion ya fue juagada, seleccione otra: ");
                                } else {
                                    System.out.println("El usuario ha elegido la posicion (" + fila + ", " + colm + ")");
                                    tablero[fila][colm] = 'X';
                                }
                            }
                        } else {
                            System.out.println("Es el turno de 0");
                            boolean esValido = false;
                            while (esValido == false) {
                                int fila = rand.nextInt(3);
                                int colm = rand.nextInt(3);
                                esValido = verificarPosicionValida(tablero, fila, colm);
                                if (esValido == true) {
                                    System.out.println("La maquina ha elegido la posicion (" + fila + ", " + colm + ")");
                                    tablero[fila][colm] = '0';
                                }
                            }
                        }

                        print(tablero);

                        hay_ganador = verificarVictoria(tablero, 'X');
                        if (hay_ganador == false) {
                            hay_ganador = verificarVictoria(tablero, '0');
                        }
                        jugadas++;
                        if (turno == 1) {
                            turno = 2;
                        } else {
                            turno = 1;
                        }
                    }

                    break;
                case 2:
                    System.out.println("---Bienvenido a Punto de Silla---");
                    System.out.println("Ingrese el numero de filas: ");
                    int filas = sc.nextInt();
                    System.out.println("Ingrese el numero de columnas: ");
                    int colum = sc.nextInt();
                    int[][] matrix = generarIntMatrizAleatoria(filas, colum, rand);
                    printRandMatriz(matrix);
                    encontrarPuntosSilla(matrix);

                    break;
                default:
                    System.out.println("Opcion invalida, la opcion ingresada debe ser 1 o 2");
            }
            System.out.println("****MENU****");
            System.out.println("Ingrese una opcion: ");
            System.out.println("1. TRES EN RAYA ");
            System.out.println("2. PUNTOS DE SILLA ");
            opcion_m = sc.nextInt();
        }
    }

    public static void print(char[][] matriz) {
        int filas = matriz.length;
        int cols = matriz[0].length;
        for (int i = 0; i < filas; i++) {
            System.out.print("[ ");
            for (int j = 0; j < cols; j++) {
                System.out.print(matriz[i][j]);
                if ((j + 1) < cols) {
                    System.out.print(", ");
                }
            }
            System.out.println(" ]");
        }
    }

    public static char[][] generarTablero() {
        char[][] tablero = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = ' ';
            }
        }
        return tablero;
    }

    public static int[][] generarIntMatrizAleatoria(int filas, int colum, Random rnd) {
        int[][] matrix = new int[filas][colum];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < colum; j++) {
                matrix[i][j] = rnd.nextInt(100) + 1;
            }
        }
        return matrix;
    }

    public static void printRandMatriz(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void encontrarPuntosSilla(int[][] matrix) {
        boolean hayPuntoSilla = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int valor = matrix[i][j];
                boolean esmenor = true;
                for (int k = 0; k < matrix[i].length; k++) {
                    if (valor > matrix[i][k]) {
                        esmenor = false;
                    }
                }
                if (esmenor) {
                    boolean esmayor = true;
                    for (int k = 0; k < matrix.length; k++) {
                        if (valor < matrix[k][j]) {
                            esmayor = false;
                        }
                    }
                    if (esmayor) {
                        System.out.println("La coordenada " + i + ", " + j + " es punto silla: " + valor);
                        hayPuntoSilla = true;
                    }
                }
            }
        }
        if (!hayPuntoSilla) {
            System.out.println("No se encontro ningun punto silla");
        }
    }

    public static boolean verificarPosicionValida(char[][] matrix, int fila, int col) {
        if (matrix[fila][col] == ' ') {
            return true;

        } else {
            return false;
        }
    }

    public static boolean verificarVictoria(char[][] matrix, char jugador) {
        boolean gane = false;
        int cols = matrix[0].length - 1;

        int contf = 0;
        int contc = 0;
        int diag1 = 0;
        int diag2 = 0;

        for (int i = 0; i < matrix.length; i++) {
            contf = 0;
            contc = 0;
            diag1 = 0;
            diag2 = 0;
            // Filas y Columnas
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == jugador) {
                    contf++;
                }
                if (matrix[j][i] == jugador) {
                    contc++;
                }                
            }
            //Diagonales
            for(int j =0 ; j <= cols; j++)
            {
                if (matrix[j][j] == jugador) {
                    diag1++;
                }
                if (matrix[j][cols - j] == jugador) {
                    diag2++;
                }
            }
            
            if (contf == 3 || contc == 3 || diag1 == 3 || diag2 == 3) {
                gane = true;
            }
        }
        if (gane) {
            System.out.println("El ganador es: " + jugador);
        }
        return gane;
    }
}
