package com.company;

import com.company.strategies.FirstStrategy;
import com.company.strategies.SecondStrategy;
import com.company.strategies.ThirdStrategy;

public class Main {

    public static void main(String[] args) {
        int p = 90; //maxLoad
        int r = 20; //minLoad
        int z = 10; //maxInquires
        int N = 100; //amountOfProcessors
        int allResourcesPerProcessor = 1000;
        new FirstStrategy(Generator.generateProcessors(N, allResourcesPerProcessor, r, p), Generator.generateProcesses(N * N, (int) ((double) allResourcesPerProcessor * 5 / 100.0)), z);
        new SecondStrategy(Generator.generateProcessors(N, allResourcesPerProcessor, r, p), Generator.generateProcesses(N * N, (int) ((double) allResourcesPerProcessor * 5 / 100.0)));
        new ThirdStrategy(Generator.generateProcessors(N, allResourcesPerProcessor, r, p), Generator.generateProcesses(N * N, (int) ((double) allResourcesPerProcessor * 5 / 100.0)));
    }
}
