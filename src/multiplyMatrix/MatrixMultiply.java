package multiplyMatrix;

public class MatrixMultiply {

    /**
     * Умножение матриц
     *
     * @param m1 матрица 1
     * @param m2 матрица 2
     * @return результат
     */
    public static Matrix multiply(Matrix m1, Matrix m2) {
        Matrix resultMatrix = new Matrix(m1.getSize());

        int[] row, column;
        int sum = 0;

        for (int i = 0; i < resultMatrix.getSize(); i++) {
            for (int j = 0; j < resultMatrix.getSize(); j++) {
                row = m1.getRow(i);
                column = m2.getColumn(j);
                for (int k = 0; k < resultMatrix.getSize(); k++) {
                    sum += row[k] * column[k];
                }
                resultMatrix.getMatrix()[i][j] = sum;
                sum = 0;
            }
        }
        return resultMatrix;
    }
}