package com.company;

import java.util.ArrayList;
import java.util.Random;

public class PageGenerator {
    private ArrayList<Page> pages;

    public PageGenerator(int howManyPages, int maxAmountOfDifferentPages) {
        pages = new ArrayList<>();

        for (int i = 0; i < howManyPages; i++) {
            pages.add(new Page(new Random().nextInt(maxAmountOfDifferentPages), true,0));
        }

        return;
    }

    public ArrayList<Page> getPages(){
        return pages;
    }
}
