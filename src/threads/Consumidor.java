package threads;

import models.Monitor;

import java.util.concurrent.ThreadLocalRandom;

public class Consumidor implements Runnable{
    private Monitor monitor;
    public Consumidor(Monitor monitor){
        this.monitor = monitor;
    }
    @Override
    public void run() {
        while (true){
            monitor.extraer();
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(500)+500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
