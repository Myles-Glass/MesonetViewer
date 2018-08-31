import java.text.ParseException;

/**
 * CSFile class
 * @author Jeffrey Glass
 * @version 3/30/2018
 *
 */
public class CsFile extends CsAbstractFile
{
    /**
     * Constructor
     * @param inFileName file name
     */
    public CsFile(String inFileName)
    {
        super(inFileName);
    }
    
    /**
     * getter for filename
     * @return String filename
     */
    public String getFileName()
    {
        return super.fileName;
    }


    /**
     * Checks to see if newer than
     * 
     * @param inDateTime String
     * @return boolean true or false
     */
    public boolean newerThan(String inDateTime) throws ParseException
    {
        return compareWithTimeString(inDateTime) == 1 ? true : false;
    }

    /**
     * Checks to see if older than
     * 
     * @param inDateTime String
     * @return boolean true or false
     */
    public boolean olderThan(String inDateTime) throws ParseException
    {
        return compareWithTimeString(inDateTime) == -1 ? true : false;
    }

    /**
     * Comparator
     * 
     * @param inDateTime String
     * @return int 1, 0, or -1
     */
    @Override
    public int compareWithTimeString(String inDateTime) throws ParseException
    {
        return -1;
    }
}