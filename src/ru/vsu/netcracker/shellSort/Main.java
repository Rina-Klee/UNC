package ru.vsu.netcracker.shellSort;

import ru.vsu.netcracker.shellSort.sorter.ShellSorter;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.print("Введите размерность массива: ");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        System.out.print("Введите максимальный числовой предел: ");
        int max = scanner.nextInt();

        long start;
        long end;
        Random random = new Random();
        int[] array1 = new int[size];
        int[] array2 = new int[size];
        for (int i = 0; i < array1.length; i++) {
            array1[i] = random.nextInt(max);
            array2[i] = array1[i];
        }

        /* исходный массив до сортировки */
        System.out.println("\nИсходный массив до сортировки:");
        print(array1);

        /**
         * Подсчет времени исполнения
         */
        start = System.currentTimeMillis();
        ShellSorter.sort(array1);
        end = System.currentTimeMillis() - start;
        System.out.println("\nОднопоточная сортировка Шелла. Время: " + end + "мс");

        start = System.currentTimeMillis();
        MultithreadedShellSort mss = new MultithreadedShellSort(array2);
        mss.run();
        array2 = mss.getArray();
        end = System.currentTimeMillis() - start;
        System.out.println("Многопоточная сортировка Шелла. Время: " + end + "мс");
        System.out.println("Отсортированный массив:");
        print(array2);
    }

    private static void print(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
    }
}

