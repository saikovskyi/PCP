package org.cdu.edu.ua;

public class ThreadController {
    private final int threadCount;
    private final double[] array;

    public ThreadController(int threadCount, double[] array) {
        this.threadCount = threadCount;
        this.array = array;
        this.minInBoundElems = new double[threadCount];
        this.finishedThreadCount = 0;
        threadMin();
    }

    public void threadMin() {
        MyThread[] myThread = new MyThread[threadCount];

        for (int i = 0; i < myThread.length; i++) {
            myThread[i] = new MyThread(
                    i,
                    i * array.length / threadCount,
                    (i + 1) * array.length / threadCount,
                    this);
        }

        for (MyThread thread : myThread) {
            new Thread(thread).start();
        }
    }

    private final double[] minInBoundElems;
    double min, minIndex;
    private int finishedThreadCount;

    synchronized public void collectMin(int id, double minElem) {
        minInBoundElems[id] = minElem;
        if (minElem < min) {
            min = minElem;
        }
        incFinishedThreadCount();
    }

    synchronized private void incFinishedThreadCount() {
        finishedThreadCount++;
        notify();
    }

    public synchronized double getMin() {
        while (getFinishedThreadCount() < threadCount){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return findMinInArray(minInBoundElems);
    }

    private int getFinishedThreadCount() {
        return finishedThreadCount;
    }

    public double partMin(int firstBound, int secondBound) {
        double min = array[firstBound];

        for (int i = firstBound; i < secondBound; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }

    private double findMinInArray(double[] array) {
        double min = array[0];

        for (double num : array) {
            if (num < min) {
                min = num;
            }
        }

        return min;
    }
}
