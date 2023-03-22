package com.company.queueHandlers;

import com.company.Process;

import java.util.ArrayList;

public class PreemptiveShortestJobFirst extends QueueHandler { //wywlaszczenie polega na nie przerwaniu wykonania procesu jezeli pozostaly jego czas jest krotszy od czasu przybylego procesu
    public PreemptiveShortestJobFirst(int contextSwitchTime, int amountOfProcessesPerSimulation, int amountOfSimulations, int minTime, int maxTime) {
        super(contextSwitchTime, amountOfProcessesPerSimulation, amountOfSimulations, minTime, maxTime);
        makeRandomArrayList();
    }

    public PreemptiveShortestJobFirst(int contextSwitchTime, ArrayList<Process>[] processes2) {
        super(contextSwitchTime, processes2[0].size(), processes2.length, 0, 10);
        copyArraylist(processes2);
    }

    @Override
    public void simulate() {
        Process[] lastProcess = new Process[amountOfSimulations];
        while (!isDone()) {
            for (int i = 0; i < amountOfSimulations; i++) {

                for (int j = 0; j < amountOfSimulations; j++) fromProcessesToActive(times[i], i);

                sortActiveByRemainingCPUTime(i);

                if (activeProcesses[i].size() > 0) {
                    if (lastProcess[i] == null || lastProcess[i] != activeProcesses[i].get(0)) times[i] += contextSwitchTime;
                    times[i]++;
                    activeProcesses[i].get(0).reduceRemainingCPUTimeAndGetLeftoverTime(1);
                    ifAnyProcessPresent = true;
                    lastProcess[i] = activeProcesses[i].get(0);

                    if (activeProcesses[i].get(0).isDone()) {
                        activeProcesses[i].get(0).setWaitTime(times[i] - (activeProcesses[i].get(0).getTimeOfArrival() + activeProcesses[i].get(0).getRequiredCPUTime()));
                        doneProcesses[i].add(activeProcesses[i].get(0));
                        activeProcesses[i].remove(activeProcesses[i].get(0));
                    }
                }

                if (!ifAnyProcessPresent && !isDoneSimulation(i)) times[i]++;
                ifAnyProcessPresent = false;
            }
        }
    }
}
