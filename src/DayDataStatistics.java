import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.EnumMap;
import java.util.HashMap;


/**
 * DayDataStatistics class
 * @author Jeffrey Glass
 * @version 3/30/2018
 *
 */
public class DayDataStatistics
{
    /** The set of data. */
    private ArrayList<TimeData> data = new ArrayList<TimeData>();

    /** The TAIR EnumMap */
    private EnumMap<StatType, StatMeasurement> tairEnumMap = 
            new EnumMap<StatType, StatMeasurement>(StatType.class);
    
    /** The TA9M EnumMap */
    private EnumMap<StatType, StatMeasurement> ta9mEnumMap = 
            new EnumMap<StatType, StatMeasurement>(StatType.class);
    
    /** The SRAD EnumMap */
    private EnumMap<StatType, StatMeasurement> sradEnumMap = 
            new EnumMap<StatType, StatMeasurement>(StatType.class);
    
    /** The HashMap */
    private HashMap<String, EnumMap<StatType, StatMeasurement>> paramStats = 
            new HashMap<String, EnumMap<StatType, StatMeasurement>>();

    /** The station ID */
    private String stationId = "nada";

    /**
     * Constructor
     * @param inData array list
     */
    public DayDataStatistics(ArrayList<TimeData> inData)
    {
        data = inData;
        this.stationId = inData.get(0).getStationID();

        calculateAirTemperatureStatistics("TAIR");
        calculateAirTemperatureStatistics("TA9M");
        calculateSolarRadiationStatistics();
    }

    /**
     * Calculates air temps
     * @param tairName name of param
     */
    private void calculateAirTemperatureStatistics(String tairName)
    {
        // These variables represent the "best so far" for min and max.
        // By setting these these to the largest and smallest possible
        // values, we ensure that the first time a valid Measurement is
        // found, it will replace these values

        // Accumulator and counter for computing average
        //double sum = 0;
        //int numberOfValidObservations = 0;

        GregorianCalendar minCalendar = new GregorianCalendar();
        GregorianCalendar maxCalendar = new GregorianCalendar();
        
       // FINISH IMPLEMENTATION
        //The loop for tair
        if (tairName.contains("TAIR"))
        {
            double total = 0;
            StatMeasurement compareStat = new StatMeasurement();
            
            //Sets min and max to impossible levels for comparing
            StatMeasurement tairMin = new StatMeasurement(800.0, minCalendar, stationId, tairName, StatType.MIN);
            StatMeasurement tairMax = new StatMeasurement(-800.0, maxCalendar, stationId, tairName, StatType.MAX);
            StatMeasurement tairAverage = new StatMeasurement(-800.0, maxCalendar, stationId, tairName, StatType.AVG);
            
            for (int i = 0; i < data.size(); i++)
            {
                //Grabs a single value
                compareStat.value = data.get(i).getTair().getValue();
                
                //Adds singleValue to the total for averaging
                total = compareStat.value + total;
                
                //Finds minimum
                if (compareStat.value < tairMin.value)
                {
                    minCalendar = data.get(i).getMeasurementDateTime();
                    
                    tairMin = new StatMeasurement(compareStat.value, 
                            minCalendar, stationId, tairName, StatType.MIN);
                    
                    tairEnumMap.put(StatType.MIN, tairMin);
                }
                
                //Finds maximum
                if (compareStat.value > tairMax.value)
                {
                    maxCalendar = data.get(i).getMeasurementDateTime();
                    tairMax = new StatMeasurement(compareStat.value, 
                            maxCalendar, stationId, tairName, StatType.MAX);
                    
                    tairEnumMap.put(StatType.MAX, tairMax);
                }
            }
            tairAverage.value = total / data.size();
            tairEnumMap.put(StatType.AVG, tairAverage);
            paramStats.put("TAIR", tairEnumMap);
        }
        
        //The loop for ta9m
        

        if (tairName.contains("TA9M"))
        {
            double total = 0;
            StatMeasurement compareStat = new StatMeasurement();
            
            //Sets min and max to impossible levels for comparing
            StatMeasurement ta9mMin = new StatMeasurement(800.0, minCalendar, stationId, tairName, StatType.MIN);
            StatMeasurement ta9mMax = new StatMeasurement(-800.0, maxCalendar, stationId, tairName, StatType.MAX);
            StatMeasurement ta9mAverage = new StatMeasurement(-800.0, maxCalendar, stationId, tairName, StatType.AVG);
            
            for (int i = 0; i < data.size(); i++)
            {
                //Grabs a single value
                compareStat.value = data.get(i).getTa9m().getValue();
                
                //Adds singleValue to the total for averaging
                total = compareStat.value + total;
                
                //Finds minimum
                if (compareStat.value < ta9mMin.value)
                {
                    minCalendar = data.get(i).getMeasurementDateTime();
                    ta9mMin = new StatMeasurement(compareStat.value, minCalendar, stationId, 
                            tairName, StatType.MIN);
                    
                    ta9mEnumMap.put(StatType.MIN, ta9mMin);
                }
                
                //Finds maximum
                if (compareStat.value > ta9mMax.value)
                {
                    maxCalendar = data.get(i).getMeasurementDateTime();
                    ta9mMax = new StatMeasurement(compareStat.value, maxCalendar, stationId,
                            tairName, StatType.MAX);
                    
                    ta9mEnumMap.put(StatType.MAX, ta9mMax);
                }
            }
            ta9mAverage.value = total / data.size();
            ta9mEnumMap.put(StatType.AVG, ta9mAverage);
            paramStats.put("TA9M", ta9mEnumMap);
        }
    }

    /**
     * Compute and fill in the solar radiation-related statistics
     * (solarRadiationMin, solarRadiationMax, solarRadiationAverage, and
     * solarRadiationTotal).
     * <P>
     * Notes:
     * <UL>
     * <LI>Only valid Measurements can be used in these computations
     * <LI>You may assume that every month has at least one valid Measurement
     * </UL>
     */
    private void calculateSolarRadiationStatistics()
    {
        double total = 0;
        StatMeasurement compareStat = new StatMeasurement();
        
        GregorianCalendar minCalendar = new GregorianCalendar();
        GregorianCalendar maxCalendar = new GregorianCalendar();
        //Sets min and max to impossible levels for comparing
        StatMeasurement solarRadiationMin = new 
                StatMeasurement(800.0, minCalendar, stationId, "SRAD", StatType.MIN);
        StatMeasurement solarRadiationMax = new 
                StatMeasurement(-800.0, maxCalendar, stationId, "SRAD", StatType.MAX);
        StatMeasurement solarRadiationAverage = new 
                StatMeasurement(-800.0, maxCalendar, stationId, "SRAD", StatType.MAX);
        StatMeasurement solarRadiationTotal = new 
                StatMeasurement(-800.0, maxCalendar, stationId, "SRAD", StatType.MAX);
        
        for (int i = 0; i < data.size(); i++)
        {
            //Grabs a single value
            compareStat.value = data.get(i).getSolarRadiation().getValue();
            
            //Adds singleValue to the total for averaging
            total = compareStat.value + total;
            
            //Finds minimum
            if (compareStat.value < solarRadiationMin.value)
            {
                minCalendar = data.get(i).getMeasurementDateTime();
                solarRadiationMin = new StatMeasurement(compareStat.value, minCalendar, 
                        stationId, "SRAD", StatType.MIN);
                
                sradEnumMap.put(StatType.MIN, solarRadiationMin);
            }
            
            //Finds maximum
            if (compareStat.value > solarRadiationMax.value)
            {
                maxCalendar = data.get(i).getMeasurementDateTime();
                solarRadiationMax = new StatMeasurement(compareStat.value, maxCalendar, 
                        stationId, "SRAD", StatType.MAX);
                
                sradEnumMap.put(StatType.MAX, solarRadiationMax);
            }
        }
        solarRadiationTotal.value = total;
        solarRadiationAverage.value = total / data.size();
        
        sradEnumMap.put(StatType.TOT, solarRadiationTotal);
        sradEnumMap.put(StatType.AVG, solarRadiationAverage);
        paramStats.put("SRAD", sradEnumMap);

    }
    
    /**
     * Getter for any statistic
     * @param param the parameter
     * @param statType the statistic type
     * @return result StatMeasurement
     */
    public StatMeasurement getStatistic(String param, StatType statType)
    {
        StatMeasurement result = new StatMeasurement();
        result = paramStats.get(param).get(statType);
        return result;
    }

    /**
     * @return station ID
     */
    public String getStationID()
    {
        return stationId;
    }

    /**
     * Describe DayStatistics
     * 
     * @return A string describing the statistics for the day
     */
    public String toString()
    {
        String date = data.get(0).getYear() + "-" + data.get(0).getMonth() + "-" + data.get(0).getDay();
        return String.format("%s, %s: \n"
                + "Air temperature[1.5m] = [%.4f, %.4f, %.4f],\n"
                + "Air temperature[9m] = [%.4f, %.4f, %.4f],\n"
                + "Solar Radiation = [%.4f, %.4f, %.4f, %.4f]",
                date, stationId, getStatistic("TAIR", StatType.MIN).value, 
                getStatistic("TAIR", StatType.AVG).value, 
                getStatistic("TAIR", StatType.MAX).value, 
                getStatistic("TA9M", StatType.MIN).value, 
                getStatistic("TA9M", StatType.AVG).value, 
                getStatistic("TA9M", StatType.MAX).value, 
                getStatistic("SRAD", StatType.MIN).value, 
                getStatistic("SRAD", StatType.AVG).value, 
                getStatistic("SRAD", StatType.MAX).value, 
                getStatistic("SRAD", StatType.TOT).value);

    }
}
