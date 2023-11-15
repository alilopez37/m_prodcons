package models;

import java.util.Arrays;
import java.util.Random;

public class Monitor {
    private final int TOTAL = 10;
    private int buffer[] = new int[TOTAL];
    private int lleno;
    private Random random = new Random(System.currentTimeMillis());
    public Monitor(){
        for (int i=0;i<TOTAL;i++)
            buffer[i] = 0;
        lleno = 0;
    }
    public synchronized void insertar(){
        int valor;
        int indice;
        valor = random.nextInt(400) + 100;
        while (lleno == TOTAL)
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        indice = 0;
        while ( buffer[indice] != 0)
            indice++;
        buffer[indice] = valor;
        System.out.println("P: " + toString());
        lleno++;
        this.notifyAll();
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "buffer=" + Arrays.toString(buffer) +
                '}';
    }

    public synchronized void extraer(){
        int valor;
        int indice;
        while (lleno == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        indice = 0;
        while (buffer[indice] == 0)
            indice++;
        valor = buffer[indice];
        buffer[indice] = 0;
        System.out.println("C: " + toString());
        lleno--;
        this.notifyAll();
    }

}
