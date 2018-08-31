import java.text.ParseException;
/**
 * StatType class
 * @author Jeffrey Glass
 * @version 3/30/2018
 *
 */
public interface TimeComparable 
{
    /**
     * Newer than method
     * @param inDateTimeStr String
     * @return boolean true or false
     * @throws ParseException exception
     */
    boolean newerThan(String inDateTimeStr) throws ParseException;
    
    /**
     * Older than method
     * @param inDateTimeStr String
     * @return boolean true or false
     * @throws ParseException exception
     */
    boolean olderThan(String inDateTimeStr) throws ParseException;
}