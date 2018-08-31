import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * MesonetTimeFile class
 * @author Jeffrey Glass
 * @version 3/30/2018
 *
 */
public class MesonetTimeFile extends CsFile
{
    private ArrayList<TimeData> data = new ArrayList<TimeData>();
    private HeaderDateTime headerDateTime;

    private static final int YEAR = 1;
    private static final int MONTH = 2;
    private static final int DAY = 3;
    private static final int MINUTE = 5;


    private int tairPosition = 4;
    private int ta9mPosition = 14;
    private int sradPosition = 13;
    private int minutePosition = 2;
    private int stidPosition = 0;

    /**
     * HeaderDaetTime class
     * @author Jeffrey Glass
     * @version 3/30/2018
     *
     */
    class HeaderDateTime
    {
        /** The year */
        public int year;
        /** The month */
        public int month;
        /** The day */
        public int day;
        /** The minute */
        public int minute;

        /**
         * Constructor
         * @param inYear year
         * @param inMonth month
         * @param inDay day
         * @param inMinute minute
         */
        HeaderDateTime(int inYear, int inMonth, int inDay, int inMinute)
        {
            year = inYear;
            month = inMonth;
            day = inDay;
            minute = inMinute;
        }
    }

    /**
     * Constructor
     * @param inFileName File name
     * @throws IOException exception
     * @throws WrongCopyrightException exception
     */
    MesonetTimeFile(String inFileName) throws IOException, WrongCopyrightException
    {
        super(inFileName);

        try
        {
            parseFile();
        }
        catch (IOException e)
        {
            throw new IOException("File not found.");
        }
        catch (WrongCopyrightException e)
        {
            throw new WrongCopyrightException();
        }
    }

    /**
     * Parses the data in the file
     * @return Arraylist of timeData
     * @throws IOException exception
     * @throws WrongCopyrightException exception
     * @throws NumberFormatException exception
     */
    public ArrayList<TimeData> parseFile() throws IOException, WrongCopyrightException
    {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String strg = null;

        // Copyright checker
        copyrightIsCorrect(br.readLine());
        // Date parsing
        parseDateTimeHeader(br.readLine());
        // Header parsing
        parseParamHeader(br.readLine());
        
        strg = br.readLine();
        
        while (strg != null)
        {
            parseData(strg);
            strg = br.readLine();
        }
        br.close();
        
        return data;
    }

    /**
     * Parses a single line of data from the file
     * @param line single data line from file
     */
    private void parseData(String line)
    {
        if (line != null)
        {
            String[] input = line.trim().split("\\s+");

            TimeData values = new TimeData(input[stidPosition], headerDateTime.year, 
                    headerDateTime.month, headerDateTime.day,
                    Integer.parseInt(input[minutePosition]),
                    new Measurement(Double.parseDouble(input[tairPosition])),
                    new Measurement(Double.parseDouble(input[ta9mPosition])),
                    new Measurement(Double.parseDouble(input[sradPosition])));
            data.add(values);
        }
    }

    /**
     * Get the list of parameters from the mts file header to establish an index of a column 
     * that has a given parameter
     * @param inParamStr parameter string from file
     */
    private void parseParamHeader(String inParamStr)
    {
        String[] parsedParam = inParamStr.split(" ");
    }

    /**
     * Checks to see if copyright is correct
     * @param inCopyrightStr string from file
     * 
     */
    private void copyrightIsCorrect(String inCopyrightStr) throws WrongCopyrightException
    {
        if (!inCopyrightStr.contains("101"))
        {
            throw new WrongCopyrightException();
        }
    }

    /**
     * Parse the start time that is used as the base for finding date-time of each of the measurements
     * @param inHeader header string
     */
    void parseDateTimeHeader(String inHeader)
    {
        try
        {
            inHeader = inHeader.trim();
            String[] parsedParam = inHeader.split(" ");
            int inYear = Integer.parseInt(parsedParam[YEAR]);
            int inMonth = Integer.parseInt(parsedParam[MONTH]);
            int inDay = Integer.parseInt(parsedParam[DAY]);
            int inMinute = Integer.parseInt(parsedParam[MINUTE]);
            this.headerDateTime = new HeaderDateTime(inYear, inMonth - 1, inDay, inMinute);
        }
        catch (NumberFormatException e)
        {
            System.out.println("not a number"); 
        }
    }

    /**
     * Returns a String that is created from the base parsed in parseDateTimeHeader()
     * @return string header in string
     */
    String getStarDateTimeStringFromFile()
    {
        String headerDateString = headerDateTime.year + " " + headerDateTime.month + 1 + " " + headerDateTime.day
                + " " + headerDateTime.minute;
        return headerDateString;
    }

    /**
     * Returns a String that has time of “now” – the time when you call it.
     * @return string current time
     */
    String getDateTimeString()
    {
        Calendar now = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat(CsAbstractFile.dateTimeFormat);
        String dateString = dateFormat.format(now.getTime());
        return dateString;
    }
}
