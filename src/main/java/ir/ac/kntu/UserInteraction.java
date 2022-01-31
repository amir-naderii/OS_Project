package ir.ac.kntu;

import java.util.*;

public class UserInteraction {
    private PriorityQueue<Process> pq;

    public void inputTaker() throws InterruptedException {
        Random rand = new Random();
        Scanner in = new Scanner(System.in);
        System.out.println("enter number of processes:");
        int processNumber = in.nextInt();
        pq = new PriorityQueue(processNumber, new CompareByArrivalTime());
        for(int j = 0; j<processNumber ; j++){
            Process process = new Process(rand.nextInt(10)+1,rand.nextInt(10),3);
            pq.add(process);
        }
        Scheduler sch = new Scheduler(processNumber,pq);
        sch.schedulingProcesses(9);
    }
}
