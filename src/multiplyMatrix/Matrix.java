package multiplyMatrix;

import java.util.Random;

public class Matrix {

    private  int size;
    private int matrix[][];

    public Matrix(int size) {
        this.size = size;
        filling();
    }

    /**
     * Заполнение матрицы числовыми значениями
     */
    private void filling() {
        Random rand = new Random();
        matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = rand.nextInt(42);
            }
        }
    }

    /**
     * Вывод матрицы в консоль
     */
    public void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Вернуть столбец матрицы
     * @param indexRow номер столбца
     * @return столбец
     */
    public int[] getColumn(int indexRow) {
        int[] row = new int[size];
        for (int i = 0; i < size; i++) {
            row[i] = matrix[i][indexRow];
        }
        return row;
    }

    /**
     * Вернуть строку матрицы
     * @param indexColumn номер строки
     * @return строка
     */
    public int[] getRow(int indexColumn){
        return matrix[indexColumn];
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getSize() {
        return size;
    }

}

