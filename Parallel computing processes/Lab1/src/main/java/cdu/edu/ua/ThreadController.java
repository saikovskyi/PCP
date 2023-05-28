package cdu.edu.ua;

public class ThreadController {

    public ThreadController(int threadCount, Breaker breaker) {
        for (int i = 0; i < threadCount; i++) {
            new Thread(new MyTread(i, breaker)).start();
        }
    }
}
