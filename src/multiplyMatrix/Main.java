package multiplyMatrix;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.print("Введите размерность матриц: ");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        Matrix m1 = new Matrix(size);
        Matrix m2 = new Matrix(size);
        long startTime = System.currentTimeMillis();
        MatrixMultiply.multiply(m1, m2);
        System.out.println("Время однопоточного умножения: " + (System.currentTimeMillis() - startTime) + " мс");

        startTime = System.currentTimeMillis();
        MultyThreadMultiplyer.multiply(m1, m2, 5);
        System.out.println("Время многопоточного умножения: " + (System.currentTimeMillis() - startTime) + " мс");
    }
}
