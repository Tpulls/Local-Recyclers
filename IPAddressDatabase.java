// With File Management Code

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.SpringLayout;

// File Management-related Imports 
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;


/**
 * @author Mark O'Reilly
 */

public class IPAddressDatabase extends Frame implements ActionListener, WindowListener
{
    // SOME BACKGROUND INFORMATION:
    // An IPAddresses.txt data file has been added to the directory of this update.
    // If using NetBeans as your IDE, this file might be copied into the IPAddressDatabase project
    //     folder (within your Netbeans project folder), but placed outside the project's src folder.
    //     Note: Your IPAddressDatabase project folder MAY be located within the default Netbeans
    //           project folder - which may be within your 'My Documents' folder.
    // The IPAddressDatabase application will load all the data from the data file and
    //     place a copy into RAM memory (or equivalent).  Then the first record (line/entry)
    //     of this data will be displayed on screen.
    // Three arrays are being introduced in this update to store the data from the data file
    //     within RAM memory.


    // DECLARATIONS --------------------------------------------------------------------------

    int maxEntries = 100;     // Global variable to define the maximum size of the 3 arrays.
    int numberOfEntries = 0;  // Global variable to remember how many actual entries are currently in the 3 arrays.
    int currentEntry = 0;     // Global variable to remember which entry in the arrays we are currently focused on.
    
    // Declare the 3 arrays for storing the PC/IP data in memory - each has a maximum size of
	//         maxEntries (currently equal to 100 entries)
    String[] PCName = new String[maxEntries];   
    String[] PCID = new String[maxEntries];
    String[] IPAddresses = new String[maxEntries];

    Label lblPCName, lblPCID, lblIP, lblFind;
    TextField txtPCName, txtPCID, txtIP, txtFind;
    Button btnNew, btnSave, btnDel, btnFind, btnExit, btnFirst, btnPrev, btnNext, btnLast;

    // --------------------------------------------------------------------------------------


    public static void main(String[] args)
    {
        Frame myFrame = new IPAddressDatabase();
        myFrame.setSize(470,250);
        myFrame.setLocation(400, 200);
        myFrame.setResizable(false);
        myFrame.setVisible(true);
    }

    public IPAddressDatabase()
    {
        setTitle("IP Address Database");
        setBackground(Color.yellow);

        SpringLayout myLayout = new SpringLayout();
        setLayout(myLayout);
        
        LocateLabels(myLayout);
        LocateTextFields(myLayout);
        LocateButtons(myLayout);

        this.addWindowListener(this);
    }

    public void LocateLabels(SpringLayout myLabelLayout)
    {
        lblPCName = LocateALabel(myLabelLayout, lblPCName, "PC Name", 30, 25);
        lblPCID = LocateALabel(myLabelLayout, lblPCID, "PC ID", 30, 50);
        lblIP = LocateALabel(myLabelLayout, lblIP, "IP", 30, 75);
        lblFind = LocateALabel(myLabelLayout, lblFind, "Search", 30, 120);
    }

    public Label LocateALabel(SpringLayout myLabelLayout, Label myLabel, String  LabelCaption, int x, int y)
    {
        myLabel = new Label(LabelCaption);
        add(myLabel);        
        myLabelLayout.putConstraint(SpringLayout.WEST, myLabel, x, SpringLayout.WEST, this);
        myLabelLayout.putConstraint(SpringLayout.NORTH, myLabel, y, SpringLayout.NORTH, this);
        return myLabel;
    }
   
    public void LocateTextFields(SpringLayout myTextFieldLayout)
    {
        txtPCName  = LocateATextField(myTextFieldLayout, txtPCName, 20, 130, 25);
        txtPCID = LocateATextField(myTextFieldLayout, txtPCID, 20, 130, 50);
        txtIP = LocateATextField(myTextFieldLayout, txtIP, 20, 130, 75);
        txtFind = LocateATextField(myTextFieldLayout, txtFind, 20, 130, 120);
    }

    public TextField LocateATextField(SpringLayout myTextFieldLayout, TextField myTextField, int width, int x, int y)
    {
        myTextField = new TextField(width);
        add(myTextField);        
        myTextFieldLayout.putConstraint(SpringLayout.WEST, myTextField, x, SpringLayout.WEST, this);
        myTextFieldLayout.putConstraint(SpringLayout.NORTH, myTextField, y, SpringLayout.NORTH, this);
        return myTextField;
    }

    public void LocateButtons(SpringLayout myButtonLayout)
    {
        btnNew = LocateAButton(myButtonLayout, btnNew, "New", 320, 25, 80, 25);
        btnSave = LocateAButton(myButtonLayout, btnSave, "Save", 320, 50, 80, 25);
        btnDel = LocateAButton(myButtonLayout, btnDel, "Delete", 320, 75, 80, 25);
        btnFind = LocateAButton(myButtonLayout, btnFind, "Find", 320, 100, 80, 25);
        btnExit = LocateAButton(myButtonLayout, btnExit, "Exit", 320, 170, 80, 25);
        btnFirst = LocateAButton(myButtonLayout, btnFirst, "|<", 140, 170, 30, 25);
        btnPrev = LocateAButton(myButtonLayout, btnPrev, "<", 180, 170, 30, 25);
        btnNext = LocateAButton(myButtonLayout, btnNext, ">", 220, 170, 30, 25);
        btnLast = LocateAButton(myButtonLayout, btnLast, ">|", 260, 170, 30, 25);
    }

    public Button LocateAButton(SpringLayout myButtonLayout, Button myButton, String  ButtonCaption, int x, int y, int w, int h)
    {    
        myButton = new Button(ButtonCaption);
        add(myButton);
        myButton.addActionListener(this);
        myButtonLayout.putConstraint(SpringLayout.WEST, myButton, x, SpringLayout.WEST, this);
        myButtonLayout.putConstraint(SpringLayout.NORTH, myButton, y, SpringLayout.NORTH, this);
        myButton.setPreferredSize(new Dimension(w,h));
        return myButton;
    }

   public void actionPerformed(ActionEvent e)
    {
        // BUTTON FIRST
        if(e.getSource() == btnFirst)
        {
        }

        // BUTTON PREVIOUS
        if(e.getSource() == btnPrev)
        {
        }

        // BUTTON NEXT
        if (e.getSource()== btnNext)
        {
        }

        // BUTTON LAST
        if(e.getSource() == btnLast)
        {
        }

        // BUTTON NEW
        if(e.getSource() == btnNew)
        {
            txtPCName.setText("New button clicked...");
        }

        // BUTTON SAVE
        if(e.getSource() == btnSave)
        {
        }

        // BUTTON DELETE
        if(e.getSource()== btnDel)
        {
        }

        // BUTTON FIND
        if(e.getSource() == btnFind)
        {   
        }
        
        // BUTTON EXIT
        if(e.getSource() == btnExit)
        {
            // Call the method below that writes the data back to the data file
            writeFile();
            System.exit(0);
        }
           
    }

    public void windowClosing(WindowEvent we)
    {
	// Call the method below that writes the data back to the data file
        writeFile();
        System.exit(0);
    }

    public void windowIconified(WindowEvent we)
    {
    }

    public void windowOpened(WindowEvent we)
    {
	// Call the method below that reads the data from the data file (when the Frame first opens)
        readFile();
	// Display the first data entry (record) in the Frame
        displayEntry(currentEntry);
    }

    public void windowClosed(WindowEvent we)
    {
    }

    public void windowDeiconified(WindowEvent we)
    {
    }

    public void windowActivated(WindowEvent we)
    {
    }

    public void windowDeactivated(WindowEvent we)
    {
    }


    // NEW METHODS: --------------------------------------------------------------------------------

    // Display the required data entry (record) in the Frame
    // The calling method must specify the number (index) of the entry that this
    //     method needs to currently display on screen.
    public void displayEntry(int index)
    {
        // Take the required entry from the PCName array and display it
        //      in the txtPCName TextField.
        txtPCName.setText(PCName[index]);
        txtPCID.setText(PCID[index]);
        txtIP.setText(IPAddresses[index]);
    }

    
    // Take the current record displayed on screen and save it back into the 'currentEntry' position
    //      of the 3 arrays.
    public void saveEntry(int index)
    {
        // Take the current entry in the txtPCName TextField (on screen) and copy it 
        //      into the appropriate (currentEntry) position of the PCName array.
        PCName[index] = txtPCName.getText();
        PCID[index] = txtPCID.getText();
        IPAddresses[index] = txtIP.getText();
		
        // (If required) Call the method below that writes the data back to the data file.
        writeFile();
    }

        
    // Read in the data from the data file - IPAddresses.txt - one line at a time and store in the 3 arrays.
    // Remember the number of entries read in, in the global variable: numberOfEntries.
    public void readFile()
    {
        // Try to read in the data and if an exception occurs go to the Catch section 
        try
        {
            // Set up vaious streams for reading in the content of the data file.
            FileInputStream fstream = new FileInputStream("IPAddresses.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            
            int i = 0;   // i is used as the line counter
            String line; // line is used to temporarily store the line read in from the data file
			
            // Read a line from the data file into the buffer and then check whether
            //      it is null.  The while loop continues until a line read in is null.
            while ((line = br.readLine()) != null)
            {
                // Split the line of data (from the text file) and put each entry into the
                //                                             temporary array - temp[]
                String[] temp = line.split(",");

                // Save each entry into its respective array:
                PCName[i] = temp[0];      //Takes the first entry in temp and puts it in the PCName array at the current location
                PCID[i] = temp[1];        //Takes the second entry in temp and puts it in the PCID array at the current location
                IPAddresses[i] = temp[2]; //Takes the third entry in temp and puts it in the IPAddress array at the current location

                i++;  // Increment i so we can keep a count of how many entries have been read in.
            }

            numberOfEntries = i;   // Set numberOfEntries equal to i, so as to remember how many entries are now in the arrays 

            br.close();            // Close the BufferedReader
            in.close();            // Close the DataInputStream
            fstream.close();       // Close the FileInputStream
        }
        catch (Exception e)
        {
            // If an exception occurs, print an error message on the console.
            System.err.println("Error Reading File: " + e.getMessage());
        }
    }

    
    // Write the data back out to the data file - one line at a time
    // Note: You may wish to use a different data file name while initially
    //       developing, so as not to accidently corrupt your input file.
    public void writeFile()
    {
        // Try to print out the data and if an exception occurs go to the Catch section 
        try
        {
            // After testing has been completed, replace the hard-coded filename: "IPAddresses_New.txt"
            //       with the parameter variable: fileName 
            // Set up a PrintWriter for printing the array content out to the data file.
            PrintWriter out = new PrintWriter(new FileWriter("IPAddresses_New.txt"));
            
            // Print out each line of the array into your data file.
            // Each line is printed out in the format:  PCName,PCID,IPAddress
            for(int m = 0; m < numberOfEntries; m++){
                out.println(PCName[m] +"," +PCID[m] + "," + IPAddresses[m]);
            }

            // Close the printFile (and in so doing, empty the print buffer)
             out.close();
        }
        catch (Exception e)
        {
            // If an exception occurs, print an error message on the console.
            System.err.println("Error Writing File: " + e.getMessage());
        }
    }
  
}
