package com.company;

import com.company.algorithms.Equal;
import com.company.algorithms.ErrorRateControl;
import com.company.algorithms.Proportional;
import com.company.algorithms.ZoneModel;

public class Main {

    public static void main(String[] args) {
        int processAmount = 100;
        int frameAmount = 300;

        Process[] equalProcesses = Utils.generateProcesses(processAmount);
        Process[] proportionalProcesses = Utils.copyProcesses(equalProcesses);
        Process[] errorRateControlProcesses = Utils.copyProcesses(equalProcesses);
        Process[] zoneControlProcesses = Utils.copyProcesses(equalProcesses);

        Equal equal = new Equal(frameAmount, equalProcesses, Utils.generatePageCalls(equalProcesses), 2);
        equal.simulate();
        Utils.outAllInfo(equal);


        Proportional proportional = new Proportional(frameAmount, proportionalProcesses, Utils.generatePageCalls(proportionalProcesses), 50);
        proportional.simulate();
        Utils.outAllInfo(proportional);

        ErrorRateControl errorRateControl = new ErrorRateControl(frameAmount, errorRateControlProcesses, Utils.generatePageCalls(errorRateControlProcesses), 80);
        errorRateControl.simulate();
        Utils.outAllInfo(errorRateControl);

        ZoneModel zoneModel = new ZoneModel(frameAmount, zoneControlProcesses, Utils.generatePageCalls(zoneControlProcesses), 10);
        zoneModel.simulate();
        Utils.outAllInfo(zoneModel);
    }
}
