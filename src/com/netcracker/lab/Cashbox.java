package com.netcracker.lab;

public class Cashbox {
    private int cash;

    public Cashbox(int cash) {
        this.cash = cash;
    }

    public void put(int money) {
        cash += money;
    }

    public int get(int money) {
        if (cash < money) {
            return 0;
        } else {
            cash -= money;
            return money;
        }
    }

    public int getCash() {
        return cash;
    }
}

