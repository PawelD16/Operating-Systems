package com.company.requestHandler;

import com.company.Request;
import com.company.realTimeHandler.IRealTimeAlgorithm;
import java.util.Arrays;
import java.util.Comparator;

public class FCFS extends Algorithm {
    public FCFS(int headerMovementTime, int amountOfCylinders, int requestsPerCylinder, int maxArrivalTime, int maxDeadLineLength, int percentageOfRealTimeRequests, IRealTimeAlgorithm realTimeAlgorithm) {
        super(headerMovementTime, amountOfCylinders, requestsPerCylinder, maxArrivalTime, maxDeadLineLength, percentageOfRealTimeRequests, realTimeAlgorithm);
        Arrays.sort(requests, Comparator.comparingInt(Request::getArrivalTime));
    }

    public FCFS(int timeToMoveOneCylinder, Request[] requests, IRealTimeAlgorithm realTimeAlgorithm) {
        super(timeToMoveOneCylinder, requests, realTimeAlgorithm);
        Arrays.sort(requests, Comparator.comparingInt(Request::getArrivalTime));
    }

    @Override
    public void simulate() {
        findFirst();

        while (!this.isDone()) {

            ifAnyRequestPresent = false;

            for (int i = 0; i < requests.length; i++) {
                realTimeAlgorithm.manageRealTime(this);
                if (doRequest(i, true)) break;
            }
            if (!ifAnyRequestPresent) time++;
        }
        this.listRequestsifDone();
    }
}
