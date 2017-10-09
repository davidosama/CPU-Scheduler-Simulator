package osproject;

public class Process {
    public String name;
    int burst;
    int arrive;
    int priority;
    int remainingTime;
    
    int start;
    int finish;
    int waiting;
//    int turnaround;
    
    Process (String n, int b, int a, int p){
        this.name = n;
        this.burst = b;
        this.arrive = a;
        this.priority = p;
        this.remainingTime = b;
    }
    Process (String n, int b, int a, int p, int r){
        this.name = n;
        this.burst = b;
        this.arrive = a;
        this.priority = p;
        this.remainingTime = r;
    }
    Process (String n, int b, int a, int p, int r, int start, int finish){
        this.name = n;
        this.burst = b;
        this.arrive = a;
        this.priority = p;
        this.remainingTime = r;
        this.start = start;
        this.finish = finish;
    }
    Process (String n, int b, int a, int p, int r, int start, int finish, int waiting){
        this.name = n;
        this.burst = b;
        this.arrive = a;
        this.priority = p;
        this.remainingTime = r;
        this.start = start;
        this.finish = finish;
        this.waiting = waiting;
    }
    int getArrive(){
        return this.arrive;
    }
    int getBurst(){
        return this.burst;
    }
    int getPriority(){
        return this.priority;
    }
    
}
