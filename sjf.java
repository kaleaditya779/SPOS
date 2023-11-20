
// Scheduling algorithm = FCFS (preemptive)

import java.util.*;

class Proce {
    int pid;
    int at;
    int bt;

    public Proce(int id, int aat, int bbt){
        pid = id;
        at = aat;
        bt = bbt;
    }
}

public class sjf {

    static void wtFunc(Proce pr[], int n, int[] wt, int[] rem, int[] ct, int[] tat){

        int timeline = 0; // timeline (time period)
        int complete = 0; // nos of processess whoes execution is completed.
        int shortest_arrived = 0; // index of shortest arrived and accepted.
        int minn = Integer.MAX_VALUE; // min value or remTime
        boolean is_process_found = false; // to check whether we are getting that process, or else we need to proceed the timeline with empty CPU.

        int avgWT = 0;
        int avgTAT = 0;

        while (complete != n) {

            for (int i=0; i<n; i++){
                if ((pr[i].at <= timeline) && (rem[i] < minn) && rem[i] > 0) {
                    minn = rem[i]; // Assigning the remTime of this process to min rem time
                    shortest_arrived = i; // saved the index of this process
                    is_process_found = true;
                }
            }

            if (is_process_found == false){
                timeline++; // No process with required criteria is found, so increment timeline
                continue; // continue from next loop. (ecape/skip the remaining body of this iterations) 
            }

            rem[shortest_arrived]--; // Decremented the remTime for the process we took for execution for 1 timeline.
            minn = rem[shortest_arrived];

            // Means process is completely executed.
            if (minn == 0) {
                complete++; // Increment complete
                minn = Integer.MAX_VALUE; //  again placing the minn to max int.
                is_process_found = false; // making this flag false, for next iteration.

                // CT, WT of that process =>
                ct[shortest_arrived] = timeline + 1; // CT
                wt[shortest_arrived] = ct[shortest_arrived] - pr[shortest_arrived].bt - pr[shortest_arrived].at; // WT

                if (wt[shortest_arrived] < 0){
                    wt[shortest_arrived] = 0;
                }
            }

            // Increment timeline for next iteration of while loop
            timeline++;
        }

        System.out.println("Pid\tArrival\tBurst\tcompletion\tturn\twaiting");
        // TAT of all the processess =>
        for (int i=0; i<n; i++){
            tat[i] = ct[i] - pr[i].at;

            avgTAT += tat[i];
            avgWT += wt[i];

            System.out.println(pr[i].pid+"\t"+pr[i].at+"\t"+pr[i].bt+"\t"+ct[i]+"\t"+tat[i]+"\t"+wt[i]);
        }
        System.out.println("The average waiting time of all procesess: "+ (float) avgWT/n);
        System.out.println("The average turn around time of all procesess: "+ (float) avgTAT/n);
        
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("No, of processes");
        int n = sc.nextInt();

        Proce proc[] = new Proce[n];
        int wt[] = new int[n];
        int tat[] = new int[n];
        int ct[] = new int[n];

        int[] remTime = new int[n];
    
        System.out.println("ENter the process ids, at, bt respectively for each process");
        for (int i=0; i<n; i++) {
            int pid = sc.nextInt();
            int at = sc.nextInt();
            int bt = sc.nextInt();

            proc[i] = new Proce(pid, at, bt);
            remTime[i] = proc[i].bt; // Initialized the remainig time as burst time.
        }
        wtFunc(proc, n, wt, remTime, ct, tat);
        sc.close();
    }
}