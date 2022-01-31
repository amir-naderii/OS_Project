package ir.ac.kntu;

import java.util.List;
import java.util.concurrent.Semaphore;

public class CPU {
    private static CPU cpu = null;
    private int cores;
    private Semaphore semaphore;

    private CPU(int cores) {
        semaphore = new Semaphore(cores);
    }

    public static CPU getInstance(int cores)
    {
        if (cpu == null)
            cpu = new CPU(cores);

        return cpu;
    }

    public static CPU getInstance()
    {
        return cpu;
    }


    public void Login(int coreNeeded) throws InterruptedException {
        semaphore.acquire(coreNeeded);
    }

    public void logout(int coreUsed){
        semaphore.release(coreUsed);
    }

    public int availableSlots() {
        return semaphore.availablePermits();
    }

    public void setCores(int cores) {
        this.cores = cores;
    }
}
