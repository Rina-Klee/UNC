package com.netcracker.lab;

import java.util.ArrayList;

public class Bank {
    private ArrayList<Clerk> clerks;
    private Storage storage;

    public Bank(int clerkCount) {
        this.clerks = new ArrayList<>(clerkCount);
        this.storage = new Storage(10000000);

        for (int i = 0; i < clerkCount; i++) {
            clerks.add(new Clerk(new Cashbox(500000), storage));
            clerks.get(i).start();
        }
    }

    public void service(Customer customer) {
        while (true) {
            for (Clerk clerk : clerks) {
                if (clerk.getQueue().isEmpty()) {
                    clerk.addToQueue(customer);
                    break;
                }
            }
            break;
        }
    }
}
