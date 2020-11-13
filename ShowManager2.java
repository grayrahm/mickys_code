import java.util.LinkedList;

class ShowManager2 {

    ShowManager2() {}

    /**
     * Produce a list of a report of all shows that are not specials
     * @param shows, a list of all possible shows
     * @return a list of non-special shows in the order provided sorted by time aired
     */
    public ShowSummary organizeShows(LinkedList<Show> shows)
    {
        LinkedList<Show> dayTime = new LinkedList<Show>();
        LinkedList<Show> primeTime = new LinkedList<Show>();
        LinkedList<Show> lateNight = new LinkedList<Show>();

        for(Show aShow : shows){
            if(aShow.broadcastTime >= 0600 && aShow.broadcastTime < 1700 && ! aShow.isSpecial){
                dayTime.add(aShow);
            }
            else if(aShow.broadcastTime >= 1700 && aShow.broadcastTime < 2200 && ! aShow.isSpecial){
                primeTime.add(aShow);
            }
            else if (! aShow.isSpecial){
                lateNight.add(aShow);
            }
        }
        ShowSummary sortedShows = new ShowSummary(dayTime, primeTime, lateNight);
        return sortedShows;
    }
}