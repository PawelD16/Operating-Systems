package com.company;

public class Page {

    private int PageID;
    private boolean approximationBit;
    private int lastReference;

    public Page(int PageID, boolean approximationBit, int lastReference) {
        this.PageID = PageID;
        this.approximationBit = approximationBit;
        this.lastReference = lastReference;
    }

    public Page(Page p) {
        this.PageID = p.getPageID();
        this.approximationBit = p.isApproximationBit();
        this.lastReference = p.getLastReference();
    }


    public int getPageID() {
        return PageID;
    }

    public boolean isApproximationBit() {
        return approximationBit;
    }

    public void setApproximationBit(boolean approximationBit) {
        this.approximationBit = approximationBit;
    }

    public int getLastReference() {
        return lastReference;
    }

    public void setLastReference(int lastReference) {
        this.lastReference = lastReference;
    }
}
