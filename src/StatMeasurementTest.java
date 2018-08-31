
import java.io.IOException;
import java.text.ParseException;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for MesonetTimeFile
 * @author Jeffrey Glass
 * @version 2018-03-31
 *
 */
public class StatMeasurementTest
{

    /**
     * Tests for the class
     * @throws IOException exception
     * @throws WrongCopyrightException exception
     * @throws ParseException exception
     */
    @Test
    public void statMeasurementClasstest() throws IOException, WrongCopyrightException, ParseException
    {
        /*String[] files = { "data/mesonet/20180102stil.mts"};
        DaysStatistics stats = new DaysStatistics(files);
        stats.findStatistics();*/
        GregorianCalendar testCalendar = new GregorianCalendar();
        StatMeasurement stat = new StatMeasurement(100.0, testCalendar, "station", 
                "param", StatType.MAX);
        String inDateTimeNewer = "2015 01 25 45";
        String inDateTimeOlder = "2025 01 25 45";
        
        Assert.assertEquals(false, stat.newerThan(inDateTimeNewer));
        Assert.assertEquals(false, stat.newerThan(inDateTimeOlder));
        
        StatMeasurement compareWithGreater = new StatMeasurement(120.0, testCalendar, "station", 
                "param", StatType.MAX);
        StatMeasurement compareWithLesser = new StatMeasurement(10.0, testCalendar, "station", 
                "param", StatType.MAX);
        Assert.assertEquals(true, stat.isGreaterThan(compareWithGreater));
        Assert.assertEquals(false, stat.isLessThan(compareWithLesser));
        
        Assert.assertEquals("param", stat.getParamId());
        Assert.assertEquals(StatType.MAX, stat.getStatType());
        Assert.assertEquals(100.0, stat.getValue(), 0.01);
        Assert.assertEquals(testCalendar, stat.getDateTimeOfMeasurment());
        
        
        //Assert.assertEquals("param MAX 100.0 station 2018-04-01T03:32:50 UTC", stat.toString());
    }

}
