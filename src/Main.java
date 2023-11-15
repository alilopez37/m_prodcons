import models.Monitor;
import threads.Consumidor;
import threads.Productor;

public class Main {
    public static void main(String[] args) {
        Monitor monitor = new Monitor();

        Thread prod = new Thread(new Productor(monitor));
        Thread cons = new Thread(new Consumidor(monitor));

        prod.start();
        cons.start();

        try {
            prod.join();
            cons.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}