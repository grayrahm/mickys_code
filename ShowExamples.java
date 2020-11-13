import static org.junit.Assert.*;
import org.junit.Test;
import java.util.LinkedList;
import java.util.Arrays;

public class ShowExamples
{
    ShowManager1 sm1 = new ShowManager1();
    ShowManager2 sm2 = new ShowManager2();
    LinkedList<Show> shows = new LinkedList<Show>();
    ShowSummary report1 = new ShowSummary();
    LinkedList<Show> noNonSpecials = new LinkedList<Show>();
    LinkedList<Show> noShows = new LinkedList<Show>();
    ShowSummary reportNoNonSpecials = new ShowSummary();
    ShowSummary reportNoShows = new ShowSummary();
    LinkedList<Show> filteredNoNonSpecials = new LinkedList<Show>();

    public ShowExamples()
    {
        LinkedList<Episode> eps1 = new LinkedList<Episode>();
        eps1.add(new Episode("Best of Both Worlds", 88));
        eps1.add(new Episode("The Ultimate Computer", 49));
        eps1.add(new Episode("Trials and Tribble-ations", 43));
        Show s1 = new Show("Star Trek", 1800, eps1, false);
        shows.add(s1);
        report1.primetime.add(s1);

        LinkedList<Episode> eps2 = new LinkedList<Episode>();
        eps2.add(new Episode("Fear of a Bot Planet", 23));
        eps2.add(new Episode("The Why of Fry", 21));
        eps2.add(new Episode("Roswell that Ends Well", 23));
        eps2.add(new Episode("Meanwhile", 22));
        Show s2 = new Show("Futurama", 1900, eps2, false);
        shows.add(s2);
        report1.primetime.add(s2);

        LinkedList<Episode> eps3 = new LinkedList<Episode>();
        eps3.add(new Episode("Yakko's World", 4));
        eps3.add(new Episode("Hello Nice Warners", 8));
        eps3.add(new Episode("Where Rodents Dare", 9));
        Show s3 = new Show("Animaniacs", 1630, eps3, false);
        shows.add(s3);
        report1.daytime.add(s3);

        LinkedList<Episode> eps4 = new LinkedList<Episode>();
        eps4.add(new Episode("The Letter W", 59));
        eps4.add(new Episode("The Letter P", 57));
        eps4.add(new Episode("The Letter I", 58));
        Show s4 = new Show("Sesame Street", 900, eps4, false);
        shows.add(s4);
        report1.daytime.add(s4);

        LinkedList<Episode> noNonSpecials1 = new LinkedList<Episode>();
        noNonSpecials1.add(new Episode("Fear of a Bot Planet", 23));
        noNonSpecials1.add(new Episode("The Why of Fry", 21));
        noNonSpecials1.add(new Episode("Roswell that Ends Well", 23));
        noNonSpecials1.add(new Episode("Meanwhile", 22));
        Show nns1 = new Show("Futurama", 1900, eps2, true);
        noNonSpecials.add(nns1);

        LinkedList<Episode> noNonSpecials2 = new LinkedList<Episode>();
        noNonSpecials2.add(new Episode("Yakko's World", 4));
        noNonSpecials2.add(new Episode("Hello Nice Warners", 8));
        noNonSpecials2.add(new Episode("Where Rodents Dare", 9));
        Show nns2 = new Show("Animaniacs", 1630, eps3, true);
        noNonSpecials.add(nns2);

        LinkedList<Episode> noNonSpecials3 = new LinkedList<Episode>();
        noNonSpecials1.add(new Episode("Fear of a Bot Planet", 23));
        noNonSpecials1.add(new Episode("The Why of Fry", 21));
        noNonSpecials1.add(new Episode("Roswell that Ends Well", 23));
        noNonSpecials1.add(new Episode("Meanwhile", 22));
        Show nns3 = new Show("Futurama", 1900, eps2, false);
        noNonSpecials.add(nns3);
        filteredNoNonSpecials.add(nns3);
        reportNoNonSpecials.primetime.add(nns3);
    }

    @Test
    public void instructorTestOrganizeShows()
    {
        ShowSummary report2 = sm1.organizeShows(shows);
        assertEquals(report1, report2);
    }

    @Test
    public void noShowsTestOrganizeShows()
    {
        ShowSummary report2 = sm1.organizeShows(noShows);
        assertEquals(report2, reportNoShows);
    }

    @Test
    public void noNonSpecialsTestOrganizeShows()
    {
        ShowSummary report2 = sm1.organizeShows(noNonSpecials);
        assertEquals(report2, reportNoNonSpecials);
    }

    @Test
    public void noNonSpecialsTestIsSpecials()
    {
        LinkedList<Show> report2 = sm1.isNonSpecial(noNonSpecials);
        assertEquals(report2, filteredNoNonSpecials);
    }

}

/*
Subtasks:
-create an empty list
-remove specials
-sort through the list and determine which shows are
 daytime, prime time, and late night
-create a ShowSummary object with those shows
-return the ShowSummary
 */