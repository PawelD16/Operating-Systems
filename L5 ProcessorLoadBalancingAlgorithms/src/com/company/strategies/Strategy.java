package com.company.strategies;

import com.company.Process;
import com.company.Processor;

import java.util.ArrayList;
import java.util.Random;

public abstract class Strategy {
    protected final Processor[] processors;
    protected final ArrayList<Process> processes;


    public Strategy(Processor[] processors, ArrayList<Process> processes) {
        this.processes = processes;
        this.processors = processors;
    }

    public abstract void simulation();

    protected int getRandomProcessorNumber() {
        return new Random().nextInt(processors.length);
    }

    protected void tickAllProcessors() {
        for (Processor processor : processors) {
            processor.tick();
        }
    }

    protected boolean allProcessorsDone(){
        for(Processor processor:processors){
            if(!processor.hasEmptyQueue()) return false;
        }
        return true;
    }

    protected void printInfo() {
        double averageLoad = 0;
        double averageDeviationOfLoad = 0;
        int allMigrations = 0, allInquires = 0;
        for (int i = 0; i < processors.length; i++) {
            averageLoad += processors[i].getAverageLoad();
            averageDeviationOfLoad += processors[i].getStandardDeviationOfLoad();
            allMigrations += processors[i].getAmountOfMigrations();
            allInquires += processors[i].getAmountOfLoadInquiries();
        }
        System.out.println("\nAverage load: " + (averageLoad / (double) processors.length)*100 + "%\nAverage deviation of load: " + (averageDeviationOfLoad / (double) processors.length)*100 + "%\nAmount of migrations: " + allMigrations + "\nAmount of inquires: " + allInquires);
    }
}
