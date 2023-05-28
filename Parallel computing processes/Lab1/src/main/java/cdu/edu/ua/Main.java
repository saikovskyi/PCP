package cdu.edu.ua;

public class Main {
    public static void main(String[] args) {
        int threadCount = 12;
        double[] stop_duration = {5.0, 3.0, 2.0, 1.0, 4.0, 12.0, 11.0, 9.0, 10.0, 7.0, 8.0, 6.0};
        //double[] stop_duration = {5.0, 3.0, 2.0, 1.0, 4.0, 12.0};
        Breaker breaker = new Breaker(stop_duration);
        new ThreadController(threadCount, breaker);

        new Thread(breaker).start();
    }
}