import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for MesonetTimeFile
 * @author Jeffrey Glass
 * @version 2018-03-31
 *
 */
public class MesonetTimeFileTest
{

    /**
     * Test the parse date
     * @throws IOException exeption
     * @throws WrongCopyrightException exception
     */
    @Test
    public void parseDateTimeHeaderTest() throws IOException, WrongCopyrightException
    {
        MesonetTimeFile mts = new MesonetTimeFile("data/mesonet/20180103okcn.mts");
        
        Assert.assertEquals("2018 01 3 0", mts.getStarDateTimeStringFromFile());
    }
}
