/**
 * MesonetTimeFile class
 * @author Jeffrey Glass
 * @version 3/30/2018
 *
 */
public class WrongTimeZoneException extends Exception
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public WrongTimeZoneException()
    {
        super("Invalid time zone detected, should be UTC");
        // default implementation ignored
    }
}
