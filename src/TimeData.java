import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * 
 * @author Jeffrey Glass
 * @version 2018-01-01
 * 
 *
 */
public class TimeData
{
    /**Time and date of measurement */
    private GregorianCalendar measurementDateTimeUTC = new GregorianCalendar();
    
    /** ID of the station */
    private String stationID;
    
    /** tair value of the data */
    private Measurement tair;
    
    /** ta9m value of the data */
    private Measurement ta9m;
    
    /** solar radiation value of the data */
    private Measurement solarRadiation;

    /**
     * The constructor for TimeData
     * @param stationID ID of the station
     * @param year Year of the data
     * @param month Month of the data
     * @param day Day of the data
     * @param minute Minute of the data
     * @param tair tair of the data
     * @param ta9m ta9m of the data
     * @param solarRadiation Solar radiation of the data
     */
    public TimeData(String stationID, int year, int month, int day, int minute, Measurement tair,
            Measurement ta9m, Measurement solarRadiation)
    {
        this.stationID = stationID;
        setDateTimeComponents(year, month, day, minute);
        setMeasurements(tair, ta9m, solarRadiation);
    }
    
    /**
     * The constructor for TimeData
     * @param stationID ID of the station
     * @param dateTime GregorianCalendar
     * @param tair tair of the data
     * @param ta9m ta9m of the data
     * @param solarRadiation Solar radiation of the data
     */
    public TimeData(String stationID, GregorianCalendar dateTime, Measurement tair,
            Measurement ta9m, Measurement solarRadiation)
    {
        this.stationID = stationID;
        this.measurementDateTimeUTC = dateTime;
        this.tair = tair;
        this.ta9m = ta9m;
        this.solarRadiation = solarRadiation;
    }
    
    /**
     * Getter for calendar
     * @return GregorianCalendar the calendar
     */
    public GregorianCalendar getMeasurementDateTime()
    {
        return this.measurementDateTimeUTC;
    }
    
    
    /**
     * Setter for calendar
     * @param year The year
     * @param month The month
     * @param day The day
     * @param minute The minute
     */
    private void setDateTimeComponents(int year, int month, int day, int minute)
    {
        this.measurementDateTimeUTC.set(year, month, day);
        this.measurementDateTimeUTC.set(Calendar.MINUTE, minute);
        this.measurementDateTimeUTC.set(Calendar.SECOND, 0);
    }
    
    /**
     * Setter for measurements
     * @param inTair tair measurement
     * @param inTa9m ta9m measurement
     * @param inSolarRadiation solarRadiation measurement
     */
    private void setMeasurements(Measurement inTair, Measurement inTa9m, Measurement inSolarRadiation)
    {
        this.tair = inTair;
        this.ta9m = inTa9m;
        this.solarRadiation = inSolarRadiation;
    }
    
    /**
     * Getter for minute
     * @return minute minute value
     */
    public int getMinute()
    {
        return measurementDateTimeUTC.MINUTE;
    }

    /**
     * Getter for day
     * @return day day value
     */
    public int getDay()
    {
        return measurementDateTimeUTC.DAY_OF_MONTH;
    }

    /**
     * Getter for month
     * @return month month value
     */
    public int getMonth()
    {
        return measurementDateTimeUTC.MONTH;
    }
    
    /**
     * Getter for year
     * @return year year value
     */
    public int getYear()
    {
        return measurementDateTimeUTC.YEAR;
    }

    /**
     * Getter for stationID
     * @return stationID ID of station
     */
    public String getStationID()
    {
        return stationID;
    }

    /**
     * Getter for ta9m
     * @return ta9m ta9m value
     */
    public Measurement getTa9m()
    {
        return ta9m;
    }

    /**
     * Getter for solarRadiation
     * @return solarRadiation Solar radiation value
     */
    public Measurement getSolarRadiation()
    {
        return solarRadiation;
    }
    
    /**
     * Getter for tair
     * @return tair tair value
     */
    public Measurement getTair()
    {
        return tair;
    }
}
