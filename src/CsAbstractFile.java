import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;

/**
 * Project 2
 * 
 * @author Jeffrey Glass
 * @version 3-28-18
 *
 * Abstract file class
 */
public abstract class CsAbstractFile implements TimeComparable
{
    /** The file object */
    protected File file;
    
    /** The date format */
    protected DateFormat dateFormat;
    
    /** Time format used for strings */
    protected static String dateTimeFormat = "yyyy-MM-dd'T'HH:mm:ss z";
    
    /** File name */
    protected String fileName;
    
 
    /**
     * Constructor
     * @param inFileName file name
     */
    protected CsAbstractFile(String inFileName)
    {
        this.file = new File(inFileName);
        this.fileName = inFileName;
    }
    
    /**
     * Checks whether the file exists
     * @return boolean checks existence
     */
    public boolean exists()
    {
        return file.exists();
    }
    
    /**
     * Returns the last modification
     * @return long last modification of the file
     */
    public long getDateModified()
    {
        return file.lastModified();
    }
    
    /** toString of the class
     * @return String the tostring
     */
    @Override
    public String toString()
    {
        return file.toString();
    }

    /**
     * Comparator
     * @param inDateTimeStr string for datetime
     * @return int comparator
     * @throws ParseException for parsing
     */
    public abstract  int compareWithTimeString(String inDateTimeStr) throws ParseException;
}
