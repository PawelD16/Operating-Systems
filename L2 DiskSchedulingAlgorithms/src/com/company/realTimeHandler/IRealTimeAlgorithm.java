package com.company.realTimeHandler;

import com.company.Request;
import com.company.requestHandler.Algorithm;

public interface IRealTimeAlgorithm {
    int ifAnyRealTimePresent(Request[] requests, int time);
    void manageRealTime(Algorithm algorithm);
}
