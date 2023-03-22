package com.company;

public class Process {
    private final int requiredResources, processID;
    private static int globalProcessID;
    private int leftClocks;

    public Process(int requiredResources) {
        this.requiredResources = requiredResources;
        this.leftClocks = requiredResources;
        this.processID = ++globalProcessID;
    }

    public int getLeftClocks() {
        return leftClocks;
    }

    public boolean doTickAndIsDone() {
        leftClocks--;
        return leftClocks <= 0;
    }

    private boolean isDone() {
        return leftClocks <= 0;
    }

    @Override
    public String toString() {
        return this.processID + " " + requiredResources + " " + isDone();
    }
}
