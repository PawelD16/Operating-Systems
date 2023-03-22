package com.company.strategies;

import com.company.Process;
import com.company.Processor;

import java.util.ArrayList;

public class FirstStrategy extends Strategy {
    private final int maxInquires;

    public FirstStrategy(Processor[] processors, ArrayList<Process> processes, int maxInquires) {
        super(processors, processes);
        this.maxInquires = maxInquires;
        simulation();
        printInfo();
    }

    public void simulation() {
        for (Process process : processes) {
            int xProcessor = getRandomProcessorNumber();
            boolean ifProcessAdded = false;

            for (int k = 0; k < maxInquires && !ifProcessAdded; k++) {
                int yProcessor = getRandomProcessorNumber();
                processors[yProcessor].incrementInquiries();
                if (processors[yProcessor].addProcess(process)) {
                    ifProcessAdded = true;
                    processors[yProcessor].incrementMigrations();
                }
                processors[yProcessor].tick();
            }
            if (!ifProcessAdded) {
                while (!processors[xProcessor].addProcessOverTheMax(process)) processors[xProcessor].tick();
            }
        }

        while (!allProcessorsDone()) {
            tickAllProcessors();
        }
    }
}
