import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MesonetFrame extends JFrame
{

    /**
     * Serial
     */
    private static final long serialVersionUID = 1L;

    /** Menu bar */
    private FileMenuBar fileMenuBar;

    private StatisticsPanel statistics;
    private ParameterPanel parameters;
    private DataPanel dataPanel;
    private MesonetMainPanel banner;
    private JPanel buttonPanel;
    private JButton calcButton;
    private JButton exitButton;

    public MesonetFrame()
    {
        super("Mesonet Calculator");
        setLayout(new BorderLayout());
        
        //Strictly to attach the File Menu to the top
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());
        fileMenuBar = new FileMenuBar();
        menuPanel.add(fileMenuBar, BorderLayout.NORTH);
        this.add(menuPanel, BorderLayout.NORTH); //Adds menuPanel to the frame
        
        //The rest of the gui
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        //Adds the banner
        banner = new MesonetMainPanel();
        mainPanel.add(banner, BorderLayout.NORTH);
        
        //Adds the statistics
        statistics = new StatisticsPanel();
        mainPanel.add(statistics, BorderLayout.WEST);
        
        //Adds the parameters
        parameters = new ParameterPanel();
        mainPanel.add(parameters, BorderLayout.CENTER);
        
        //Adds the dataPanel
        dataPanel = new DataPanel();
        mainPanel.add(dataPanel, BorderLayout.EAST);
        
        buildButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        
        //Adds mainPanel to the frame
        this.add(mainPanel, BorderLayout.CENTER);
        
        setSize(636, 260);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Builds the button panel
     */
    private void buildButtonPanel()
    {
        // Create a panel for buttons.
        buttonPanel = new JPanel();
        calcButton = new JButton("Calculate");
        exitButton = new JButton("Exit");
        
        buttonPanel.add(calcButton);
        buttonPanel.add(exitButton);
        
        buttonPanel.setBackground(new Color(102, 178, 210));

        // Register the action listeners
        calcButton.addActionListener(new CalcButtonListner(){});
        exitButton.addActionListener(new ExitButtonListner(){});

    }

    /**
     * @author Jeffrey Glass
     * 
     * Exit button listener
     *
     */
    private class ExitButtonListner implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);

        }

    }

    /**
     * @author Jeffrey Glass
     * 
     * Calc button listener
     *
     */
    private class CalcButtonListner implements ActionListener 
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            DaysStatistics daysData;
            String result = "";
            
            String[] fileArray = fileMenuBar.getFileList().toArray(new String[0]);
            try
            {
                daysData = new DaysStatistics(fileArray);
                
                if (statistics.getStatisticsType().contains("MINIMUM"))
                {
                    for (int i = 0; i < parameters.getParamIds().size(); i++)
                    {
                        result += daysData.getMinimumDay(parameters.getParamIds().get(i)).toString();
                    }
                }
                
                if (statistics.getStatisticsType().contains("MAXIMUM"))
                {
                    for (int i = 0; i < parameters.getParamIds().size(); i++)
                    {
                        result += daysData.getMaximumDay(parameters.getParamIds().get(i)).toString();
                    }
                }
                
            } catch (IOException e1)
            {
                System.out.println("File not found.");
            } catch (WrongCopyrightException e1)
            {
                System.out.println("Wrong copyright.");
            } catch (ParseException e1)
            {
                System.out.println("Invalid parsing.");
            } catch (WrongParameterIdException e1)
            {
                System.out.println("Invalid parameter ID.");
            }

            MesonetFrame.this.dataPanel.updateData(result);

        }

    }

    ///////////////////////////////////////////////////////////////////
    /**
     * 
     * @author modified by Jeffrey Glass
     * @version 2018-x-x
     * 
     *          Menu bar that provides file loading and program exit capabilities.
     *
     */
    public class FileMenuBar extends JMenuBar
    {
        // Menu on the menu bar
        private JMenu menu;

        // Two options for the menu
        private JMenuItem menuOpen;
        private JMenuItem menuExit;

        // Reference to a file chooser pop-up
        private JFileChooser fileChooser;

        private ArrayList<String> listOfFiles;

        /**
         * Constructor: fully assemble the menu bar and attach the necessary action
         * listeners.
         */
        public FileMenuBar()
        {
            listOfFiles = new ArrayList<>();
            // Create the menu and add it to the menu bar
            menu = new JMenu("File");
            add(menu);

            // Create the menu items and add them to the menu
            menuOpen = new JMenuItem("Open Data File");
            menuOpen.setName("Menu Open");
            menuExit = new JMenuItem("Exit");
            menu.add(menuOpen);
            menu.add(menuExit);

            // Action listener for exit
            menuExit.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    System.exit(0);
                }
            });

            // Create the file chooser
            fileChooser = new JFileChooser(new File("./data/mesonet"));
            fileChooser.setMultiSelectionEnabled(true);

            // Action listener for file open
            menuOpen.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    // Ask for files
                    int returnVal = fileChooser.showOpenDialog(menuOpen);
                    // Did we get one?
                    if (returnVal == JFileChooser.APPROVE_OPTION)
                    {
                        // Yes
                        File[] files = fileChooser.getSelectedFiles();
                        // System.out.println(files.length);
                        try
                        {
                            for (File file : files)
                            {
                                String fileName = file.toString();
                                System.out.println(fileName);
                                listOfFiles.add(fileName);
                            }
                        }
                        catch (Exception e2)
                        {
                            // Catch all other exceptions
                            JOptionPane.showMessageDialog(fileChooser, "File load error");
                            MesonetFrame.this.setCursor(null);
                        }
                    }
                    else
                    {
                        System.out.println("No files.");
                    }
                }
            });

        }

        public ArrayList<String> getFileList()
        {
            return (ArrayList<String>) listOfFiles.clone();
        }
    }

}
