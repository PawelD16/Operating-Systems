package com.company;

import com.company.algorithms.Algorithm;
import com.company.algorithms.*;


import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Page> pageArrayList = new PageGenerator(1000, 50).getPages();

        //20 ramek
        System.out.println("20 ramek:");
        int n = 20;

        Algorithm FIFO = new FIFO(n, pageArrayList);
        FIFO.simulate();
        System.out.print(FIFO);

        Algorithm RAND = new RAND(n, pageArrayList);
        RAND.simulate();
        System.out.print(RAND);

        Algorithm LRU = new LRU(n, pageArrayList);
        LRU.simulate();
        System.out.print(LRU);

        Algorithm ALRU = new ALRU(n, pageArrayList);
        ALRU.simulate();
        System.out.print(ALRU);

        Algorithm OPT = new OPT(n, pageArrayList);
        OPT.simulate();
        System.out.print(OPT);


        //10 ramek
        System.out.println("\n\n10 ramek:");
        n = 10;
        Algorithm FIFO2 = new FIFO(n, pageArrayList);
        FIFO2.simulate();
        System.out.print(FIFO2);

        Algorithm RAND2 = new RAND(n, pageArrayList);
        RAND2.simulate();
        System.out.print(RAND2);

        Algorithm LRU2 = new LRU(n, pageArrayList);
        LRU2.simulate();
        System.out.print(LRU2);

        Algorithm ALRU2 = new ALRU(n, pageArrayList);
        ALRU2.simulate();
        System.out.print(ALRU2);

        Algorithm OPT2 = new OPT(n, pageArrayList);
        OPT2.simulate();
        System.out.print(OPT2);


        //5 ramek
        System.out.println("\n\n5 ramek:");
        n = 5;
        Algorithm FIFO3 = new FIFO(n, pageArrayList);
        FIFO3.simulate();
        System.out.print(FIFO3);

        Algorithm RAND3 = new RAND(n, pageArrayList);
        RAND3.simulate();
        System.out.print(RAND3);

        Algorithm LRU3 = new LRU(n, pageArrayList);
        LRU3.simulate();
        System.out.print(LRU3);

        Algorithm ALRU3 = new ALRU(n, pageArrayList);
        ALRU3.simulate();
        System.out.print(ALRU3);

        Algorithm OPT3 = new OPT(n, pageArrayList);
        OPT3.simulate();
        System.out.print(OPT3);
    }
}
