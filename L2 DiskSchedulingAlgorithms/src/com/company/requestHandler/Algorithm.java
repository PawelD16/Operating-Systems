package com.company.requestHandler;

import com.company.Request;
import com.company.realTimeHandler.IRealTimeAlgorithm;

import java.util.ArrayList;
import java.util.Random;

public abstract class Algorithm {

    protected final Request[] requests;
    protected ArrayList<Request> doneRequests;
    protected int time, currentCylinderNumber, timeOfHeaderMovement, headerCylinderChanges, headerPosition; //seek time = amount of cylinders we need to jump * time to jump a cylinder
    protected boolean ifAnyRequestPresent;
    protected IRealTimeAlgorithm realTimeAlgorithm;

    public Algorithm(int timeOfHeaderMovement, int amountOfCylinders, int requestsPerCylinder, int maxArrivalTime, int maxDeadLineLength, int percentageOfRealTimeRequests, IRealTimeAlgorithm realTimeAlgorithm) {
        this.timeOfHeaderMovement = timeOfHeaderMovement;
        this.currentCylinderNumber = 0;
        this.headerCylinderChanges = 0;
        this.headerPosition = -1;
        this.ifAnyRequestPresent = false;
        this.realTimeAlgorithm = realTimeAlgorithm;
        this.requests = new Request[amountOfCylinders * requestsPerCylinder];
        this.doneRequests = new ArrayList<>();
        this.generateNewRequests(requestsPerCylinder, maxArrivalTime, maxDeadLineLength, percentageOfRealTimeRequests);
    }

    public Algorithm(int timeOfHeaderMovement, Request[] requests, IRealTimeAlgorithm realTimeAlgorithm) {
        this.timeOfHeaderMovement = timeOfHeaderMovement;
        this.currentCylinderNumber = 0;
        this.headerCylinderChanges = 0;
        this.headerPosition = -1;
        this.ifAnyRequestPresent = false;
        this.realTimeAlgorithm = realTimeAlgorithm;
        this.requests = requests;
        this.doneRequests = new ArrayList<>();
    }

    protected void generateNewRequests(int requestsPerCylinder, int maxArrivalTime, int maxDeadLineLength, int percentageOfRealTimeRequests) {
        Random generator = new Random();
        for (int i = 0; i < requests.length; i++) {
            if (generator.nextInt(100) < percentageOfRealTimeRequests)
                requests[i] = new Request(generator.nextInt(maxArrivalTime), i / requestsPerCylinder + 1, generator.nextInt(maxDeadLineLength));
            else requests[i] = new Request(generator.nextInt(maxArrivalTime), i / requestsPerCylinder + 1, -2);
        }
    }

    protected boolean isDone() {
        for (Request request : requests) {
            if (!request.isDone()) return false;
        }
        return true;
    }

    public void listRequestsifDone() {
        double allTimeSpentWaiting = 0;
        if (this.isDone())

            for (Request request : doneRequests) {
                //System.out.println(request);
                allTimeSpentWaiting += request.getWaitTime();
            }
        System.out.println("Header cylinder changes: " + headerCylinderChanges + "\taverage realisation time: " + allTimeSpentWaiting / (double) requests.length);
    }



    public boolean doRequest(int i, boolean ifCountHeaderDirectionChanges) {
        if (requests[i].getArrivalTime() <= time && !requests[i].isDone()) {
            if (requests[i].getCylinderNumber() != this.currentCylinderNumber) {
                time += Math.abs(requests[i].getCylinderNumber() - this.currentCylinderNumber) * timeOfHeaderMovement;
                if (ifCountHeaderDirectionChanges) headerCylinderChanges+=Math.abs(requests[i].getCylinderNumber() - this.currentCylinderNumber);
                else headerCylinderChanges++;
            }

            ifAnyRequestPresent = true;
            this.currentCylinderNumber = requests[i].getCylinderNumber();
            requests[i].setWaitTime(time - requests[i].getArrivalTime());
            requests[i].setDone(true);
            headerPosition = i;
            doneRequests.add(requests[i]);
            return true;
        }
        return false;
    }

    protected void findFirst() {
        while (headerPosition == -1) { //finding the first request to move to
            ifAnyRequestPresent = false;

            for (int i = 0; i < requests.length; i++) {
                if (doRequest(i, true)) {
                    headerPosition = i;
                    break;
                }
            }
            if (!ifAnyRequestPresent) time++;
        }
    }

    protected int findNextBest() { //finding the next request with shortest seek time (shortest distance (from right, as our cylinders go from fight to left) which is calculated by the amount of columns required to slip between)
        int k = -1;
        int predictedSeekTime = -1;

        while (headerPosition != -1 && k == -1) {
            ifAnyRequestPresent = false;

            for (int i = requests.length - 1; i >= 0; i--) {
                if (requests[i].getArrivalTime() <= time && !requests[i].isDone() && i != headerPosition) {
                    if (predictedSeekTime == -1) {
                        predictedSeekTime = Math.abs(requests[i].getCylinderNumber() - this.currentCylinderNumber) * timeOfHeaderMovement;
                        k = i;
                    } else if (predictedSeekTime > Math.abs(requests[i].getCylinderNumber() - this.currentCylinderNumber) * timeOfHeaderMovement) {
                        predictedSeekTime = Math.abs(requests[i].getCylinderNumber() - this.currentCylinderNumber) * timeOfHeaderMovement;
                        k = i;
                    }
                    ifAnyRequestPresent = true;
                }
            }
            if (!ifAnyRequestPresent) time++;
        }
        return k;
    }

    public abstract void simulate();

    public Request[] getRequests() {
        return requests;
    }

    public int getTime() {
        return time;
    }

    public ArrayList<Request> getDoneRequests() {
        return doneRequests;
    }

    public int getHeaderPosition(){
        return headerPosition;
    }

}
