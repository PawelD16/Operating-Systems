package com.company.algorithms;

import com.company.Page;
import com.company.Process;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class FrameAllocationAlgorithm {

    private final int MAX_FRAMES;
    private int pageFaultCounter;
    protected int timer;
    protected Process[] processes;
    protected Page[] pageCalls;
    protected ArrayList<Page> frames;

    public FrameAllocationAlgorithm(int amountOfAllFrames, Process[] processes, Page[] pageCalls) {
        this.MAX_FRAMES = amountOfAllFrames;
        this.pageFaultCounter = 0;
        this.timer = 0;
        this.pageCalls = pageCalls;
        this.processes = processes;
        this.frames = new ArrayList<>();
    }

    public int simulate() {
        for (Page n : pageCalls) {
            timer++;

            addReferenceTime();
            boolean ifFound = false;

            for (Page p : frames) {
                if (p.compareTo(n) == 0) {
                    ifFound = true;
                    p.resetNotUsedTime();
                    break;
                }
            }

            if (!ifFound) {
                n.getParentProcess().setAmountOfPageFaults(n.getParentProcess().getAmountOfPageFaults() + 1); //zwiekszanie ilosci bledow strony na proces
                n.getParentProcess().setAmountOfActivePages(n.getParentProcess().getAmountOfActivePages() + 1); //zwiekszanie ilosci aktywnych stron
                pageFaultCounter++;
                if (frames.size() < MAX_FRAMES && frameAllocation(n)) {
                    frames.add(n);
                } else {
                    simulateIfElse(n);
                }
            }
        }
        return pageFaultCounter;
    }


    private void simulateIfElse(Page n) {
        if(!frameAllocation(n)) {
            ArrayList<Page> temp = n.getParentProcess().getPages();
            temp.sort(Comparator.comparingInt(Page::getNotUsedTime).reversed());
            //frames.sort(Comparator.comparingInt(Page::getNotUsedTime).reversed());

            temp.get(0).getParentProcess().setAmountOfActivePages(n.getParentProcess().getAmountOfActivePages() - 1); //zmiejszanie ilosci aktywnych procesów
            temp.get(0).getParentProcess().doPage(temp.get(0));
            frames.remove(temp.get(0));
        }else{
            frames.sort(Comparator.comparingInt(Page::getNotUsedTime).reversed());
            Page temp = frames.remove(0);
            temp.getParentProcess().setAmountOfActivePages(n.getParentProcess().getAmountOfActivePages() - 1);
            temp.getParentProcess().doPage(temp);
        }
        frames.add(n);
    }

    private void addReferenceTime() {
        for (Page p : frames) {
            p.increaseNotUsedTime();
        }
    }

    protected abstract boolean frameAllocation(Page page);

    public int getPageFaultCounter() {
        return pageFaultCounter;
    }

    public Process[] getProcesses() {
        return processes;
    }
}
