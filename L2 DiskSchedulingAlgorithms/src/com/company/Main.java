package com.company;

import com.company.realTimeHandler.EDF;
import com.company.realTimeHandler.FDSCAN;
import com.company.requestHandler.CSCAN;
import com.company.requestHandler.FCFS;
import com.company.requestHandler.SCAN;
import com.company.requestHandler.SSTF;

public class Main {

    public static void main(String[] args) {
        /*
        Request[] tab = new Request[10];
        tab[0] = new Request(0, 1, 1);
        tab[1] = new Request(10, 1, 2);
        tab[2] = new Request(5, 1, 3);
        tab[3] = new Request(4, 1, 4);
        tab[4] = new Request(8, 1, 5);
        tab[5] = new Request(1, 2, 2);
        tab[6] = new Request(20, 2, 6);
        tab[7] = new Request(15, 2, 9);
        tab[8] = new Request(7, 2, 0);
        tab[9] = new Request(0, 2, -2);
        */

        new FCFS(15,8, 16, 600, 200, 10, new EDF()).simulate();
        new SSTF(15,8, 16, 600, 200, 10, new EDF()).simulate();
        new SCAN(15,8, 16, 600, 200, 10, new EDF()).simulate();
        new CSCAN(15,8, 16, 600, 200, 10, new EDF()).simulate();

        System.out.println("\n\n");

        new FCFS(15,8, 16, 600, 200, 10, new FDSCAN()).simulate();
        new SSTF(15,8, 16, 600, 200, 10, new FDSCAN()).simulate();
        new SCAN(15,8, 16, 600, 200, 10, new FDSCAN()).simulate();
        new CSCAN(15,8, 16, 600, 200, 10, new FDSCAN()).simulate();

        System.out.println("\n\n");

        new FCFS(15,8, 16, 600, 200, 0, new FDSCAN()).simulate();
        new SSTF(15,8, 16, 600, 200, 0, new FDSCAN()).simulate();
        new SCAN(15,8, 16, 600, 200, 0, new FDSCAN()).simulate();
        new CSCAN(15,8, 16, 600, 200, 0, new FDSCAN()).simulate();
        //new CSCAN(3,tab, new EDF()).simulate();
    }
}
