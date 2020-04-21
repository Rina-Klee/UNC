package com.netcracker.lab;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Clerk extends Thread {
    private final Cashbox cashbox;
    private final Storage storage;
    private final LinkedList<Customer> queue;

    public Clerk(Cashbox cashbox, Storage storage) {
        this.cashbox = cashbox;
        this.storage = storage;
        this.queue = new LinkedList<>();
    }

    public void addToQueue(Customer customer) {
        synchronized (queue) {
            queue.add(customer);
            queue.notify();
        }
    }

    @Override
    public void run() {
        Customer customer;
        while (true) {
           synchronized (queue) {
               while (queue.isEmpty()) {
                   try {
                       queue.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               customer = queue.removeFirst();
           }
           try {
               if (customer.isPut()) {
                   if (cashbox.getCash() + customer.getMoney() > 1000000) {
                       storage.put(cashbox.get(500000));
                       System.out.println("Переполнение кассы");
                       Thread.sleep(20000);
                       System.out.println("Количество денег в хранилище " + storage.getMoney());
                   }
                   this.cashbox.put(customer.getMoney());
                   System.out.println("Клиент внес " + customer.getMoney() + " виртуальных денег");
               } else {
                   if (cashbox.getCash() < customer.getMoney()) {
                       cashbox.put(storage.get(500000));
                       System.out.println("Пополение кассы");
                       Thread.sleep(20000);
                       System.out.println("Количество денег в хранилище " + storage.getMoney());
                   }
                   this.cashbox.get(customer.getMoney());
                   System.out.println("Клиент снял " + customer.getMoney() + " виртуальных денег");
               }
               System.out.println("Количество денег в кассе " + cashbox.getCash());
               Thread.sleep(customer.getTime());
           } catch (InterruptedException | RuntimeException e) {
               e.printStackTrace();
           }
        }
    }

    public LinkedList<Customer> getQueue() {
        return queue;
    }
}
