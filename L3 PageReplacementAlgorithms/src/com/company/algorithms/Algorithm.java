package com.company.algorithms;

import com.company.Page;

import java.util.ArrayList;

public abstract class Algorithm {
    protected final int frameSize;
    protected int pageFaultCounter;
    protected ArrayList<Page> pageReferences, frames;

    public Algorithm(int frameSize, ArrayList<Page> pageReferences) {
        this.frameSize = frameSize;
        this.pageReferences = new ArrayList<>(pageReferences);
        this.pageFaultCounter = 0;
        this.frames = new ArrayList<>();
    }

    public int simulate() {

        for (Page n : pageReferences) {
            boolean ifFound = false;

            for (Page p : frames) {
                if (p.getPageID() == n.getPageID()) {
                    ifFound = true;
                    break;
                }
            }

            if (!ifFound) {
                pageFaultCounter++;
                if (frames.size() < frameSize) {
                    frames.add(n);
                } else {
                    simulateIfElse(n);
                }
            }
        }
        return pageFaultCounter;
    }

    public abstract void simulateIfElse(Page n);

    @Override
    public abstract String toString();
}

