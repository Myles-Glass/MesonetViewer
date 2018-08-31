
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.TimeZone;


/**
 * MesonetTimeFile class
 * @author Jeffrey Glass
 * @version 3/30/2018
 *
 */
public class StatMeasurement extends Measurement implements TimeComparable
{
    private GregorianCalendar dateTimeOfMeasurment;
    private String paramId;
    private StatType statType;
    private String stationId;

    /**
     * Constructor
     */
    public StatMeasurement()
    {
        //Empty constructor
    }

    /**
     * Constructor for the class
     * @param inValue value of the measurement
     * @param obsDateTime observed date
     * @param inStationId String station id
     * @param inParamId String paramater id
     * @param inStatType the stat type
     * 
     */
    public StatMeasurement(double inValue, GregorianCalendar obsDateTime, String inStationId, 
            String inParamId, StatType inStatType)
    {
        super(inValue);
        this.dateTimeOfMeasurment = obsDateTime;
        this.stationId = inStationId;
        this.paramId = inParamId;
        this.statType = inStatType;
    }
    
    /**
     * Getter for date of measurement
     * @return GregorianCalender date of measurement
     */
    public GregorianCalendar getDateTimeOfMeasurment()
    {
        return dateTimeOfMeasurment;
    }
    
    /**
     * setter for ParamID
     * @param inParamId String
     */
    public void setParamId(String inParamId)
    {
        this.paramId = inParamId;
    }
    
    
    /**
     * Getter for paramID
     * @return String paramID
     */
    public String getParamId()
    {
        return this.paramId;
    }
    
    
    /**
     * setter for stat type
     * @param type statType
     */
    public void setStatType(StatType type)
    {
        this.statType = type;
    }
    
    
    /**
     * Getter for type
     * @return StatType type
     */
    public StatType getStatType()
    {
        return statType;
    }

    /**
     * Compare this Measurement with another Measurement
     * 
     * @param compareWith
     *            Measurement to compare with
     * @return true if both Measurements are valid AND this is strictly smaller than s OR
     *         if this is valid and s is not valid
     */
    public boolean isLessThan(StatMeasurement compareWith)
    {
        if (this.isValid())
        {
            if (this.getValue() < compareWith.getValue())
            {
                return true;
            }
            else
            {
                return !compareWith.isValid();
            }
        }
        else
        {
            return !compareWith.isValid();
        }
    }

    /**
     * Compare this Measurement with another Measurement
     * 
     * @param compareWith
     *            Measurement to compare with
     * @return true if both Measurements are valid AND this is strictly larger than s OR
     *         if this is valid and s is not valid
     */
    public boolean isGreaterThan(StatMeasurement compareWith)
    {
        if (this.isValid())
        {
            if (this.getValue() > compareWith.getValue())
            {
                return true;
            }
            else
            {
                return compareWith.isValid();
            }
        }
        else
        {
            return !compareWith.isValid();
        }
    }

    /**
     * Checks to see if one date is newer than the other
     * 
     * @param inDateTime String 
     * @return boolean true or false
     */
    public boolean newerThan(String inDateTime) throws ParseException
    {
        return compareWithTimeString(inDateTime) > 0;
    }

    /**
     * Checks to see if one date is older than the other
     * 
     * @param inDateTime String
     * @return boolean true or false
     */
    public boolean olderThan(String inDateTime) throws ParseException
    {
        return compareWithTimeString(inDateTime) < 0;
    }


    
    /**
     * Comparator for time. 1 is newer, 0 is same time, -1 is older.
     * @param inDateTime The human-readable string
     * @return int comparator results
     * @throws ParseException For parsing
     */
    public int compareWithTimeString(String inDateTime) throws ParseException
    {
        int result = 0;
        String[] parseDate = inDateTime.split(" ");
        int inYear = Integer.parseInt(parseDate[0]);
        int inMonth = Integer.parseInt(parseDate[1]);
        int inDay = Integer.parseInt(parseDate[2]);
        int inMinute = Integer.parseInt(parseDate[3]);
        
        if (this.getDateTimeOfMeasurment().YEAR > inYear)
        {
            result = 1;
        }
        else if (this.getDateTimeOfMeasurment().YEAR == inYear)
        {
            if (this.getDateTimeOfMeasurment().MONTH > inMonth)
            {
                result = 1;
            }
            else if (this.getDateTimeOfMeasurment().MONTH == inMonth)
            {
                if (this.getDateTimeOfMeasurment().DAY_OF_MONTH > inDay)
                {
                    result = 1;
                }
                else if (this.getDateTimeOfMeasurment().DAY_OF_MONTH == inDay)
                {
                    if (this.getDateTimeOfMeasurment().MINUTE > inMinute)
                    {
                        result = 1;
                    }
                    else if (this.getDateTimeOfMeasurment().MINUTE == inMinute)
                    {
                        result = 0;
                    }
                    else if (this.getDateTimeOfMeasurment().MINUTE < inMinute)
                    {
                        result = -1;
                    }
                }
            }
        }
        return result;
    }

    /** 
     * toString method
     * @return String the to string
     */
    @Override
    public String toString()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(CsAbstractFile.dateTimeFormat);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dateString = dateFormat.format(getDateTimeOfMeasurment().getTime());

        
        
        return this.paramId + " " + this.statType + " " + this.value + " " + this.stationId + " " 
                + dateString + "\n";
    }
    
}
