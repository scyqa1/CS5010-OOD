package gameSystem;

import OrderList.*;

import java.util.ArrayList;

public class TopScoreTrackerImpl implements TopScoreTracker{

    private final int monthCount;
    private final int yearCount;

    private OrderedList[] month = new OrderedList[12];
    private OrderedList year;


    public TopScoreTrackerImpl(int monthCount, int yearCount) {
        this.monthCount = monthCount;
        this.yearCount = yearCount;

        for (int i = 0; i < 12; i++){
            month[i] = new OrderedListImpl(monthCount);;
        }
        year = new OrderedListImpl(yearCount);

    }

    @Override
    public void addRun(int m, Run r) {
        month[m-1].add(r.getScore());
    }

    @Override
    public String annualReport() {
        String str = "";
        for (int i = 0; i < 12; i++){
            str += "Month" + (i+1) + ": ";
            while (month[i].getMax() != null){
                str += month[i].getMax().toString();
            }
            str += "\n";
        }

        str += "------------------\n";
        str += "Year Top: ";
        while (year.getMax() != null){
            str += year.getMax().toString();
        }

        return str;
    }
}
