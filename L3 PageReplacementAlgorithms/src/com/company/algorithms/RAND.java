package com.company.algorithms;

import com.company.Page;
import com.company.algorithms.Algorithm;

import java.util.ArrayList;
import java.util.Random;

public class RAND extends Algorithm {
    public RAND(int frameSize, ArrayList<Page> pageReferences) {
        super(frameSize, pageReferences);
    }

    @Override
    public void simulateIfElse(Page n) {
        frames.set(new Random().nextInt(frameSize), n);
    }

    @Override
    public String toString() {
        return "RAND pageFaults = " + this.pageFaultCounter + "\t";
    }
}
