package com.company.requestHandler;

import com.company.Request;
import com.company.realTimeHandler.IRealTimeAlgorithm;
import com.company.requestHandler.Algorithm;

public class SSTF extends Algorithm {


    public SSTF(int headerMovementTime, int amountOfCylinders, int requestsPerCylinder, int maxArrivalTime, int maxDeadLineLength, int percentageOfRealTimeRequests, IRealTimeAlgorithm realTimeAlgorithm) {
        super(headerMovementTime, amountOfCylinders, requestsPerCylinder, maxArrivalTime, maxDeadLineLength, percentageOfRealTimeRequests, realTimeAlgorithm);
    }

    public SSTF(int timeToMoveOneCylinder, Request[] requests, IRealTimeAlgorithm realTimeAlgorithm) {
        super(timeToMoveOneCylinder, requests, realTimeAlgorithm);
    }

    @Override
    public void simulate() {
        findFirst();

        while (!this.isDone()) {
            realTimeAlgorithm.manageRealTime(this);
            doRequest(findNextBest(), true);
        }
        this.listRequestsifDone();
    }
}
