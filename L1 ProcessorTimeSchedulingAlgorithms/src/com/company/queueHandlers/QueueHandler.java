package com.company.queueHandlers;

import com.company.Process;
import com.company.ProcessesArrayListCreator;

import java.util.ArrayList;
import java.util.Random;

public abstract class QueueHandler implements QueueHandlerInterface {

    protected ArrayList<Process>[] processes;
    protected ArrayList<Process>[] doneProcesses;
    protected ArrayList<Process>[] activeProcesses;

    protected int contextSwitchTime;
    protected int amountOfSimulations;
    protected int amountOfProcessesPerSimulation;
    protected int minTime, maxTime;
    protected Random generator;
    protected int[] times;
    protected boolean ifAnyProcessPresent;

    @SuppressWarnings("unchecked")
    public QueueHandler(int contextSwitchTime, int amountOfProcessesPerSimulation, int amountOfSimulations, int minTime, int maxTime) {

        this.amountOfSimulations = amountOfSimulations;
        this.amountOfProcessesPerSimulation = amountOfProcessesPerSimulation;
        this.contextSwitchTime = contextSwitchTime;
        this.maxTime = maxTime;
        this.minTime = minTime;
        this.generator = new Random();
        this.times = new int[amountOfSimulations];
        this.processes = new ArrayList[amountOfSimulations];
        this.doneProcesses = new ArrayList[amountOfSimulations];
        this.activeProcesses = new ArrayList[amountOfSimulations];
        this.ifAnyProcessPresent = false;

        for (int i = 0; i < amountOfSimulations; i++) {
            activeProcesses[i] = new ArrayList<>();
            doneProcesses[i] = new ArrayList<>();
        }
    }

    @Override
    public double getAverageWaitTime() {
        double waitTime = 0, amount = 0;
        if (isDone()) {
            for (ArrayList<Process> doneProcess : doneProcesses) {
                for (Process process : doneProcess) {
                    waitTime += process.getWaitTime();
                    amount++;
                }
            }
            return waitTime / amount;
        }
        return 0;
    }

    @Override
    public boolean isDone() {

        for (int i = 0; i < amountOfSimulations; i++) {
            if (!isDoneSimulation(i)) return false;
        }
        return true;
    }


    public void allDoneProcessesInfo() {
        for (int i = 0; i < doneProcesses.length; i++) {
            System.out.println("Simulation number " + i);
            for (int j = 0; j < doneProcesses[i].size(); j++)
                System.out.println(j + ". " + doneProcesses[i].get(j));
        }
    }

    @Override
    public ArrayList<Process>[] getArrayListOfProcesses() {
        return processes;
    }

    protected boolean isDoneSimulation(int i) {
        return processes[i].size() <= 0 && activeProcesses[i].size() <= 0;
    }

    protected void fromProcessesToActive(int time, int i) {

        for (int j = 0; j < processes[i].size(); j++) {
            if (processes[i].get(j).getTimeOfArrival() <= time) {
                activeProcesses[i].add(processes[i].get(j));
                processes[i].remove(processes[i].get(j));
                j--;
            }
        }
    }

    protected void doActiveProcesses(int i) {
        if (activeProcesses[i].size() > 0) {
            activeProcesses[i].get(0).setWaitTime(times[i] - activeProcesses[i].get(0).getTimeOfArrival());
            times[i] += activeProcesses[i].get(0).getRequiredCPUTime() + contextSwitchTime;
            activeProcesses[i].get(0).reduceRemainingCPUTimeAndGetLeftoverTime(activeProcesses[i].get(0).getRequiredCPUTime());
            ifAnyProcessPresent = true;
            doneProcesses[i].add(activeProcesses[i].get(0));
            activeProcesses[i].remove(activeProcesses[i].get(0));
        }
        if (!ifAnyProcessPresent && !isDoneSimulation(i)) times[i]++;
        ifAnyProcessPresent = false;
    }

    protected void makeRandomArrayList() {
        for (int i = 0; i < amountOfSimulations; i++)
            this.processes[i] = ProcessesArrayListCreator.createArrayListOfProcesses(amountOfProcessesPerSimulation, minTime, maxTime);
    }

    protected void copyArraylist(ArrayList<Process>[] processes2) {
        for (int i = 0; i < processes2.length; i++) {
            processes[i] = new ArrayList<>();
            for (Process process : processes2[i]) {
                this.processes[i].add(new Process(process.getRemainingCPUTime(), process.getTimeOfArrival()));
            }
        }
    }

    protected void sortActiveByRemainingCPUTime(int i) {
        activeProcesses[i].sort((o1, o2) ->
        {
            if (o1.isDone() || o2.isDone()) return 0;
            return Integer.compare(o1.getRemainingCPUTime(), o2.getRemainingCPUTime());
        });
    }
}
