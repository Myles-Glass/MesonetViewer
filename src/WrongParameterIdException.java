/**
 * MesonetTimeFile class
 * @author Jeffrey Glass
 * @version 3/30/2018
 *
 */
public class WrongParameterIdException extends Exception
{
    private static final long serialVersionUID = 7394973112258653626L;

    /**
     * Constructor
     */
    public WrongParameterIdException()
    {
        super("Invalid parameterID detected");
    }
    
    /**
     * Constructor
     * @param msg String
     */
    public WrongParameterIdException(String msg)
    {
        super(msg + " Invalid parameterID detected");
    }
}
