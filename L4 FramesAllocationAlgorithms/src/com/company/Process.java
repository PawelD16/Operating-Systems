package com.company;

import java.util.ArrayList;

public class Process {
    private ArrayList<Page> pages;
    private ArrayList<Page> finishedPages;
    private static int globalProcessID;
    private final int thisProcessID;
    private int amountOfActivePages;
    private int amountOfPageFaults;
    private int allProcessPageFaults;

    public Process(ArrayList<Page> pages) {
        this.thisProcessID = ++globalProcessID;
        this.amountOfActivePages = 0;
        this.amountOfPageFaults = 0;
        this.allProcessPageFaults = 0;
        this.pages = pages;
        this.finishedPages = new ArrayList<>();
    }

    public ArrayList<Page> getFinishedPages() {
        return finishedPages;
    }


    public int getAllProcessPageFaults() {
        return allProcessPageFaults;
    }

    public int getAmountOfPageFaults() {
        return amountOfPageFaults;
    }


    public void setAmountOfPageFaults(int amountOfPageFaults) {
        this.allProcessPageFaults = Math.max(amountOfPageFaults, this.amountOfPageFaults);
        this.amountOfPageFaults = amountOfPageFaults;

    }

    public int getAmountOfActivePages() {
        return amountOfActivePages;
    }

    public void setAmountOfActivePages(int amountOfActivePages) {
        this.amountOfActivePages = amountOfActivePages;
    }

    public ArrayList<Page> getPages() {
        return pages;
    }

    public void setPages(ArrayList<Page> pages) {
        this.pages = pages;
    }

    public int getThisProcessID() {
        return thisProcessID;
    }

    public Page getNextPage(Page n) {
        int index = pages.indexOf(n);
        if (index < pages.size() - 1) return pages.get(index + 1);
        return null;
    }

    public void doPage(Page page) {
        if (page != null && pages.contains(page)) finishedPages.add(page);
    }

    public boolean isFinished() {
        return pages.size() == finishedPages.size();
    }

    public Page getPageIfContains(Page page) {
        for (Page tempPage : pages) {
            if (page.equals(tempPage)) return tempPage;
        }
        return null;
    }

    @Override
    public String toString() {
        return "ProcessID: " + this.thisProcessID + " page faults: " + this.allProcessPageFaults;
    }
}
