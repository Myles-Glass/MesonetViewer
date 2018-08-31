/**
 * MesonetTimeFile class
 * @author Jeffrey Glass
 * @version 3/30/2018
 *
 */
public class WrongCopyrightException extends Exception
{
	private static final long serialVersionUID = -3352808845495117276L;

	/**
	 * Constructor
	 */
	public WrongCopyrightException()
    {
        super("Invalid copyright detected");
    }

}
