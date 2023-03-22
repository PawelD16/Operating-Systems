package com.company.requestHandler;

import com.company.Request;
import com.company.realTimeHandler.IRealTimeAlgorithm;
import com.company.requestHandler.Algorithm;

public class SCAN extends Algorithm {
    public SCAN(int timeToMoveOneCylinder, int amountOfCylinders, int requestsPerCylinder, int maxArrivalTime, int maxDeadLineLength, int percentageOfRealTimeRequests, IRealTimeAlgorithm realTimeAlgorithm) {
        super(timeToMoveOneCylinder, amountOfCylinders, requestsPerCylinder, maxArrivalTime, maxDeadLineLength, percentageOfRealTimeRequests, realTimeAlgorithm);
    }

    public SCAN(int timeToMoveOneCylinder, Request[] requests, IRealTimeAlgorithm realTimeAlgorithm) {
        super(timeToMoveOneCylinder, requests, realTimeAlgorithm);
    }

    @Override
    public void simulate() {
        findFirst();

        if (findNextBest() < headerPosition) { //if the next best is to left, we go to left first
            for (int i = headerPosition; i >= 0; i--) {
                realTimeAlgorithm.manageRealTime(this);
                doRequest(i, false);
            }
            if (!ifAnyRequestPresent) time++;
        }

        while (!this.isDone()) {
            ifAnyRequestPresent = false;

            for (int i = headerPosition; i < requests.length; i++) {
                realTimeAlgorithm.manageRealTime(this);
                doRequest(i, false);
            }

            for (int i = headerPosition; i >= 0; i--) {
                realTimeAlgorithm.manageRealTime(this);
                doRequest(i, false);
            }
            if (!ifAnyRequestPresent) time++;
        }

        this.listRequestsifDone();
    }
}
