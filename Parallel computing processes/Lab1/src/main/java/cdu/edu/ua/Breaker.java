package cdu.edu.ua;

public class Breaker implements Runnable {
    private final boolean[] canBreak;
    private final double[] stopDuration;

    public Breaker(double[] stopDuration) {
        this.stopDuration = stopDuration;
        this.canBreak = new boolean[stopDuration.length];
    }

    @Override
    public void run() {
        double min = 0;
        int minIndex = 0;
        for (int i = 0; i < stopDuration.length; i++) {
            for (int j = 0; j < stopDuration.length; j++) {
                if (stopDuration[j] != 0) {
                    min = stopDuration[j];
                    minIndex = j;
                    break;
                }
            }

            for (int j = 0; j < stopDuration.length; j++) {
                if (stopDuration[j] != 0 && min > stopDuration[j]) {
                    min = stopDuration[j];
                    minIndex = j;
                }
            }

            for (int j = 0; j < stopDuration.length; j++) {
                if (stopDuration[j] > 0) {
                    stopDuration[j] -= min;
                }
            }

            try {
                Thread.sleep((long) min * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            canBreak[minIndex] = true;
        }
    }

    synchronized public boolean isCanBreak(int id) {
        return canBreak[id];
    }
}
