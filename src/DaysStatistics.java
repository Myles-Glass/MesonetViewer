import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;



/**
 * CSFile class
 * @author Jeffrey Glass
 * @version 3/30/2018
 *
 */
public class DaysStatistics extends StatisticsAbstract
{
    private ArrayList<String> files;
    
    private ArrayList<StatMeasurement> tairMinStats = new ArrayList<StatMeasurement>();
    private ArrayList<StatMeasurement> tairAvgStats = new ArrayList<StatMeasurement>();
    private ArrayList<StatMeasurement> tairMaxStats = new ArrayList<StatMeasurement>();

    private ArrayList<StatMeasurement> ta9mMinStats = new ArrayList<StatMeasurement>();
    private ArrayList<StatMeasurement> ta9mAvgStats = new ArrayList<StatMeasurement>();
    private ArrayList<StatMeasurement> ta9mMaxStats = new ArrayList<StatMeasurement>();

    private ArrayList<StatMeasurement> sradMinStats = new ArrayList<StatMeasurement>();
    private ArrayList<StatMeasurement> sradAvgStats = new ArrayList<StatMeasurement>();
    private ArrayList<StatMeasurement> sradMaxStats = new ArrayList<StatMeasurement>();
    private ArrayList<StatMeasurement> sradTotalStats = new ArrayList<StatMeasurement>();
    
    private ArrayList<StatMeasurement> wspdMinStats = new ArrayList<StatMeasurement>();
    private ArrayList<StatMeasurement> wspdAvgStats = new ArrayList<StatMeasurement>();
    private ArrayList<StatMeasurement> wspdMaxStats = new ArrayList<StatMeasurement>();
    private ArrayList<StatMeasurement> wspdTotalStats = new ArrayList<StatMeasurement>();

    /**
     * Constructor
     * @param files the files
     * @throws IOException exception
     * @throws WrongCopyrightException exception
     * @throws ParseException exception
     */
    public DaysStatistics(String[] files) throws IOException, WrongCopyrightException, ParseException
    {
        this.files = new ArrayList<String>(Arrays.asList(files));
        
        findStatistics();
    }

    /**
     * Finds stats
     * @throws IOException exception
     * @throws WrongCopyrightException exception
     * @throws ParseException exception
     */
    public void findStatistics() throws IOException, WrongCopyrightException, ParseException
    {
        for (String fileName : files)
        {
            MesonetTimeFile mtsFile = new MesonetTimeFile(fileName);
            mtsFile.parseFile();
            ArrayList<TimeData> data = mtsFile.parseFile();
            DayDataStatistics dataStats = new DayDataStatistics(data);

            assignStats(dataStats);
        }
    }

    /**
     * Assigns stats
     * @param dataStats the data
     * @throws ParseException for parsing
     */
    private void assignStats(DayDataStatistics dataStats) throws ParseException
    {
        tairMinStats.add(dataStats.getStatistic("TAIR", StatType.MIN));
        tairAvgStats.add(dataStats.getStatistic("TAIR", StatType.AVG));
        tairMaxStats.add(dataStats.getStatistic("TAIR", StatType.MAX));
        ta9mMinStats.add(dataStats.getStatistic("TA9M", StatType.MIN));
        ta9mAvgStats.add(dataStats.getStatistic("TA9M", StatType.AVG));
        ta9mMaxStats.add(dataStats.getStatistic("TA9M", StatType.MAX));
        sradMinStats.add(dataStats.getStatistic("SRAD", StatType.MIN));
        sradAvgStats.add(dataStats.getStatistic("SRAD", StatType.AVG));
        sradMaxStats.add(dataStats.getStatistic("SRAD", StatType.MAX));
        sradTotalStats.add(dataStats.getStatistic("SRAD", StatType.TOT));
        wspdMinStats.add(dataStats.getStatistic("WSPD", StatType.MIN));
        wspdAvgStats.add(dataStats.getStatistic("WSPD", StatType.AVG));
        wspdMaxStats.add(dataStats.getStatistic("WSPD", StatType.MAX));
        wspdTotalStats.add(dataStats.getStatistic("WSPD", StatType.TOT));
    }

    /**
     * Gets minimum day
     * 
     * @param inParamId String 
     * @return StatMeasurement the stat
     */
    @Override
    public StatMeasurement getMinimumDay(String inParamId) throws WrongParameterIdException
    {
        StatMeasurement measurement = new StatMeasurement();
        measurement.value = 999;
        if (inParamId.contains("TAIR"))
        {
            for (int i = 0; i < tairMinStats.size(); i++)
            {
                if (tairMinStats.get(i).value < measurement.value)
                {
                    measurement = tairMinStats.get(i);
                }
            }
        }
        
        if (inParamId.contains("TA9M"))
        {
            for (int i = 0; i < ta9mMinStats.size(); i++)
            {
                if (ta9mMinStats.get(i).value < measurement.value)
                {
                    measurement = ta9mMinStats.get(i);
                }
            }
        }
        
        if (inParamId.contains("SRAD"))
        {
            for (int i = 0; i < sradMinStats.size(); i++)
            {
                if (sradMinStats.get(i).value < measurement.value)
                {
                    measurement = sradMinStats.get(i);
                }
            }
        }
        
        if (inParamId.contains("WSPD"))
        {
            for (int i = 0; i < wspdMinStats.size(); i++)
            {
                if (wspdMinStats.get(i).value < measurement.value)
                {
                    measurement = wspdMinStats.get(i);
                }
            }
        }

        return measurement;
    }

    /**
     * Gets maximum day
     * 
     * @param inParamId String 
     * @return StatMeasurement the stat
     */
    @Override
    public StatMeasurement getMaximumDay(String inParamId) throws WrongParameterIdException
    {
        StatMeasurement measurement = new StatMeasurement();
        measurement.value = -999;
        if (inParamId.contains("TAIR"))
        {
            for (int i = 0; i < tairMaxStats.size(); i++)
            {
                if (tairMaxStats.get(i).value > measurement.value)
                {
                    measurement = tairMaxStats.get(i);
                }
            }
        }
        
        if (inParamId.contains("TA9M"))
        {
            for (int i = 0; i < ta9mMaxStats.size(); i++)
            {
                if (ta9mMaxStats.get(i).value > measurement.value)
                {
                    measurement = ta9mMaxStats.get(i);
                }
            }
        }
        
        if (inParamId.contains("SRAD"))
        {
            for (int i = 0; i < sradMaxStats.size(); i++)
            {
                if (sradMaxStats.get(i).value > measurement.value)
                {
                    measurement = sradMaxStats.get(i);
                }
            }
        }
        
        if (inParamId.contains("WSPD"))
        {
            for (int i = 0; i < wspdMaxStats.size(); i++)
            {
                if (wspdMaxStats.get(i).value > measurement.value)
                {
                    measurement = wspdMaxStats.get(i);
                }
            }
        }

        return measurement;
    }

    /**
     * Combined stats for days
     * @param paramId the parameter
     * @return String a string
     * @throws WrongParameterIdException exception
     */
    public String combineMinMaxStatistics(String paramId) throws WrongParameterIdException
    {       
        StatMeasurement maximumDay = getMaximumDay(paramId);
        StatMeasurement miniumuDay = getMinimumDay(paramId);
        return maximumDay.toString() + miniumuDay.toString();
    }
    
    
    
    /** 
     * The toString for DaysStatistics
     * @return String the tostring 
     */
    public String toString()
    {
        try
        {
            String header = "ID  STAT      VALUE  STID       DATE T TIME     TZ\n";
            String divider = "-----------------------------------------------------\n";

            String combinedString = header + divider 
                    + combineMinMaxStatistics("TAIR") 
                    + combineMinMaxStatistics("TA9M") 
                    + combineMinMaxStatistics("SRAD");
            
            return combinedString;
        } 
        catch (WrongParameterIdException e)
        {
            e.printStackTrace();
            return "Error in DaysStatistics";
        }
    }
}
