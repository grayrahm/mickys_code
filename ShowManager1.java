import java.util.LinkedList;

class ShowManager1 {

    ShowManager1() {}

    /**
     * Produce a list of a report of all shows that are not specials
     * @param shows, a list of all possible shows
     * @return a list of non-special shows in the order provided sorted by time aired
     */
    public ShowSummary organizeShows(LinkedList<Show> shows)
    {
        LinkedList<Show> nonSpecialShows = isNonSpecial(shows);
        LinkedList<Show> dayTime = new LinkedList<Show>();
        LinkedList<Show> primeTime = new LinkedList<Show>();
        LinkedList<Show> lateNight = new LinkedList<Show>();

        for(Show aShow : nonSpecialShows){
            if(aShow.broadcastTime >= 0600 && aShow.broadcastTime < 1700){
                dayTime.add(aShow);
            }
            else if(aShow.broadcastTime >= 1700 && aShow.broadcastTime < 2200){
                primeTime.add(aShow);
            }
            else{
                lateNight.add(aShow);
            }
        }
        ShowSummary sortedShows = new ShowSummary(dayTime, primeTime, lateNight);
        return sortedShows;
    }

    /**
     * Produce a list of all shows that are not specials
     * @param shows, a list of all possible shows
     * @return a list of non-special shows in the order provided
     */
    public LinkedList<Show> isNonSpecial(LinkedList<Show> shows){
        LinkedList<Show> nonSpecialShows = new LinkedList<Show>();
        for(Show aShow : shows){
            if(! aShow.isSpecial){
                nonSpecialShows.add(aShow);
            }
        }
        return nonSpecialShows;
    }

}