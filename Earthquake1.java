import sun.awt.image.ImageWatched;

import java.util.LinkedList;

class Earthquake1 {
    Earthquake1() {
    }
    //Earthquake1 E1 = new Earthquake1();

    // checks whether a datum is a date
    boolean isDate(double anum) {
        return (int) anum > 10000000;
    }

    // extracts the month from an 8-digit date
    int extractMonth(double dateNum) {
        return ((int) dateNum % 10000) / 100;
    }

    /**
     * Find the max frequency correspond to each date of the given month
     * @param data list of dates and frequencies that we want to generate the report
     * @param month the month that we want to extract the data
     * @return a list of report of the date and its highest earthquake frequency
     */
    public LinkedList<MaxHzReport> dailyMaxForMonth(LinkedList<Double> data, int month) {
        LinkedList<MaxHzReport> result = new LinkedList<MaxHzReport>();
        MaxHzReport report = new MaxHzReport(0,0);
        LinkedList<Double> dataList = findMaxData(data, month); // list of frequencies in corresponding index of list of dates
        LinkedList<Double> listDates = getDates(data, month); // list of dates in corresponding index of list of max frequencies
        for(double aData : listDates) {
            report = new MaxHzReport(aData, dataList.get(listDates.indexOf(aData)));
            result.add(report);
        }
        return result;
    }

    /**
     * Find the maximum frequency of each date of the given month
     * @param data list of data that we want to extract list of max frequency of each date
     * @param month the month that we want to extract the data
     * @return a list of maximum frequency of each date of the given month
     */
    public LinkedList<Double> findMaxData(LinkedList<Double> data, int month) {
        LinkedList<Double> result = new LinkedList<Double>();
        LinkedList<Double> dataList = desireData(data, month);
        dataList.addLast(-1.0);
        LinkedList<Double> dateData = new LinkedList<Double>();  // list of frequencies
        double maxValue = 0;
        for(double aData : dataList) {
            if(aData == -1.0) {
                maxValue = findMax(dateData);
                result.add(maxValue);
                return result;
            }
            if(dateData.size()>0 && isDate(dataList.get(dataList.indexOf(aData) + 1))
                    && extractMonth(dataList.get(dataList.indexOf(aData) + 1)) == month){ // when get to new date
                maxValue = findMax(dateData); // find maximum frequency of the dateData
                result.add(maxValue);
            }
            if(isDate(aData)) {
                dateData = new LinkedList<Double>(); //reset the list of frequencies to empty
            }
            if(!isDate(aData)) {
                dateData.add(aData);
            }
        }
        return result;
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

    /**
     * Find all the dates in the list
     * @param data list of data extracted from the method desireData()
     * @param month the month that we want to extract the data
     * @return a list of dates from the give list of data
     */
    public LinkedList<Double> getDates(LinkedList<Double> data, int month) {
        Earthquake1 E1 = new Earthquake1();
        LinkedList<Double> listDates = new LinkedList<Double>();
        for(Double aData : data){
            if(E1.isDate(aData) && E1.extractMonth(aData) == month) {
                listDates.add(aData);
            }
        }
        return listDates;
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
                return getList;
            }
        }
        return getList;
    }
}
