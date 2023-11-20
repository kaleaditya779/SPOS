
// Scheduling algorithm = FCFS (Non preemptive)

import java.util.*;

public class Fcfs {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes: ");
        int n = sc.nextInt();

        int[] pids = new int[n]; // Process ids
        int[] AT = new int[n]; // arrival time
        int[] BT = new int[n]; // Burst time
        int[] WT = new int[n]; // Waiting time
        int[] RT = new int[n]; // Responsr time
        int[] TAT = new int[n]; // Turn around time
        int[] CT = new int[n]; // Completion time
        int avgWT = 0;
        int avgTAT = 0;

        System.out.println("Input the times of processes");
        System.out.println("Enter the ids of processes: ");
        for (int i = 0; i < n; i++) {
            System.out.println(String.format("%s : ", i + 1));
            int temp = sc.nextInt();
            pids[i] = temp;
        }

        System.out.println("Enter the arrival times (AT) of processes: ");
        for (int i = 0; i < n; i++) {
            System.out.println(String.format("%s : ", i + 1));
            int temp = sc.nextInt();
            AT[i] = temp;
        }

        System.out.println("Enter the burst times (BT) of processes: ");
        for (int i = 0; i < n; i++) {
            System.out.println(String.format("%s : ", i + 1));
            int temp = sc.nextInt();
            BT[i] = temp;
        }

        System.out.println("Which algorithm to use ?\n1) FCFS\n2) Round Robin\n:");
        int response = sc.nextInt();

        if (response == 1) // FCFS
        {
            /*
             * 1. sort the pids, AT, BT according to arrival time. (Can use any sorting
             * algorithm)
             * 2. find completion time (CT) of rach process.
             * 3. find TAT. (TAT = CT - AT)
             * 4. find WT. (Same logic as CT)
             * 5. find RT. (WT + AT)
             */

            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    if (AT[j] < AT[i]) {
                        int temp = AT[j];
                        AT[j] = AT[i];
                        AT[i] = temp;

                        temp = BT[j];
                        BT[j] = BT[i];
                        BT[i] = temp;

                        temp = pids[j];
                        pids[j] = pids[i];
                        pids[i] = temp;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                if (i == 0) {
                    CT[i] = AT[i] + BT[i];
                } else {
                    if (AT[i] > CT[i - 1]) {
                        CT[i] = AT[i] + BT[i];
                    } else {
                        CT[i] = CT[i - 1] + BT[i];
                    }
                }
                TAT[i] = CT[i] - AT[i];
                WT[i] = TAT[i] - BT[i];
                RT[i] = AT[i] + WT[i];
                avgTAT = avgTAT + TAT[i];
                avgWT = avgWT + WT[i];
            }
            System.out.println("Pid Arrival Burst completion turn waiting response");
            for (int i=0; i<n; i++)
            {
                System.out.println(pids[i]+"\t"+AT[i]+"\t"+BT[i]+"\t"+CT[i]+"\t"+TAT[i]+"\t"+WT[i]+"\t"+RT[i]);
            }
            System.out.println("The average waiting time: "+ avgWT/n);
            System.out.println("The average turn aroud time: "+ avgTAT/n);

        }
        else{
            System.out.println("Wrong input");
        }
        sc.close();

    }

}
