package ir.ac.kntu;

import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Scheduler {
    private PriorityQueue<Process> pq;
    ExecutorService pool;

    public Scheduler(int processNumber,PriorityQueue pq){
       this.pq = pq;
       pool = Executors.newFixedThreadPool(processNumber);
    }

    public void schedulingProcesses(int cores) throws InterruptedException {
        CPU.getInstance(cores);
        while(!pq.isEmpty()) {
            Process process = pq.poll();
            if(process.getState().equals("ready")){
                pool.execute(process);
            }
        }
        pool.shutdown();
    }

}
