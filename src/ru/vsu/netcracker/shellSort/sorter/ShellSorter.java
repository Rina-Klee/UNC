package ru.vsu.netcracker.shellSort.sorter;

public class ShellSorter {

    /**
     * Алгоритм сортировки Шелла
     * @param array
     */
    public static int[] sort(int[] array) {
        int middleIndex = array.length / 2;
        while (middleIndex > 0) {
            for (int i = 0; i < (array.length - middleIndex); i++) {
                int j = i;
                while ((j >= 0) && (array[j] > array[j + middleIndex])) {
                    int temp = array[j];
                    array[j] = array[j + middleIndex];
                    array[j + middleIndex] = temp;
                    j--;
                }
            }
            middleIndex /= 2;
        }
        return array;
    }
}