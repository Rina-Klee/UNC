package com.netcracker.lab;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Bank bank = new Bank(5);
        while (true) {
            if (random.nextInt(100000) == 1) {
                Customer customer = new Customer(bank);
                customer.start();
            }
        }
    }
}
