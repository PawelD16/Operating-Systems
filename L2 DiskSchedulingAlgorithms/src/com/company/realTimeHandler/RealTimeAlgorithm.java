package com.company.realTimeHandler;

import com.company.Request;

public abstract class RealTimeAlgorithm implements IRealTimeAlgorithm {

    @Override
    public int ifAnyRealTimePresent(Request[] requests, int time){
        for (int i=0;i<requests.length;i++) {
            if (!requests[i].isDone() && requests[i].getDeadline() > -1 && requests[i].getArrivalTime() <= time) {
                return i;
            }
        }
            return -1;
    }
}
