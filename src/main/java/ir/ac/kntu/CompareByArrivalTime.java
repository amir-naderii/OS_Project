package ir.ac.kntu;

import java.util.Comparator;

public class CompareByArrivalTime implements Comparator<Process> {
    public int compare(Process p1, Process p2) {
        if(p1.getArrivalTime() < p2.getArrivalTime())
            return -1;
        else if(p2.getArrivalTime() < p1.getArrivalTime())
            return 1;
        return 0;
    }
}
