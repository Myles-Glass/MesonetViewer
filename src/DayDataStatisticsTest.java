import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for MesonetTimeFile
 * @author Jeffrey Glass
 * @version 2018-03-31
 *
 */
public class DayDataStatisticsTest
{

    /**
     * toString test
     */
    @Test
    public void toStringTest()
    {
        ArrayList<TimeData> data = new ArrayList<TimeData>();
        GregorianCalendar testCalendar = new GregorianCalendar();
        TimeData timeData = new TimeData("station", testCalendar, new Measurement(100.0), new Measurement(200.0),
                new Measurement(300.0));
        data.add(timeData);
        DayDataStatistics dayData = new DayDataStatistics(data);

        String compare = "1-2-5, station: \n" +
            "Air temperature[1.5m] = [100.0000, 100.0000, 100.0000],\n" +
            "Air temperature[9m] = [200.0000, 200.0000, 200.0000],\n" +
            "Solar Radiation = [300.0000, 300.0000, 300.0000, 300.0000]";
        //Assert.assertEquals("station", timeData.getStationID());
        Assert.assertEquals(compare, dayData.toString());
    }

}
