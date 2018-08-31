import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * ParameterPanel class
 * @author Jeffrey Glass
 * @version 5/6/2018
 *
 */
public class ParameterPanel extends JPanel
{
    /**
     * 
     */
    private static final long serialVersionUID = 4236142307230121244L;
    public final String TAIR = "TAIR";
    public final String TA9M = "TA9M";
    public final String SRAD = "SRAD";
    public final String WSPD = "WSPD";
    
    // Check boxes for the available parameters
    private JCheckBox airTemp;
    private JCheckBox ta9m;
    private JCheckBox srad;
    private JCheckBox wspd;
    
    public ParameterPanel()
    {
        System.out.println("Building Parameter panel");
        
        //Titled Border
        TitledBorder title = BorderFactory.createTitledBorder
                (BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Param");
        this.setBorder(title);
        

        // Create a GridLayout Manager
        setLayout(new GridLayout(4,1));
        setBackground(new Color(153, 204, 210));

        airTemp = new JCheckBox(TAIR);
        ta9m = new JCheckBox(TA9M);
        srad = new JCheckBox(SRAD);
        wspd = new JCheckBox(WSPD);
        airTemp.setBackground(new Color(153, 204, 210));
        ta9m.setBackground(new Color(153, 204, 210));
        srad.setBackground(new Color(153, 204, 210));
        wspd.setBackground(new Color(153, 204, 210));
        add(airTemp);
        add(ta9m);
        add(srad);
        add(wspd);
        
        
    }
    
    public ArrayList<String> getParamIds()
    {
	// create ArrayList<String> to hold selected parameters
        ArrayList<String> paramSelected = new ArrayList<String>();
        if (airTemp.isSelected())
        {
            paramSelected.add(TAIR);
        }
        
        if (ta9m.isSelected())
        {
            paramSelected.add(TA9M);
        }
        
        if (srad.isSelected())
        {
            paramSelected.add(SRAD);
        }
        
        if (wspd.isSelected())
        {
            paramSelected.add(WSPD);
        }
        
        return paramSelected;
    }

}
