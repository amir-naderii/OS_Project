package ir.ac.kntu;

import java.time.LocalTime;
import java.util.*;

public class UserInteraction {
    private PriorityQueue<Process> pq;
    private SyncArrayList processes;

    public void inputTaker() throws InterruptedException {
        Random rand = new Random();
        Scanner in = new Scanner(System.in);
        System.out.println("enter number of processes:");
        int processNumber = in.nextInt();
        System.out.println("enter number of cores");
        int cores = in.nextInt();
        pq = new PriorityQueue(processNumber, new CompareByArrivalTime());
        processes = new SyncArrayList();
        int cnt = 0;
        for(int j = 0; j<processNumber ; j++){
            Process process = new Process(rand.nextInt(10)+1,rand.nextInt(10)+1,rand.nextInt(cores-2)+1,cnt);
            pq.add(process);
            processes.addList(process);
            cnt++;
        }
        Scheduler sch = new Scheduler(processNumber,pq,processes);
        sch.schedulingProcesses(cores);
    }

}
