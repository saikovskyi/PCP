package org.cdu.edu.ua;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int threadCount = 12;
        double[] array = getRandomArray(12000000);

        System.out.println("Multithreading min search:");
        long start = System.currentTimeMillis();
        ThreadController threadController = new ThreadController(threadCount, array);
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;

        System.out.println(threadController.getMin());
        System.out.println("Time: " + timeElapsed + "ms");
        System.out.println();

        System.out.println("Default min method:");
        start = System.currentTimeMillis();
        double min = Arrays.stream(array).min().getAsDouble();
        finish = System.currentTimeMillis();
        timeElapsed = finish - start;

        System.out.println(min);
        System.out.println("Time: " + timeElapsed + "ms");
        System.out.println();
    }

    private static double[] getRandomArray(int count) {
        Random random = new Random();
        double[] array = new double[count];

        for (int i = 0; i < count; i++) {
            array[i] = random.nextDouble(5000);
        }

        array[random.nextInt(count)] = -(random.nextDouble(5000));

        return array;
    }
}