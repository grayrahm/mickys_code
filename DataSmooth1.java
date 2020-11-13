import com.sun.scenario.effect.impl.state.LinearConvolveKernel;

import java.util.LinkedList;

class DataSmooth1 {
    DataSmooth1(){}

    /**
     * Produce a list of the smoothed runTime values
     * @param shows
     * @return a list of runTime values
     */
    public LinkedList<Double> dataSmooth(LinkedList<Show> shows)
    {
        //for cases where there will be no smoothing
        if(shows.size() <= 2){
            return getRunTimes(shows);
        }

        LinkedList<Double> runTimes = getRunTimes(shows);
        LinkedList<Double> shortenedRunTimes = new LinkedList<Double>();
        //create duplicate of runTimes
        for(Double aRunTimes: runTimes){
            shortenedRunTimes.add(aRunTimes);
        }
        LinkedList<Double> smoothedList = new LinkedList<Double>();
        smoothedList.add(runTimes.get(0)); //add first to smoothed

        double predecessor;
        double successor;
        double smoothedValue;

        for(int i=1; i < shortenedRunTimes.size() - 1; i++){
            predecessor = runTimes.get(i - 1); //set predecessor
            successor = runTimes.get(i + 1); //set successor
            smoothedValue = shortenedRunTimes.get(i) + predecessor + successor;
            smoothedValue = smoothedValue / 3;
            smoothedList.add(smoothedValue);
        }

        smoothedList.add(runTimes.getLast());
        return smoothedList;
    }

    /**
     * Create a list of the runTimes of all of the shows
     * The average runtime is the sum of the runtimes of
     * all episodes in the show divided by the number of episodes.
     * @param shows
     * @return a list of runtimes
     */
    public LinkedList<Double> getRunTimes(LinkedList<Show> shows){
        LinkedList<Double> runTimes = new LinkedList<Double>();
        double sum;
        double averageRunTime;
        if(! (shows.size() == 0)) {
            for (Show aShow : shows) {
                if(! (aShow.episodes.size() == 0)) {
                    sum = 0; //reset sum to 0
                    averageRunTime = 0; //reset average to 0
                    for (Episode anEpisode : aShow.episodes) {
                        sum += anEpisode.runTime;
                    }
                    averageRunTime = sum / aShow.episodes.size();
                    runTimes.add(averageRunTime);
                }
                else{
                    runTimes.add(0.0);
                }
            }
        }
        return runTimes;
    }
}
