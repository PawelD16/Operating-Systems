package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Generator {

    public static Processor[] generateProcessors(int amountOfProcessors, int allAvailableResourcesPerProcessor, int minLoad, int maxLoad) {
        Processor[] processors = new Processor[amountOfProcessors];
        for (int i = 0; i < amountOfProcessors; i++) {
            processors[i] = new Processor(allAvailableResourcesPerProcessor, minLoad, maxLoad);
        }
        return processors;
    }

    public static ArrayList<Process> generateProcesses(int amountOfProcesses, int maxResourcesPerProcess) {
        ArrayList<Process> processes = new ArrayList<>();
        for (int i = 0; i < amountOfProcesses; i++) {
            processes.add(new Process(new Random().nextInt(maxResourcesPerProcess)));
        }

        return processes;
    }
}
