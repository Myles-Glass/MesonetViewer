import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class DataPanel extends JPanel
{
    /**
     * 
     */
    private static final long serialVersionUID = 5777740971062336138L;

    private JTextArea resultDescription;
    
    public DataPanel()
    {
        final int COLUMN_FIELD_WIDTH = 40;
        final int COLUMN_FIELD_HEIGHT = 7;
        
        resultDescription = new JTextArea();
        
        resultDescription.setColumns(COLUMN_FIELD_WIDTH);
        resultDescription.setRows(COLUMN_FIELD_HEIGHT);
        resultDescription.setWrapStyleWord(true);
        resultDescription.setLineWrap(true);
        this.add(resultDescription);
        setBackground(new Color(2, 126, 218));
        
        TitledBorder title = BorderFactory.createTitledBorder
                (BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Output");
        this.setBorder(title);
    }
    
	
    public synchronized void updateData(String result)
    {
        resultDescription.setText(result);
    }
    
    

    
}
