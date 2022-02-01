package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;

public class SyncArrayList {
    private ArrayList<Process> array;

    public SyncArrayList() {
        this.array = new ArrayList();
    }

    public SyncArrayList(ArrayList<Process> arr){
        array = new ArrayList<>(arr);
    }

    public void addList(Process p){
        synchronized (array) {
            array.add(p);
        }
    }

    public void removeList(Process p){
        synchronized (array) {
            array.remove(p);
        }
    }

    public Process getList(int i){
        synchronized (array){
            return array.get(i);
        }
    }

    public boolean isEmpty(){
        synchronized (array){
            return array.isEmpty();
        }
    }

    public int size(){
        synchronized (array){
            return array.size();
        }
    }

    public SyncArrayList copy(){
        synchronized (array){
            return new SyncArrayList(array);
        }
    }

}
