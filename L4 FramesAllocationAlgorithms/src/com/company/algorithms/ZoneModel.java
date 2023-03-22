package com.company.algorithms;

import com.company.Page;
import com.company.Process;

import java.util.ArrayList;
import java.util.List;

public class ZoneModel extends FrameAllocationAlgorithm {
    private final int zoneSize;

    public ZoneModel(int amountOfAllFrames, Process[] processes, Page[] pageCalls, int zoneSize) {
        super(amountOfAllFrames, processes, pageCalls);
        this.zoneSize = zoneSize;
    }

    @Override
    protected boolean frameAllocation(Page page) {
        List<Page> temp = page.getParentProcess().getFinishedPages();
        if(temp.size() > 0) temp = temp.subList(Math.max((temp.size() - 1 - zoneSize), 0), temp.size() - 1);
        Page[] tempArray = new Page[temp.size()];
        tempArray = temp.toArray(tempArray);

        return this.countDifferentPages(tempArray) < page.getParentProcess().getAmountOfActivePages();
    }

    private int countDifferentPages(Page[] pageArray) {
        int numOfDifferentPages = 0;

        ArrayList<Page> diffNum = new ArrayList<>();

        for (Page page : pageArray) {
            if (!diffNum.contains(page)) {
                diffNum.add(page);
            }
        }

        if (diffNum.size() > 1) numOfDifferentPages = diffNum.size();


        return numOfDifferentPages;
    }
}
