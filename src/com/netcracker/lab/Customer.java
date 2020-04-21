package com.netcracker.lab;

import java.util.Random;

public class Customer extends Thread {
    private final int money;
    private final boolean isPut;
    private final long time;
    private final Bank bank;

    public Customer(Bank bank) {
        Random random = new Random();
        this.money = random.nextInt(100000);
        this.isPut = random.nextBoolean();
        this.time = random.nextInt(15000);
        this.bank = bank;
    }

    @Override
    public void run() {
        bank.service(this);
    }

    public int getMoney() {
        return money;
    }

    public boolean isPut() {
        return isPut;
    }

    public long getTime() {
        return time;
    }

    public Bank getBank() {
        return bank;
    }
}
