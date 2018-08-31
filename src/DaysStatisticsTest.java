import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for DaysStatistics
 * @author Jeffrey Glass
 * @version 3/30/2018
 *
 */
public class DaysStatisticsTest
{

    /**
     * Test for tostring
     * @throws IOException  exception
     * @throws WrongCopyrightException exception
     * @throws ParseException exception
     * @throws WrongParameterIdException 
     */
    @Test
    public void toStringTest() throws IOException, WrongCopyrightException, ParseException, WrongParameterIdException
    {
        String[] files = { "data/mesonet/20180102stil.mts",
                "data/mesonet/20180102okcn.mts", "data/mesonet/20180102okce.mts"};

        DaysStatistics stats = new DaysStatistics(files);
            
        stats.findStatistics();
        System.out.println(stats.toString());

        Assert.assertEquals(true, stats.toString().contains("TAIR MAX"));
    }

}
