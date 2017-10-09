package osproject;

import java.util.*;

public class Algorithms {
    public static LinkedList <Process> Processes;
    public static LinkedList <Process> OriginalProcesses = new LinkedList ();
    public static int qt;
    public static double avgWaiting;
    
    static void FCFS(){
        Processes = new LinkedList(OriginalProcesses);
        for (int i=0; i<(Processes.size()-1); i++){
            for(int j=1; j<(Processes.size()-i); j++){
                if (Processes.get(j-1).arrive > Processes.get(j).arrive){
                    Collections.swap (Processes , j, j-1 );
                }
            }
        }
    }
    
    static void Nonpreem_table(){
        int clock = Processes.get(0).arrive;
        int totalWaiting = 0;
        System.out.println("Clock start "+clock);
        for(int i=0; i<Processes.size(); i++){
            Processes.get(i).start = clock;
            Processes.get(i).finish = clock + Processes.get(i).burst;
            clock += Processes.get(i).burst;
            Processes.get(i).waiting = Processes.get(i).start - Processes.get(i).arrive;
            totalWaiting += Processes.get(i).waiting;
        }
        avgWaiting = ((double)totalWaiting)/Processes.size();
        System.out.println("Clock end "+clock+" average waiting "+avgWaiting);
        
    }
    
    static void SJF_preemptive(){
        LinkedList <Process> Sorting = new LinkedList ();
        LinkedList <Process> Sorted = new LinkedList ();
        LinkedList <Process> OriginalList = new LinkedList(OriginalProcesses);
        Processes = new LinkedList (OriginalProcesses);
        FCFS();
        
        int clock = Processes.get(0).getArrive();
        int count = Processes.size();

        int totalBurst = 0 + Processes.get(0).getArrive();
        for(int i=0; i<Processes.size(); i++){
            totalBurst += Processes.get(i).burst;
        }
        System.out.println("Clock starts at "+clock+" and ends at "+totalBurst);
        while ( clock <= totalBurst ){
            for(int i=0; i<Processes.size(); i++){
                if (Processes.get(i).getArrive() <= clock){
                    System.out.println(Processes.get(i).name);
                    String name = Processes.get(i).name;
                    int burst = Processes.get(i).burst;
                    int arrive = Processes.get(i).arrive;
                    int priority = Processes.get(i).priority;
                    Sorting.add(new Process(name,burst,arrive,priority));
                    Processes.remove(i);
                    i--;
                }
            }
            System.out.println("Sorting size before : "+Sorting.size());
            System.out.println("clock "+clock);
            for (int i=0; i<(Sorting.size()-1); i++){
                for(int j=1; j<(Sorting.size()-i); j++){
                    if (Sorting.get(j-1).remainingTime > Sorting.get(j).remainingTime){
                        Collections.swap (Sorting , j, j-1 );
                    }
                }
            }
            if(Sorting.size()>0){
                if ( Sorting.get(0).remainingTime <= 0){
                    Sorting.remove(0);
                }

                Sorting.get(0).remainingTime -= 1;
                String name = Sorting.get(0).name;
                int burst = Sorting.get(0).burst;
                int arrive = Sorting.get(0).arrive;
                int priority = Sorting.get(0).priority;
                int remaining = Sorting.get(0).remainingTime;
                int start = clock;
                int finish = clock+1;
//                int waiting = start - arrive;
//                totalWaiting += waiting;
                Sorted.add(new Process(name,burst,arrive,priority,remaining,start,finish));
                
                if ( Sorting.get(0).remainingTime <= 0){
                    Sorting.remove(0);
                }
            }
            clock++;
        }
        Processes = new LinkedList (Sorted);
        MergeProcesses();
        for(int i=0; i<OriginalList.size(); i++){
            for (int j=0; j<Processes.size(); j++){
                if (OriginalList.get(i).name.equals(Processes.get(j).name)){
//                    OriginalList.get(i).finish = Processes.get(j).finish;
                    OriginalList.get(i).waiting = Processes.get(j).finish - OriginalList.get(i).arrive - OriginalList.get(i).burst;
                    
                }
            }
        }
        int totalWaiting = 0;
        for(int i=0; i<OriginalList.size(); i++){
            System.out.print(OriginalList.get(i).name+" waiting "+OriginalList.get(i).waiting+"\n");
            totalWaiting += OriginalList.get(i).waiting;
        }
        System.out.println("total waiting: "+totalWaiting);
        avgWaiting = ((double)totalWaiting)/count;
    }
    
    static void SJF_nonpreemptive(){
        LinkedList <Process> Sorting = new LinkedList();
        LinkedList <Process> Sorted = new LinkedList ();
        FCFS();
        
        int clock = Processes.get(0).arrive;
        int totalBurst = 0;
        for(int i=0; i<Processes.size(); i++){
            totalBurst += Processes.get(i).burst;
        }
        
        while (clock <= totalBurst){
            for(int i=0; i<Processes.size(); i++){
                if(Processes.get(i).arrive <= clock){
                    Sorting.add(Processes.get(i));
                    Processes.remove(i);
                    i--;
                }
            }
            for(int i=0; i<Sorting.size(); i++){
                for(int j=1; j<(Sorting.size()-i); j++){
                    if (Sorting.get(j-1).getBurst() > Sorting.get(j).getBurst()){
                        Collections.swap (Sorting , j, j-1 );
                    }
                }
            }
            Sorted.add(Sorting.get(0));
            clock += Sorting.get(0).burst;
            Sorting.remove(0);
        }
        Processes = Sorted;
    }
   
    static void Priority_preemptive(){
        LinkedList <Process> Sorting = new LinkedList ();
        LinkedList <Process> Sorted = new LinkedList ();
        LinkedList <Process> OriginalList = new LinkedList(OriginalProcesses);
        Processes = new LinkedList (OriginalProcesses);
        FCFS();
        
        int clock = Processes.get(0).getArrive();
        int count = Processes.size();

        int totalBurst = 0 + Processes.get(0).getArrive();
        for(int i=0; i<Processes.size(); i++){
            totalBurst += Processes.get(i).burst;
        }
        System.out.println("Clock starts at "+clock+" and ends at "+totalBurst);
        while ( clock <= totalBurst ){
            for(int i=0; i<Processes.size(); i++){
                if (Processes.get(i).getArrive() <= clock){
                    System.out.println(Processes.get(i).name);
                    String name = Processes.get(i).name;
                    int burst = Processes.get(i).burst;
                    int arrive = Processes.get(i).arrive;
                    int priority = Processes.get(i).priority;
                    Sorting.add(new Process(name,burst,arrive,priority));
                    Processes.remove(i);
                    i--;
                }
            }
            System.out.println("Sorting size before : "+Sorting.size());
            System.out.println("clock "+clock);
            for (int i=0; i<(Sorting.size()-1); i++){
                for(int j=1; j<(Sorting.size()-i); j++){
                    if (Sorting.get(j-1).priority > Sorting.get(j).priority){
                        Collections.swap (Sorting , j, j-1 );
                    }
                }
            }
            if(Sorting.size()>0){
                if ( Sorting.get(0).remainingTime <= 0){
                    Sorting.remove(0);
                }

                Sorting.get(0).remainingTime -= 1;
                String name = Sorting.get(0).name;
                int burst = Sorting.get(0).burst;
                int arrive = Sorting.get(0).arrive;
                int priority = Sorting.get(0).priority;
                int remaining = Sorting.get(0).remainingTime;
                int start = clock;
                int finish = clock+1;
//                int waiting = start - arrive;
//                totalWaiting += waiting;
                Sorted.add(new Process(name,burst,arrive,priority,remaining,start,finish));
                
                if ( Sorting.get(0).remainingTime <= 0){
                    Sorting.remove(0);
                }
            }
            clock++;
        }
        Processes = new LinkedList (Sorted);
        MergeProcesses();
        for(int i=0; i<OriginalList.size(); i++){
            for (int j=0; j<Processes.size(); j++){
                if (OriginalList.get(i).name.equals(Processes.get(j).name)){
//                    OriginalList.get(i).finish = Processes.get(j).finish;
                    OriginalList.get(i).waiting = Processes.get(j).finish - OriginalList.get(i).arrive - OriginalList.get(i).burst;
                    
                }
            }
        }
        int totalWaiting = 0;
        for(int i=0; i<OriginalList.size(); i++){
            System.out.print(OriginalList.get(i).name+" waiting "+OriginalList.get(i).waiting+"\n");
            totalWaiting += OriginalList.get(i).waiting;
        }
        System.out.println("total waiting: "+totalWaiting);
        avgWaiting = ((double)totalWaiting)/count;
    }
    
    static void Priority_nonpreemptive(){
        System.out.println("Priority nonpreemtive called");
        
        LinkedList <Process> Sorting = new LinkedList();
        LinkedList <Process> Sorted = new LinkedList ();
        FCFS();
        
        int clock = Processes.get(0).arrive;
        int totalBurst = 0;
        for(int i=0; i<Processes.size(); i++){
            totalBurst += Processes.get(i).burst;
        }
        
        while (clock <= totalBurst){
            for(int i=0; i<Processes.size(); i++){
                if(Processes.get(i).arrive <= clock){
                    Sorting.add(Processes.get(i));
                    Processes.remove(i);
                    i--;
                }
            }
            for(int i=0; i<Sorting.size(); i++){
                for(int j=1; j<(Sorting.size()-i); j++){
                    if (Sorting.get(j-1).getPriority()> Sorting.get(j).getPriority()){
                        Collections.swap (Sorting , j, j-1 );
                    }
                }
            }
            Sorted.add(Sorting.get(0));
            clock += Sorting.get(0).burst;
            Sorting.remove(0);
        }
        Processes = Sorted;
    }
    
    static void RR(){
        LinkedList <Process> Sorted = new LinkedList ();
        LinkedList <Process> Sorting = new LinkedList ();
        LinkedList <Process> OriginalList = new LinkedList (OriginalProcesses);
        Processes = new LinkedList (OriginalProcesses);
        FCFS();
        int clock = Processes.get(0).arrive;
        int totalBurst = 0 + Processes.get(0).arrive;
        
        for(int i=0; i<Processes.size(); i++){
            totalBurst += Processes.get(i).burst;
        }
        int loop = 0;
        System.out.println("clock start "+clock+" and end "+totalBurst);
        while (clock <= totalBurst){
            loop++;
            System.out.println("clock "+clock+"  loop "+loop);
            for(int i=0; i<Processes.size(); i++){
                if(Processes.get(i).arrive <= clock){
                    String name = Processes.get(i).name;
                    int burst = Processes.get(i).burst;
                    int arrive = Processes.get(i).arrive;
                    int priority = Processes.get(i).priority;
                    int remaining = Processes.get(i).remainingTime;
                    Sorting.add(new Process (name,burst,arrive,priority,remaining));
                    Processes.remove(i);
                    i--;
                }
            }
            if (Sorting.size() > 0){
                if (Sorting.get(0).remainingTime > qt){
                    Sorting.get(0).remainingTime -= qt;
                    
                    String name = Sorting.get(0).name;
                    int burst = Sorting.get(0).burst;
                    int arrive = Sorting.get(0).arrive;
                    int priority = Sorting.get(0).priority;
                    int remaining = Sorting.get(0).remainingTime;
                    int start = clock;
                    int finish = clock+qt;
                    
                    clock += qt;
                    
                    Sorted.add(new Process (name,burst,arrive,priority,remaining,start,finish));
                    Sorting.remove(0);
                    Processes.add(new Process(name,burst,arrive,priority,remaining));
                }
                else if (Sorting.get(0).remainingTime > 0){
                    int clockIncrease = Sorting.get(0).remainingTime;
                    Sorting.get(0).remainingTime = 0;
                    
                    String name = Sorting.get(0).name;
                    int burst = Sorting.get(0).burst;
                    int arrive = Sorting.get(0).arrive;
                    int priority = Sorting.get(0).priority;
                    int remaining = Sorting.get(0).remainingTime;
                    int start = clock;
                    int finish = clock+clockIncrease;
                    
                    clock += clockIncrease;
                    
                    Sorted.add(new Process (name,burst,arrive,priority,remaining,start,finish));
                    Sorting.remove(0);
                }
            }
            else clock++;
        }
        Processes = new LinkedList(Sorted);
        for(int i=0; i<OriginalList.size(); i++){
            for (int j=0; j<Processes.size(); j++){
                if (OriginalList.get(i).name.equals(Processes.get(j).name)){
//                    OriginalList.get(i).finish = Processes.get(j).finish;
                    OriginalList.get(i).waiting = Processes.get(j).finish - OriginalList.get(i).arrive - OriginalList.get(i).burst;
                    
                }
            }
        }
        int totalWaiting = 0;
        int count = OriginalProcesses.size();
        for(int i=0; i<OriginalList.size(); i++){
            System.out.print(OriginalList.get(i).name+" waiting "+OriginalList.get(i).waiting+"\n");
            totalWaiting += OriginalList.get(i).waiting;
        }
        System.out.println("total waiting: "+totalWaiting);
        avgWaiting = ((double)totalWaiting)/count;
    }
    
    static void MergeProcesses(){
        int i = 0;
        for(int j=1; j<Processes.size(); ){
            if(Processes.get(i).name.equals(Processes.get(j).name)){
                Processes.get(i).finish = Processes.get(j).finish;
                Processes.remove(j);
            }
            else{
                i++;
                j++;
            }
        }
    }
    
}
