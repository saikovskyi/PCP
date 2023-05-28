package org.example;

public class Philosopher implements Runnable {
    private final int id;
    private final Table table;

    public Philosopher(int id, Table table) {
        this.id = id;
        this.table = table;
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Philosopher " + id + " thinking " + i + " time.");

                System.out.println("Philosopher " + id + " wanna eat " + i + " time.");
                table.requestFork(this);

                System.out.println("Philosopher " + id + " eats " + i + " time.");
                Thread.sleep(1000);

                table.returnFork(this);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
