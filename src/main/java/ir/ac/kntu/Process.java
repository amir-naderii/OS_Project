package ir.ac.kntu;

public class Process implements Runnable{
    private int pid;
    private int arrivalTime;
    private int cpuBurst;
    private int coreNeeded;
    private String state;


    public Process(int arrivalTime, int cpuBurst, int coreNeeded, int pid) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.cpuBurst = cpuBurst;
        this.coreNeeded = coreNeeded;
        this.state = "ready";
    }

    public void run() {
        try {
            CPU.getInstance().Login(this.coreNeeded);
            setState("running");
            System.out.println("process " + this.pid +" is running");
            Thread.sleep(this.cpuBurst * 1000);
            setState("terminated");
            CPU.getInstance().logout(this.coreNeeded);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getCpuBurst() {
        return cpuBurst;
    }

    public void setCpuBurst(int cpuBurst) {
        this.cpuBurst = cpuBurst;
    }

    public int getCoreNeeded() {
        return coreNeeded;
    }

    public void setCoreNeeded(int coreNeeded) {
        this.coreNeeded = coreNeeded;
    }

    public String getState() {
        synchronized (state) {
            return state;
        }
    }

    public void setState(String state) {
        synchronized (state){
            this.state = state;
        }
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
