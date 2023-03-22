package com.company.strategies;

import com.company.Process;
import com.company.Processor;

import java.util.ArrayList;

public class ThirdStrategy extends SecondStrategy {
    public ThirdStrategy(Processor[] processors, ArrayList<Process> processes) {
        super(processors, processes);
    }

    @Override
    public void simulation() {
        for (Process process : processes) {
            basicSimulation(process);

            redistribute();
        }

        while (!allProcessorsDone()) {
            redistribute();
            tickAllProcessors();
        }
    }

    private void redistribute() {
        for (Processor processor : processors) {
            if (processor.isUnderMin()) {
                for (Processor processor1 : processors) {

                    if (!processor1.isUnderMax()) {
                        while (processor != processor1 && processor.getCurrentLoad() < processor1.getCurrentLoad()) {
                            processor.addProcess(processor1.getLastProcess()); //dodajemy, az sie wyrownaja (mniej wiecej)
                            processor1.incrementMigrations();
                            processor1.incrementInquiries();
                            processor1.tick();
                        }
                        break;
                    }
                }
            }
        }
    }
}
