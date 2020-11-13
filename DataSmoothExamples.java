import static org.junit.Assert.*;
import org.junit.Test;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;

public class DataSmoothExamples
{
    LinkedList<Show> shows = new LinkedList<Show>();
    LinkedList<Show> averageTest = new LinkedList<Show>();
    LinkedList<Double> showResults = new LinkedList<Double>();
    DataSmooth1 D1 = new DataSmooth1();
    DataSmooth2 D2 = new DataSmooth2();
    LinkedList<Show> emptyShows = new LinkedList<Show>();
    LinkedList<Show> lengthTwoShows = new LinkedList<Show>();
    LinkedList<Show> lengthThreeShows = new LinkedList<Show>();
    LinkedList<Show> lengthFourShows = new LinkedList<Show>();

    public DataSmoothExamples()
    {
        LinkedList<Episode> eps1 = new LinkedList<Episode>();
        eps1.add(new Episode("Best of Both Worlds", 88));
        eps1.add(new Episode("The Ultimate Computer", 49));
        eps1.add(new Episode("Trials and Tribble-ations", 43));
        shows.add(new Show("Star Trek", 1800, eps1, false));
        averageTest.add(new Show("Star Trek", 1800, eps1, false));

        LinkedList<Episode> eps2 = new LinkedList<Episode>();
        eps2.add(new Episode("Fear of a Bot Planet", 23));
        eps2.add(new Episode("The Why of Fry", 21));
        eps2.add(new Episode("Roswell that Ends Well", 23));
        eps2.add(new Episode("Meanwhile", 22));
        shows.add(new Show("Futurama", 1900, eps2, false));

        LinkedList<Episode> eps3 = new LinkedList<Episode>();
        eps3.add(new Episode("Yakko's World", 4));
        eps3.add(new Episode("Hello Nice Warners", 8));
        eps3.add(new Episode("Where Rodents Dare", 9));
        shows.add(new Show("Animaniacs", 1630, eps3, false));

        LinkedList<Episode> eps4 = new LinkedList<Episode>();
        eps4.add(new Episode("The Letter W", 59));
        eps4.add(new Episode("The Letter P", 57));
        eps4.add(new Episode("The Letter I", 58));
        shows.add(new Show("Sesame Street", 900, eps4, false));

        showResults.add(60.0);
        showResults.add(29.75);
        showResults.add(29.08333);
        showResults.add(58.0);

        LinkedList<Episode> first = new LinkedList<Episode>();
        first.add(new Episode("Best of Both Worlds", 88));
        first.add(new Episode("The Ultimate Computer", 49));
        lengthTwoShows.add(new Show("Star Trek", 1800, first, false));
        lengthThreeShows.add(new Show("Star Trek", 1800, first, false));
        lengthThreeShows.add(new Show("Star Trek", 1800, first, false));
        lengthFourShows.add(new Show("Star Trek", 1800, first, false));
        lengthFourShows.add(new Show("Star Trek", 1800, first, false));

        LinkedList<Episode> second = new LinkedList<Episode>();
        second.add(new Episode("Best of Both Worlds", 50));
        second.add(new Episode("The Ultimate Computer", 100));
        lengthTwoShows.add(new Show("Star Trek", 1800, second, false));
        lengthThreeShows.add(new Show("Star Trek", 1800, second, false));
        lengthFourShows.add(new Show("Star Trek", 1800, second, false));
        lengthFourShows.add(new Show("Star Trek", 1800, second, false));

        LinkedList<Episode> third = new LinkedList<Episode>();
        third.add(new Episode("Best of Both Worlds", 50));
        lengthFourShows.add(new Show("Star Trek", 1800, third, false));
    }

    @Test
    public void instructorTestDS()
    {
        LinkedList<Double> runtimes = D1.dataSmooth(shows);

        for(int i = 0; i < runtimes.size(); i++)
        {
            assertEquals(runtimes.get(i), showResults.get(i), .01);
        }
    }

    @Test
    public void getRunTimesTestBasic(){
        LinkedList<Double> runtimes = D1.getRunTimes(averageTest);
        for(int i = 0; i < runtimes.size(); i++)
        {
            assertEquals(60, runtimes.get(i), .01);
        }
    }

    @Test
    public void dataSmoothTestEmpty(){
        LinkedList<Double> empty = new LinkedList<Double>();
        assertEquals(empty, D1.dataSmooth(emptyShows));
    }

    @Test
    public void dataSmoothTestLengthTwoList(){
        LinkedList<Double> runtimes = D1.dataSmooth(lengthTwoShows);
        assertEquals(68.5, runtimes.get(0), .01);
        assertEquals(75.0, runtimes.get(1), .01);
    }

    @Test
    public void dataSmoothTestLengthThreeList(){
        LinkedList<Double> runtimes = D1.dataSmooth(lengthThreeShows);
        assertEquals(68.5, runtimes.get(0), .01);
        assertEquals(70.667, runtimes.get(1), .01);
        assertEquals(75.0, runtimes.get(2), .01);
    }

    @Test
    public void dataSmoothTestLengthFiveList(){
        LinkedList<Double> runtimes = D1.dataSmooth(lengthFourShows);
        assertEquals(68.5, runtimes.get(0), .01);
        assertEquals(70.6667, runtimes.get(1), .01);
        assertEquals(72.8333, runtimes.get(2), .01);
        assertEquals(66.6667, runtimes.get(3), .01);
        assertEquals(50.0, runtimes.get(4), .01);
        // 68.5, 68.5, 75, 75, 50
    }

}

/*
Subtasks
-Create an empty list of double
-Calculate the average runTime of each show listed in shows
-Return the list of runTimes
-Calculate the average of each of the number and its predecessor and successor
 */