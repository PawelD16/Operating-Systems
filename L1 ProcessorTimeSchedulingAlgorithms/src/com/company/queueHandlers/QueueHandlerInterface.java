package com.company.queueHandlers;

import com.company.Process;

import java.util.ArrayList;

public interface QueueHandlerInterface {

    double getAverageWaitTime();
    void simulate();
    void allDoneProcessesInfo();
    boolean isDone();
    ArrayList<Process>[] getArrayListOfProcesses();
}
