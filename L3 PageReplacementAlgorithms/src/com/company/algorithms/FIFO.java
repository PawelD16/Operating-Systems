package com.company.algorithms;

import com.company.Page;

import java.util.ArrayList;

public class FIFO extends Algorithm {
    public FIFO(int frameSize, ArrayList<Page> pageReferences) {
        super(frameSize, pageReferences);
    }

    @Override
    public void simulateIfElse(Page n) {
        frames.remove(0);
        frames.add(n);
    }

    @Override
    public String toString() {
        return "FIFO pageFaults = " + this.pageFaultCounter + "\t";
    }
}
