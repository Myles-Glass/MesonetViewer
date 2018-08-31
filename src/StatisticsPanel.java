import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * StatisticsPanel class
 * @author Jeffrey Glass
 * @version 5/6/2018
 *
 */
public class StatisticsPanel extends JPanel
{
    /** Serial */
    private static final long serialVersionUID = -5778130703074619169L;
    /** String for max button */
    public final static String MAX_BUTTON = "MAXIMUM";
    /** String for min button */
    public final static String MIN_BUTTON = "MINIMUM";
    /** Max radio button */
    private JRadioButton maxButton;
    /** Min radio button */
    private JRadioButton minButton;
    /** Radio button group */
    private ButtonGroup bg;
    
    /**
     * Constructor for the class
     */
    public StatisticsPanel()
    {
        this.setLayout(new GridLayout(0,1));
        minButton = new JRadioButton(MIN_BUTTON);
        maxButton = new JRadioButton(MAX_BUTTON);
        minButton.setBackground(new Color(153, 204, 210));
        maxButton.setBackground(new Color(153, 204, 210));
        
        bg = new ButtonGroup();
        bg.add(minButton);
        bg.add(maxButton);
        this.add(minButton);
        this.add(maxButton);
        
        
        //Titled Border
        TitledBorder title = BorderFactory.createTitledBorder
                (BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Statistics");
        this.setBorder(title);

        setBackground(new Color(153, 204, 210));
    }
    
    
    /**
     * Getter for statistics type
     * @return String String result
     */
    public String getStatisticsType()
    {
        String result = null;
        if (bg.getSelection() == maxButton.getModel())
        {
            result = "MAXIMUM";
        }
        if (bg.getSelection() == minButton.getModel())
        {
            result = "MINIMUM";
        }
        return result;
    }
}
