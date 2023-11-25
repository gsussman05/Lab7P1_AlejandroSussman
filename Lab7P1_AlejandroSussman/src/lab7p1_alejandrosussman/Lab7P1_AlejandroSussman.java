
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
                    System.out.println("Es el turno de: ");
                    boolean 
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

    public static boolean verificarVictoria(char[][] matrix) {
        boolean gane = false;
        for (int i = 0; i < matrix.length; i++) {
            gane = true;
            for (int j = 0; j < matrix[i].length; j++) {
                
            }
        }
        return gane;
    }
}
