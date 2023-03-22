package com.company.algorithms;

import com.company.Page;
import com.company.Process;

public class Equal extends FrameAllocationAlgorithm {
    private final int maxActiveFramesPerProcess;

    public Equal(int amountOfAllFrames, Process[] processes, Page[] pageCalls, int maxActiveFramesPerProcess) {
        super(amountOfAllFrames, processes, pageCalls);
        this.maxActiveFramesPerProcess = maxActiveFramesPerProcess;
    }

    @Override
    protected boolean frameAllocation(Page page) {
        return page.getParentProcess().getAmountOfActivePages() < maxActiveFramesPerProcess;
    }
}
