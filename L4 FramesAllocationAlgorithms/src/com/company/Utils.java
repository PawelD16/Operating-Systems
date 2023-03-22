package com.company;

import com.company.algorithms.*;

import java.util.ArrayList;
import java.util.Random;

public abstract class Utils {
    private static int allProcessesCounter;

    public static Process[] generateProcesses(int amount) {

        Random random = new Random();
        Process[] processes = new Process[amount];

        for (int i = 0; i < processes.length; i++) {

            ArrayList<Page> pages = new ArrayList<>();
            Process tempProcess = new Process(null);
            int maxPages = random.nextInt((int) Math.sqrt(amount)) + 2;

            for (int j = 1; j <= maxPages / 2; j++) {
                if (random.nextInt(100) <= 70) pages.add(new Page(j, tempProcess));
                else pages.add(new Page(random.nextInt(j) + j, tempProcess));
            }

            for (int j = 1; j < maxPages / 2; j++) {
                if (random.nextInt(100) <= 90) pages.add(new Page(j, tempProcess));
                else pages.add(new Page(random.nextInt(j) + j, tempProcess));
            }

            tempProcess.setPages(pages);
            processes[i] = tempProcess;
        }

        return processes;
    }

    public static Page[] generatePageCalls(Process[] processes) {
        ArrayList<Page> pagesArrayList = new ArrayList<>();


        for (Process process : processes) {
            pagesArrayList.addAll(process.getPages());
        }

        Page[] pagesArray = new Page[pagesArrayList.size()];
        pagesArray = pagesArrayList.toArray(pagesArray);

        return pagesArray;
    }

    public static Process[] copyProcesses(Process[] processes){
        Process[] tempProcesses = new Process[processes.length];

        for (int i = 0; i < processes.length; i++) {

            ArrayList<Page> pages = new ArrayList<>();
            Process tempProcess = new Process(null);

            for(int j =0;j<processes[i].getPages().size();j++){
                pages.add(new Page(processes[i].getPages().get(j).getPageID(), tempProcess));
            }

            tempProcess.setPages(pages);
            tempProcesses[i] = tempProcess;
        }

        return tempProcesses;
    }

    public static void outAllInfo(FrameAllocationAlgorithm frameAllocationAlgorithm) {
        String name = "";
        if (frameAllocationAlgorithm instanceof Equal) name = "Equal: ";
        else if (frameAllocationAlgorithm instanceof Proportional) name = "Proportional: ";
        else if (frameAllocationAlgorithm instanceof ZoneModel) name = "ZoneModel: ";
        else if (frameAllocationAlgorithm instanceof ErrorRateControl) name = "ErrorRateControl: ";

        System.out.println(name + "\n" + "all page faults: " + frameAllocationAlgorithm.getPageFaultCounter());

        Process[] processes = frameAllocationAlgorithm.getProcesses();

/*
        for (Process process : processes) {
            System.out.println(process);
        }

 */

        System.out.println("\n\n");
    }
}
