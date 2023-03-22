package com.company;

public class Request {

    private static int RID = 0; //Request Identification Number
    private final int cylinderNumber, arrivalTime, thisRID;
    private int deadline, waitTime; //-2 request is not realTime, -1 missed deadline, >=0 time to be done
    private boolean isDone, ifMissedDeadLine;
    //position on the cylinder or even realisation times dont matter, they are so incredibly small that we only care about the movement of the read/write head

    public Request(int arrivalTime, int cylinderNumber, int deadline) {
        this.thisRID = RID++;
        this.cylinderNumber = cylinderNumber;
        this.arrivalTime = arrivalTime;
        this.deadline = deadline;
        this.isDone = false;
        this.ifMissedDeadLine = false;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getDeadline() {
        return deadline;
    }

    public int getCylinderNumber() {
        return cylinderNumber;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public void setMissedDeadLine() {
        this.ifMissedDeadLine = true;
    }

    @Override
    public String toString() {

        if (deadline > -2)
            return "RID: " + this.thisRID + "\twait time: " + this.waitTime + "\ttime of arrival: " + this.arrivalTime + "\tcylinder number: " + this.cylinderNumber + "\tdeadline: " + this.deadline + "\tifMissedDeadLine: " + this.ifMissedDeadLine;
        return "RID: " + this.thisRID + "\twait time: " + this.waitTime + "\ttime of arrival: " + this.arrivalTime + "\tcylinder number: " + this.cylinderNumber;
    }
}
