package com.company.algorithms;

import com.company.Page;
import com.company.Process;

public class Proportional extends FrameAllocationAlgorithm {
    private final double proportion;

    public Proportional(int amountOfAllFrames, Process[] processes, Page[] pageCalls, double proportion) {
        super(amountOfAllFrames, processes, pageCalls);
        this.proportion = (proportion / (double) 100);
    }

    @Override
    protected boolean frameAllocation(Page page) {
        return page.getParentProcess().getPages().size() * proportion > page.getParentProcess().getAmountOfActivePages();
    }
}
