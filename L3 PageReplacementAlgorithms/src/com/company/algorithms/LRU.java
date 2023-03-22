package com.company.algorithms;

import com.company.Page;

import java.util.ArrayList;
import java.util.Comparator;

public class LRU extends Algorithm {
    public LRU(int frameSize, ArrayList<Page> pageReferences) {
        super(frameSize, pageReferences);
    }

    @Override
    public int simulate() {
        for (Page n : pageReferences) {
            addReferenceTime();
            boolean ifFound = false;

            for (Page p : frames) {
                if (p.getPageID() == n.getPageID()) {
                    ifFound = true;
                    p.setLastReference(0);
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

    @Override
    public void simulateIfElse(Page n) {
        frames.sort(Comparator.comparingInt(Page::getLastReference).reversed());
        frames.remove(0);
        frames.add(n);
    }

    protected void addReferenceTime() {
        for (Page p : frames) {
            p.setLastReference(p.getLastReference() + 1);
        }
    }

    @Override
    public String toString() {
        return "LRU pageFaults = " + pageFaultCounter + "\t";
    }
}
