import sun.awt.image.ImageWatched;

import java.util.LinkedList;

class Earthquake2 {
    Earthquake2(){}

    // checks whether a datum is a date
    boolean isDate(double anum) { return (int)anum > 10000000; }
    // extracts the month from an 8-digit date
    int extractMonth(double dateNum) { return ((int)dateNum % 10000) / 100; }

    /**
     * Find the max frequency correspond to each date of the given month
     * @param data list of dates and frequencies that we want to generate the report
     * @param month the month that we want to extract the data
     * @return a list of report of the date and its highest earthquake frequency
     */
    public LinkedList<MaxHzReport> dailyMaxForMonth(LinkedList<Double> data, int month) {
        LinkedList<MaxHzReport> result = new LinkedList<MaxHzReport>();
        LinkedList<Double> dataList = new LinkedList<Double>(); // list of frequencies
        LinkedList<Double> desireList = desireData(data, month); //list of dates and frequencies of the given month
        desireList.addLast(-1.0);
        double currentDate = 0;
        double maxValue = 0;
        MaxHzReport report = new MaxHzReport(0, 0);
        for(Double aData : desireList){
            if(aData == -1.0) { // last data on the list
                maxValue = findMax(dataList);
                report = new MaxHzReport(currentDate, maxValue);
                result.add(report);
                return result;
            }
            if(dataList.size()>0 && isDate(desireList.get(desireList.indexOf(aData) +1))){ //before get to the new date
                maxValue = findMax(dataList); //find the max frequency of the corresponding date in dataList
                report = new MaxHzReport(currentDate, maxValue);
                result.add(report);
            }
            if(isDate(aData)){
                currentDate = aData;
                dataList = new LinkedList<Double>(); //when get to new date, reset dataList to empty
            }
            if(!isDate(aData)){
                dataList.add(aData);
            }
        }
        return result;
    }


    /**
     * Find all the date of the given month and corresponding data of each date
     * @param data list of doubles that needs to be extracted
     * @param month the month that we want to extract the data
     * @return a list of dates and frequencies of the indicated month
     */
    public LinkedList<Double> desireData(LinkedList<Double> data, int month){
        LinkedList<Double> getList = new LinkedList<Double>();
        double currentDate = 0;
        for (Double aData : data) {
            if(isDate(aData) && extractMonth(aData) == month) {
                getList.add(aData);
                currentDate = aData;
            }
            else if (aData < 0) {
                getList = getList;
            }
            else if(!isDate(aData) && currentDate>0){
                getList.add(aData);
            }
            else if(isDate(aData) && extractMonth(aData) < month){
                getList=getList;
            }
            else if(isDate(aData) && extractMonth(aData) > month) {
                //getList.add(aData);
                return getList;
            }
        }
        return getList;
    }

    /**
     * Find the biggest number in the given list
     * @param listDoubles list of doubles that we want to find max
     * @return the biggest number in the list
     */
    public double findMax(LinkedList<Double> listDoubles) {
        double maxValue = 0;
        for(Double aDateData : listDoubles){
            if(aDateData > maxValue) {
                maxValue = aDateData;
            }
        }
        return maxValue;
    }
}