package ru.vsu.netcracker.shellSort;

class MultithreadedShellSort implements Runnable {
    private int[] firstPartArray;
    private int[] secondPartArray;
    private int[] array;
    private Thread thread;


    public MultithreadedShellSort(int[] array) {
        super();
        split(array);
    }

    private void split(int[] array) {
        int middleIndex = array.length / 2;
        int[] firstPartArray;
        int[] secondPartArray;

        /* если количество элементов в массиве нечетное */
        firstPartArray = new int[middleIndex];
        if (middleIndex * 2 != array.length) {
            secondPartArray = new int[middleIndex + 1];
        } else {
            secondPartArray = new int[middleIndex];
        }

        for (int i = 0; i < array.length; i++) {
            if (i < middleIndex) {
                firstPartArray[i] = array[i];
            }
            if (i >= middleIndex) {
                secondPartArray[i - middleIndex] = array[i];
            }
        }

        this.firstPartArray = firstPartArray;
        this.secondPartArray = secondPartArray;
    }

    /**
     * Начало и окончание потоков с последующим слиянием
     */
    @Override
    public void run() {
        try {
            Threads oneThread = new Threads(firstPartArray);
            Threads twoThread = new Threads(secondPartArray);
            oneThread.getThread().join();
            twoThread.getThread().join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        array = merge(firstPartArray, secondPartArray);
    }

    /**
     * Метод объединяет массивы, отсортированные в потоках
     * @param firstPartArray - первая часть отсортированного массива
     * @param secondPartArray - вторая часть отсортированного массива
     * @return результирующий массив
     */
    private int[] merge(int[] firstPartArray, int[] secondPartArray) {
        int length = firstPartArray.length + secondPartArray.length;
        int[] merged = new int[length];
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < length; i++) {
            if (i1 == firstPartArray.length) {
                merged[i] = secondPartArray[i2++];
            } else if (i2 == secondPartArray.length) {
                merged[i] = firstPartArray[i1++];
            } else {
                if (firstPartArray[i1] < secondPartArray[i2]) {
                    merged[i] = firstPartArray[i1++];
                } else {
                    merged[i] = secondPartArray[i2++];
                }
            }
        }
        return merged;
    }

    Thread getThread() { return thread; }

    int[] getArray() { return array; }
}