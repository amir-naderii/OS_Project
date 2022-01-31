package ir.ac.kntu;

public class Process implements Runnable{
    private int arrivalTime;
    private int cpuBurst;
    private int coreNeeded;
    private int priority;
    private String state;


    public Process(int arrivalTime, int cpuBurst, int coreNeeded) {
        this.arrivalTime = arrivalTime;
        this.cpuBurst = cpuBurst;
        this.coreNeeded = coreNeeded;
        this.state = "ready";
    }

    public void run() {
        try {
            CPU.getInstance().Login(this.coreNeeded);
            this.state = "running";
            System.out.println("process running for" + this.arrivalTime);
            Thread.sleep(this.cpuBurst * 1000);
            this.state = "terminated";
            CPU.getInstance().logout(this.coreNeeded);
            System.out.println("available cores: " + CPU.getInstance().availableSlots());
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
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
