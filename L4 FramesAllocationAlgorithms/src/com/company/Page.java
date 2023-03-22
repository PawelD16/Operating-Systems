package com.company;

public class Page implements Comparable<Page> {
    private final int pageID;
    private int lastUsedSince;
    private final Process parentProcess;

    public Page(int pageID, Process parentProcess) {
        this.pageID = pageID;
        this.parentProcess = parentProcess;
        this.lastUsedSince = 0;
    }

    public void increaseNotUsedTime() {
        this.lastUsedSince++;
    }

    public void resetNotUsedTime() {
        this.lastUsedSince = 0;
    }

    public int getNotUsedTime() {
        return lastUsedSince;
    }

    public int getPageID() {
        return pageID;
    }

    public Process getParentProcess() {
        return parentProcess;
    }


    @Override
    public int compareTo(Page o) {
        return (this.pageID == o.pageID && this.parentProcess.equals(o.getParentProcess())) ? 0 : 1;
    }

    @Override
    public String toString(){
        return "PageID: " + this.pageID;
    }
}
