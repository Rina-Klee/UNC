package multiplyMatrix;

/**
 * Поток для вычисления группы ячеек матрицы.
 */
class MultiplierThread extends Thread
{

    private final int[][] firstMatrix; // Первая матрица
    private final int[][] secondMatrix; // Вторая матрица
    private final int[][] resultMatrix; // Результирующая матрица
    private final int firstIndex; // Начальный индекс
    private final int lastIndex; // Конечный индекс
    private final int sumLength; // Число членов суммы при вычислении значения ячейки

    /**
     * @param firstMatrix  Первая  матрица
     * @param secondMatrix Вторая  матрица
     * @param resultMatrix Результирующая матрица
     * @param firstIndex   Начальный индекс (ячейка с этим индексом вычисляется)
     * @param lastIndex    Конечный индекс (ячейка с этим индексом не вычисляется)
     */
    public MultiplierThread(final int[][] firstMatrix,
                            final int[][] secondMatrix,
                            final int[][] resultMatrix,
                            final int firstIndex,
                            final int lastIndex)
    {
        this.firstMatrix  = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.resultMatrix = resultMatrix;
        this.firstIndex   = firstIndex;
        this.lastIndex    = lastIndex;

        sumLength = secondMatrix.length;
    }

    /**Вычисление значения в одной ячейке
     *
     * @param row Номер строки ячейки
     * @param column Номер столбца ячейки
     */
    private void calcValue(final int row, final int column)
    {
        int sum = 0;
        for (int i = 0; i < sumLength; ++i)
            sum += firstMatrix[row][i] * secondMatrix[i][column];
        resultMatrix[row][column] = sum;
    }

    /**
     *  Рабочая функция потока
     */
    @Override
    public void run()
    {
        System.out.println("Поток " + getName() + " запущен. Вычисление ячеек с " + firstIndex + " до " + lastIndex + "...");

        final int columnCount = secondMatrix[0].length;  // Число столбцов результирующей матрицы.
        for (int index = firstIndex; index < lastIndex; ++index)
            calcValue(index / columnCount, index % columnCount);

        System.out.println("Поток " + getName() + " остановлен");
    }
}