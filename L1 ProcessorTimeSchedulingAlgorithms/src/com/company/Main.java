package com.company;

import com.company.queueHandlers.FirstInFirstOut;
import com.company.queueHandlers.NonPreemptiveShortestJobFirst;
import com.company.queueHandlers.PreemptiveShortestJobFirst;
import com.company.queueHandlers.RoundRobin;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {


        ArrayList<Process>[] processes = new ArrayList[1];
        processes[0] = new ArrayList<>();
        processes[0].add(new Process(5, 0));
        processes[0].add(new Process(3, 1));
        processes[0].add(new Process(1, 2));
        processes[0].add(new Process(2, 3));
        processes[0].add(new Process(3, 4));



        FirstInFirstOut firstInFirstOut = new FirstInFirstOut(0, 300, 100, 2, 300);
        NonPreemptiveShortestJobFirst nonPreemptiveShortestJobFirst = new NonPreemptiveShortestJobFirst(0, firstInFirstOut.getArrayListOfProcesses());
        PreemptiveShortestJobFirst preemptiveShortestJobFirst = new PreemptiveShortestJobFirst(0, firstInFirstOut.getArrayListOfProcesses());
        RoundRobin roundRobin = new RoundRobin(2, 0, firstInFirstOut.getArrayListOfProcesses());

        firstInFirstOut.simulate();
        nonPreemptiveShortestJobFirst.simulate();
        preemptiveShortestJobFirst.simulate();
        roundRobin.simulate();

        //firstInFirstOut.allDoneProcessesInfo();
        //nonPreemptiveShortestJobFirst.allDoneProcessesInfo();
        //preemptiveShortestJobFirst.allDoneProcessesInfo();
        roundRobin.allDoneProcessesInfo();


        System.out.println("Average wait time with FIFO is " + firstInFirstOut.getAverageWaitTime());
        System.out.println("Average wait time with non preemptive SJF is " + nonPreemptiveShortestJobFirst.getAverageWaitTime());
        System.out.println("Average wait time with preemptive SJF is " + preemptiveShortestJobFirst.getAverageWaitTime());
        System.out.println("Average wait time with RR is " + roundRobin.getAverageWaitTime());
    }
}
