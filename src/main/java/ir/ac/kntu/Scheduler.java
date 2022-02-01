package ir.ac.kntu;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Scheduler {
    private PriorityQueue<Process> pq;
    private ExecutorService pool;
    private SyncArrayList processes;

    public Scheduler(int processNumber,PriorityQueue pq, SyncArrayList processes){
       this.pq = pq;
       this.processes = processes;
       pool = Executors.newFixedThreadPool(processNumber+1 );
    }

    public void schedulingProcesses(int cores) throws InterruptedException {
        CPU.getInstance(cores);
        CPU.getInstance().setCores(cores);
        Monitoring monitor = new Monitoring(processes);
        pool.execute(monitor);
        boolean firstCheck = true;
        while(!pq.isEmpty()) {
            Process p = pq.peek();
            if(p.getCoreNeeded()<=CPU.getInstance().availableSlots()) {
                firstCheck = true;
                Process process = pq.poll();
                if (process.getState().equals("ready")) {
                    pool.execute(process);
                    Thread.sleep(1);
                }
            }else if(firstCheck){
                System.out.println("exception not enough cores");
                firstCheck = false;
            }
        }
        pool.shutdown();
    }

}
