package com.company.algorithms;

import com.company.Page;
import com.company.Process;

public class ErrorRateControl extends FrameAllocationAlgorithm {
    private final double maxErrorRate;

    public ErrorRateControl(int amountOfAllFrames, Process[] processes, Page[] pageCalls, double maxErrorRate) {
        super(amountOfAllFrames, processes, pageCalls);
        this.maxErrorRate = (maxErrorRate / 100.0);

    }

    @Override
    protected boolean frameAllocation(Page page) {
        double temp = (double) page.getParentProcess().getAmountOfActivePages() / (double) page.getParentProcess().getAmountOfPageFaults();

        while (temp > 1) {
            temp = temp * 0.5;
        }

        return temp < maxErrorRate;
    }
}
