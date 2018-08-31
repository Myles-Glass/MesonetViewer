/**
 * StatisticsAbstract class
 * @author Jeffrey Glass
 * @version 3/30/2018
 *
 */
public abstract class StatisticsAbstract 
{    
    /**
     * Abstract method for min day
     * @param inParamId paramter
     * @return StatMeasurement measurement
     * @throws WrongParameterIdException exception
     */
    public abstract StatMeasurement getMinimumDay(String inParamId) throws WrongParameterIdException;
    
    /**
     * Abstract method for max day
     * @param inParamId paramter
     * @return StatMeasurement measurement
     * @throws WrongParameterIdException exception
     */
    public abstract StatMeasurement getMaximumDay(String inParamId) throws WrongParameterIdException;   

}
