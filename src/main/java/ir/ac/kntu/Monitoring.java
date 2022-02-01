package ir.ac.kntu;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Monitoring implements Runnable{
    private SyncArrayList processes;

    public Monitoring(SyncArrayList processes) {
        this.processes = processes;
    }

    @Override
    public void run() {
        long now = System.currentTimeMillis();
        SyncArrayList readyProcesses = processes.copy();
        SyncArrayList runningProcesses = new SyncArrayList();

        while (!readyProcesses.isEmpty()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("number of cores currently occupied: "  + (CPU.getInstance().getCores()-CPU.getInstance().availableSlots()) +
                    "\nnumber of free cores: " + CPU.getInstance().availableSlots() + "\n");
            for (int i = 0; i < processes.size(); i++) {
                if (processes.getList(i).getState().equals("terminated")) {
                    runningProcesses.removeList(processes.getList(i));
                }
            }
            for (int i = 0; i < readyProcesses.size(); i++){
                if (readyProcesses.getList(i).getState().equals("running") || readyProcesses.getList(i).getState().equals("terminated")) {
                    runningProcesses.addList(readyProcesses.getList(i));
                    readyProcesses.removeList(readyProcesses.getList(i));
                }
            }
            System.out.println("currently ready processes: ");
            for (int i = 0; i < readyProcesses.size(); i++) {
                System.out.println(" pid: "+readyProcesses.getList(i).getPid() + " cpu burst: " +
                        readyProcesses.getList(i).getCpuBurst() + " arrival time: " +
                        readyProcesses.getList(i).getArrivalTime() + " core needed: " +
                        readyProcesses.getList(i).getCoreNeeded());
            }
            System.out.println("\ncurrently running processes: ");
            for (int i = 0; i < runningProcesses.size(); i++) {
                System.out.println(" pid: "+runningProcesses.getList(i).getPid() + " cpu burst: " +
                        runningProcesses.getList(i).getCpuBurst() + " arrival time: " +
                        runningProcesses.getList(i).getArrivalTime() + " core needed: " +
                        runningProcesses.getList(i).getCoreNeeded());
            }
            System.out.println("time elapsed: " + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - now));
            System.out.println("\n---------------------------------------------------");
        }
    }
}
