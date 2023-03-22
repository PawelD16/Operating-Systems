package com.company.algorithms;

import com.company.Page;

import java.util.ArrayList;
import java.util.Comparator;

public class OPT extends Algorithm {
    public OPT(int frameSize, ArrayList<Page> pageReferences) {
        super(frameSize, pageReferences);
    }

    @Override
    public void simulateIfElse(Page n) {
        frames.remove(findPageThatWontBeUsedTheLongest());
        frames.add(n);
    }

    private Page findPageThatWontBeUsedTheLongest() {
        ArrayList<Page> temp = new ArrayList<>(pageReferences);

        for (int i = 0; i < temp.size(); i++) {
            int j;
            for (j = i; j < temp.size(); j++) {
                if (temp.get(j).getPageID() == temp.get(i).getPageID() && temp.get(j) != temp.get(i)) break;
            }
            temp.get(i).setLastReference(j - i);
        }
        temp.sort(Comparator.comparingInt(Page::getLastReference).reversed());

        for (Page p : temp) {
            if (frames.contains(p)) return p;
        }
        return temp.get(0);
    }

    @Override
    public String toString() {
        return "OPT pageFaults = " + this.pageFaultCounter + "\t";
    }
}
