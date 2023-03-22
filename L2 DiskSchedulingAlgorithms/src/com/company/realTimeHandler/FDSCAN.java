package com.company.realTimeHandler;

import com.company.requestHandler.Algorithm;

public class FDSCAN extends RealTimeAlgorithm {
    @Override
    public void manageRealTime(Algorithm algorithm) {
        while (ifAnyRealTimePresent(algorithm.getRequests(), algorithm.getTime()) != -1) {
            int foundRealTime = ifAnyRealTimePresent(algorithm.getRequests(), algorithm.getTime());
            for (int i = 0; i < algorithm.getRequests().length; i++) {
                if (algorithm.getRequests()[i].getDeadline() > -1 && algorithm.getRequests()[i].getDeadline() + algorithm.getRequests()[i].getArrivalTime() < algorithm.getTime() && algorithm.getRequests()[i].getArrivalTime() <= algorithm.getTime() && !algorithm.getRequests()[i].isDone()) {
                    algorithm.getRequests()[i].setWaitTime(algorithm.getRequests()[i].getDeadline());
                    algorithm.getRequests()[i].setMissedDeadLine();
                    algorithm.getRequests()[i].setDone(true);
                    algorithm.getDoneRequests().add(algorithm.getRequests()[i]);
                }

                if (algorithm.getRequests()[i].getDeadline() + algorithm.getRequests()[i].getArrivalTime() >= algorithm.getTime() && algorithm.getRequests()[i].getArrivalTime() <= algorithm.getTime() && !algorithm.getRequests()[i].isDone() && algorithm.getRequests()[i].getDeadline() > -1 && algorithm.getRequests()[i].getDeadline() < algorithm.getRequests()[foundRealTime].getDeadline()) {
                    foundRealTime = i;
                }
            }
            if (algorithm.getHeaderPosition() < foundRealTime) {
                for (int i = algorithm.getHeaderPosition(); i <= foundRealTime; i++) {
                    if (algorithm.getRequests()[i].getArrivalTime() <= algorithm.getTime() && !algorithm.getRequests()[i].isDone() && algorithm.getRequests()[i].getDeadline() > -1)
                        algorithm.doRequest(i, false);
                }
            } else {
                for (int i = algorithm.getHeaderPosition(); i >= foundRealTime; i--) {
                    if (algorithm.getRequests()[i].getArrivalTime() <= algorithm.getTime() && !algorithm.getRequests()[i].isDone() && algorithm.getRequests()[i].getDeadline() > -1)
                        algorithm.doRequest(i, false);
                }

            }
        }
    }
}
