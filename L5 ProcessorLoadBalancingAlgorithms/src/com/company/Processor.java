package com.company;

import java.util.ArrayList;

public class Processor {
    private static int globalProcessorID;
    private final int processorID;
    private final int allAvailableResources;
    private ArrayList<Process> processes;
    private final double minLoad, maxLoad;
    private ArrayList<Double> loadPerClock;
    private int amountOfLoadInquiries, amountOfMigrations;

    public Processor(int allAvailableResources, double minLoad, double maxLoad) {
        this.processorID = ++globalProcessorID;
        this.amountOfLoadInquiries = 0;
        this.amountOfMigrations = 0;
        this.minLoad = (minLoad % 100)/100;
        this.maxLoad = (maxLoad % 100)/100;
        this.allAvailableResources = allAvailableResources;
        this.processes = new ArrayList<>();
        this.loadPerClock = new ArrayList<>();
    }

    public Double getCurrentLoad() {
        int temp = 0;
        for (Process process : processes) {
            temp += process.getLeftClocks();
        }

        return ((double) temp / (double) allAvailableResources);
    }

    public boolean addProcess(Process process) {
        if (isUnderMax() && getCurrentLoad() < 100) {
            processes.add(process);
            return true;
        }
        return false;
    }

    public boolean addProcessOverTheMax(Process process) {
        if (getCurrentLoad() < 100) {
            processes.add(process);
            return true;
        }
        return false;
    }

    public void incrementInquiries() {
        this.amountOfLoadInquiries++;
    }

    public void incrementMigrations() {
        this.amountOfMigrations++;
    }

    public boolean isUnderMax() {
        return getCurrentLoad() < maxLoad;
    }

    public boolean isUnderMin() {
        return getCurrentLoad() < minLoad;
    }

    public void tick() {
        if (processes.size() > 0 && processes.get(0).doTickAndIsDone()) processes.remove(0);
        loadPerClock.add(this.getCurrentLoad());
    }

    public int getAmountOfLoadInquiries() {
        return amountOfLoadInquiries;
    }

    public int getAmountOfMigrations() {
        return amountOfMigrations;
    }

    public Process getLastProcess() {
        return processes.remove(processes.size() - 1);
    }

    public double getAverageLoad() {
        double temp = 0;
        for (Double d : loadPerClock) {
            temp += d;
        }

        return temp / (double) loadPerClock.size();
    }

    public double getStandardDeviationOfLoad() {
        double mean = getAverageLoad();
        double temp = 0;
        for (Double d : loadPerClock) {
            temp += Math.pow((d - mean), 2);
        }

        return Math.sqrt(temp / (double) loadPerClock.size());
    }

    public boolean hasEmptyQueue() {
        return processes.size() <= 0;
    }

    @Override
    public String toString() {
        return this.processorID + " average load: " + getAverageLoad() + " deviation: +/-" + getStandardDeviationOfLoad() + " amount of migrations: " + amountOfMigrations + " load enquiry: " + amountOfLoadInquiries;
    }

}
