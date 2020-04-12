package ru.vsu.netcracker.shellSort;

import ru.vsu.netcracker.shellSort.sorter.ShellSorter;

public class Threads implements Runnable {
    private int[] mas;
    private Thread thread;

    Threads(int[] mas) {
        super();
        this.mas = mas;
        this.thread = new Thread(this);
        thread.start();
    }

    Thread getThread() { return thread; }

    public void run() { ShellSorter.sort(mas); }
}
