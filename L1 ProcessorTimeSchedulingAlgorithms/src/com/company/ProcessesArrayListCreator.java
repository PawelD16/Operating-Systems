package com.company;

import java.util.ArrayList;

public abstract class ProcessesArrayListCreator {

    public static ArrayList<Process> createArrayListOfProcesses(int amount, int minTime, int maxTime) {

        ArrayList<Process> processes = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            processes.add(new Process(minTime, maxTime, 0));
        }

        processes.sort((o1, o2) -> {
            if(o1.isDone() || o2.isDone()) return 0;
            return Integer.compare(o1.getTimeOfArrival(), o2.getTimeOfArrival());
        });

        return processes;
    }
}
