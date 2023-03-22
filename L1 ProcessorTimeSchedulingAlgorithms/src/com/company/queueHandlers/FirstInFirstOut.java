package com.company.queueHandlers;

import com.company.Process;

import java.util.ArrayList;

public class FirstInFirstOut extends QueueHandler {
    public FirstInFirstOut(int contextSwitchTime, int amountOfProcessesPerSimulation, int amountOfSimulations, int minTime, int maxTime) {
        super(contextSwitchTime, amountOfProcessesPerSimulation, amountOfSimulations, minTime, maxTime);
        makeRandomArrayList();
    }

    public FirstInFirstOut(int contextSwitchTime, ArrayList<Process>[] processes2) {
        super(contextSwitchTime, processes2[0].size(), processes2.length, 0, 10);
        copyArraylist(processes2);
    }

    @Override
    public void simulate() {
        while (!isDone()) {
            for (int i = 0; i < amountOfSimulations; i++) {
                for (int j = 0; j < amountOfSimulations; j++) fromProcessesToActive(times[i], i);
                doActiveProcesses(i);
            }
        }
    }
}
