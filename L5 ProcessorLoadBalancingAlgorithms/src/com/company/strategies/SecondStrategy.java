package com.company.strategies;

import com.company.Process;
import com.company.Processor;

import java.util.ArrayList;

public class SecondStrategy extends Strategy {
    public SecondStrategy(Processor[] processors, ArrayList<Process> processes) {
        super(processors, processes);
        simulation();
        printInfo();
    }

    @Override
    public void simulation() {
        for (Process process : processes) {
            basicSimulation(process);
        }

        while(!allProcessorsDone()){
            tickAllProcessors();
        }

    }

    protected void basicSimulation(Process process) {
        int xProcessor = getRandomProcessorNumber();
        boolean ifProcessAdded = false;

        if (!processors[xProcessor].addProcess(process)) {

            while (!ifProcessAdded) {
                int yProcessor = getRandomProcessorNumber();
                processors[yProcessor].incrementInquiries();
                if (processors[yProcessor].addProcess(process)) {
                    ifProcessAdded = true;
                    processors[yProcessor].tick();
                    processors[yProcessor].incrementMigrations();
                }
                processors[xProcessor].tick();
            }
        }
        processors[xProcessor].tick();
    }
}
