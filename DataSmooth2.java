import java.util.LinkedList;

class DataSmooth2 {
    DataSmooth2(){}

    /**
     * Produce a list of the smoothed runTime values
     * @param shows
     * @return a list of runTime values
     */
    public LinkedList<Double> dataSmooth(LinkedList<Show> shows)
    {
        if(shows.size() == 0){
            return getRunTimes(shows);
        }
        LinkedList<Double> runTimes = getRunTimes(shows);
        LinkedList<Double> smoothedList = new LinkedList<Double>();
        smoothedList.add(runTimes.get(0)); //add first to smoothed
        for(int i=1; i < runTimes.size()-1; i++){
            smoothedList.add((runTimes.get(i) + runTimes.get(i-1) +
                    runTimes.get(i+1))/3);
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
        for (Show aShow : shows) {
            sum = 0; //reset sum to 0
            if(! (aShow.episodes.size() == 0)) {
                for (Episode anEpisode : aShow.episodes) {
                    sum += anEpisode.runTime;
                }
                runTimes.add(sum / aShow.episodes.size());
            }
            else{
                runTimes.add(0.0);
            }
        }
        return runTimes;
    }
}