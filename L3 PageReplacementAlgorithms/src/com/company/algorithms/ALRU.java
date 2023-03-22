package com.company.algorithms;

import com.company.Page;

import java.util.ArrayList;
import java.util.Comparator;

public class ALRU extends LRU {
    public ALRU(int frameSize, ArrayList<Page> pageReferences) {
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
                    p.setApproximationBit(true);
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

        frames.sort(Comparator.comparingInt(Page::getLastReference));

        do {
            for (int l = 0; l < frames.size(); l++) {
                Page p = frames.get(l);
                if (!p.isApproximationBit()) {
                    frames.remove(p);
                    frames.add(n);
                    return;
                } else {
                    p.setApproximationBit(false);
                }
            }
        } while (true);
    }

    @Override
    public String toString() {
        return "ALRU pageFaults = " + pageFaultCounter + "\t";
    }
}
