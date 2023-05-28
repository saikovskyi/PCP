package cdu.edu.ua;

public class MyTread implements Runnable {
    private final int id;
    private final Breaker breaker;

    public MyTread(int id, Breaker breaker) {
        this.id = id;
        this.breaker = breaker;
    }

    @Override
    public void run() {
        long sum = 0;
        long additions = 0;
        do {
            sum+=2;
            additions++;
        } while (!breaker.isCanBreak(id));
        System.out.println(id + " - " + sum + ", additions: " + additions + ';');
    }
}
