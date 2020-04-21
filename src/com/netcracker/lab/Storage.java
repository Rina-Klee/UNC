package com.netcracker.lab;

public class Storage {
    private int cash;

    public Storage(int cash) {
        this.cash = cash;
    }

    public synchronized void put(int money) {
        cash += money;
    }

    public synchronized int get(int money) {
        if (cash < money) {
            cash += 10000000;
            System.out.println("Пополнение хранилища");
        }
        cash -= money;
        return money;
    }

    public int getMoney() {
        return this.cash;
    }
}
