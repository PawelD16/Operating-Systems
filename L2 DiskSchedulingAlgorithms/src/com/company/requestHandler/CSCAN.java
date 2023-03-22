package com.company.requestHandler;

import com.company.Request;
import com.company.realTimeHandler.IRealTimeAlgorithm;

public class CSCAN extends Algorithm {
    public CSCAN(int timeToMoveOneCylinder, int amountOfCylinders, int requestsPerCylinder, int maxArrivalTime, int maxDeadLineLength, int percentageOfRealTimeRequests, IRealTimeAlgorithm realTimeAlgorithm) {
        super(timeToMoveOneCylinder, amountOfCylinders, requestsPerCylinder, maxArrivalTime, maxDeadLineLength, percentageOfRealTimeRequests, realTimeAlgorithm);
    }

    public CSCAN(int timeToMoveOneCylinder, Request[] requests, IRealTimeAlgorithm realTimeAlgorithm) {
        super(timeToMoveOneCylinder, requests, realTimeAlgorithm);
    }

    @Override
    public void simulate() {
        findFirst();

        if (findNextBest() < headerPosition) {  //if the next best is to left, we go to left first

            while (!this.isDone()) {
                ifAnyRequestPresent = false;

                for (int i = headerPosition; i >= 0; i--) {
                    realTimeAlgorithm.manageRealTime(this);
                    doRequest(i, false);
                }

                headerPosition = requests.length - 1;
                currentCylinderNumber = requests[requests.length - 1].getCylinderNumber();

                if (!ifAnyRequestPresent) time++;
                else {
                    time += timeOfHeaderMovement;
                }
            }
        } else {

            while (!this.isDone()) {
                ifAnyRequestPresent = false;

                for (int i = headerPosition; i < requests.length; i++) {
                    realTimeAlgorithm.manageRealTime(this);
                    doRequest(i, false);
                }

                headerPosition = 0;
                currentCylinderNumber = requests[0].getCylinderNumber();

                if (!ifAnyRequestPresent) time++;
                else {
                    time += timeOfHeaderMovement;
                }
            }
        }
        this.listRequestsifDone();
    }
}
