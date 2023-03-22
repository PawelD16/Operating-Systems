package com.company;

import java.util.Random;

public class Process {

    private int remainingCPUTime;
    private final int requiredCPUTime;
    private static int amountOfAllProcesses;
    private final int thisPID;
    private int waitTime;
    private final int timeOfArrival;
    private Random generator;

    public Process(int minTime, int maxTime, int timeOfArrival) {
        Random generator = new Random();
        this.remainingCPUTime = generator.nextInt(maxTime - minTime) + minTime;
        this.requiredCPUTime = remainingCPUTime;
        this.thisPID = amountOfAllProcesses++;
        this.waitTime = 0;
        this.timeOfArrival = generator.nextInt(maxTime + minTime);
    }

    public Process(int remainingCPUTime, int timeOfArrival) { //zakladamy, ze pozostaly czas jest marnowany

        this.remainingCPUTime = remainingCPUTime;
        this.requiredCPUTime = this.remainingCPUTime;
        this.thisPID = amountOfAllProcesses++;
        this.waitTime = 0;
        this.timeOfArrival = timeOfArrival;
    }

    public int reduceRemainingCPUTimeAndGetLeftoverTime(int time) { //zakladamy, ze pozostaly czas nie jest marnowany
        int temp;
        if (!isDone() && time > 0) {
            remainingCPUTime -= time;
            temp =remainingCPUTime;
            if(remainingCPUTime<0) remainingCPUTime = 0;
            if (temp<0) {
                return (-1) * temp;
            } else return 0;
        } else return 0;
    }

    public boolean isDone() {
        return getRemainingCPUTime() <= 0;
    }

    public int getThisPID() {
        return thisPID;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public int getRemainingCPUTime() {
        if (remainingCPUTime < 0) remainingCPUTime = 0;
        return remainingCPUTime;
    }

    public int getRequiredCPUTime() {
        return requiredCPUTime;
    }

    public void setAddWaitTime(int time) {
        if (time > 0) {
            this.waitTime += time;
        }
    }

    public void setWaitTime(int time) {
        if (time > 0) {
            this.waitTime = time;
        }
    }

    public int getTimeOfArrival() {
        return timeOfArrival;
    }

    @Override
    public String toString() {
        return "PID " + thisPID + "\trequired CPU time: " + requiredCPUTime + "\tremaining CPU time: " + remainingCPUTime + "\twait time: " + waitTime + "\ttime of arrival " + timeOfArrival;
    }
}
