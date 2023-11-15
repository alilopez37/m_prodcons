package threads;

import models.Monitor;

import java.util.concurrent.ThreadLocalRandom;

public class Productor implements Runnable{
    private Monitor monitor;
    public Productor(Monitor monitor){
        this.monitor = monitor;
    }
    @Override
    public void run() {
        while (true){
            monitor.insertar();
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(500)+500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
