package com.company.queueHandlers;

import com.company.Process;

import java.util.ArrayList;

public class RoundRobin extends QueueHandler {
    private final int quantum; //quantum is the CPU time slice

    public RoundRobin(int quanta, int contextSwitchTime, int amountOfProcessesPerSimulation, int amountOfSimulations, int minTime, int maxTime) {
        super(contextSwitchTime, amountOfProcessesPerSimulation, amountOfSimulations, minTime, maxTime);
        this.quantum = quanta;
        makeRandomArrayList();
    }

    public RoundRobin(int quantum, int contextSwitchTime, ArrayList<Process>[] processes2) {
        super(contextSwitchTime, processes2[0].size(), processes2.length, 0, 10);
        this.quantum = quantum;
        copyArraylist(processes2);
    }

    @Override
    public void simulate() {
        while (!isDone()) {

            for (int i = 0; i < amountOfSimulations; i++) {
                for (int j = 0; j < amountOfSimulations; j++) fromProcessesToActive(times[i], i);
                for (int l = 0; l < activeProcesses[i].size(); l++) {

                    if (!activeProcesses[i].get(l).isDone()) {
                        times[i] -= activeProcesses[i].get(l).reduceRemainingCPUTimeAndGetLeftoverTime(quantum);
                        times[i] += contextSwitchTime + quantum;
                        ifAnyProcessPresent = true;

                        for (int j = 0; j < amountOfSimulations; j++) fromProcessesToActive(times[i], i);
                        if (activeProcesses[i].get(l).isDone()) {
                            activeProcesses[i].get(l).setWaitTime(times[i] - (activeProcesses[i].get(l).getTimeOfArrival() + activeProcesses[i].get(l).getRequiredCPUTime()));
                            doneProcesses[i].add(activeProcesses[i].get(l));
                            activeProcesses[i].remove(activeProcesses[i].get(l));
                            l--;
                        }
                    }
                }

                if (!ifAnyProcessPresent) times[i]++;
                ifAnyProcessPresent = false;

            }
        }
    }
}
