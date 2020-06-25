package LocalRecyclers;

// With Sample Modularisation
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.SpringLayout;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.plaf.synth.Region;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;

// Import (include) the java library for managing array sorting 
/**
 * @author Thomas Pullar
 */
// Frame details the window and implements WindowListener for button response
public class LocalRecyclers extends Frame implements ActionListener, WindowListener {
    // Declaring the variables

    Label lblTitle, lblBusinessName, lblAddress, lblPhone, lblWebsite, lblRecycles, lblFind, lblRecyclersList;
    TextField txtBusinessName, txtAddress, txtPhone, txtWebsite, txtFind, txtFilterBy;
    TextArea txtRecycles, txtTextArea;
    Button btnNew, btnSave, btnDel, btnFind, btnExit, btnFirst, btnPrev, btnNext, btnLast, btnSortBusn, btnSearchBusn, btnFilter;
    // Global variable to define the maximum size of the 3 arrays.
    int maxEntries = 100;
    // Global variable to remember how many actual entries are currently in the 3 arrays.
    int numberOfEntries = 0;
    // Global variable to remember which entry in the arrays we are currently focused on.
    int currentEntry = 0;
    // Declare the 3 arrays for storing the PC/IP data in memory - each has a maximum size of
    //         maxEntries (currently equal to 100 entries)
    String[] BusinessName = new String[maxEntries];
    String[] Address = new String[maxEntries];
    String[] Phone = new String[maxEntries];
    String[] Website = new String[maxEntries];
    String[] Recycles = new String[maxEntries];
    String[] sortArray = new String[maxEntries];
    String dataFileName = "LocalRecyclers.txt";

    // Declaration and instantiation of an array of objects for storing the PC/IP data in memory -
    //                 based on the class: BusnDataRecord.  The array object is called: BusnInfo.
    BusnDataRecord[] BusnInfo = new BusnDataRecord[maxEntries];

    public static void main(String[] args) {
        Frame myFrame = new LocalRecyclers();
        myFrame.setSize(600, 550);
        myFrame.setLocation(600, 300);
        myFrame.setResizable(false);
        myFrame.setVisible(true);
    }

    public LocalRecyclers() {
        setTitle("Local Recyclers");

        SpringLayout myLayout = new SpringLayout();
        setLayout(myLayout);

        // Call the methods below to instantiate and place the various screen components
        LocateLabels(myLayout);
        LocateTextFields(myLayout);
        LocateTextArea(myLayout);
        LocateButtons(myLayout);
        LocateLabel(myLayout);

        this.addWindowListener(this);
    }

    //------------------------------------------------------------------------------------------
    // Method that manages the adding of multiple Labels to the screen.
    // Each line requests the LocateALabel method to instantiate, add and place a specific Label  
    public void LocateLabels(SpringLayout myLabelLayout) {
        //lblTitle = LocateALabel(myLabelLayout, lblTitle, "Local Recycler Contacts", 30, 15);
        lblBusinessName = LocateALabel(myLabelLayout, lblBusinessName, "Business Name:   ", 30, 65);
        lblAddress = LocateALabel(myLabelLayout, lblAddress, "Address:                 ", 30, 105);
        lblPhone = LocateALabel(myLabelLayout, lblPhone, "Phone:                     ", 30, 145);
        lblWebsite = LocateALabel(myLabelLayout, lblWebsite, "Web Site:                ", 30, 185);
        lblRecycles = LocateALabel(myLabelLayout, lblRecycles, "Recycles:                ", 30, 225);
        lblFind = LocateALabel(myLabelLayout, lblFind, "Find:", 405, 14);
        lblRecyclersList = LocateALabel(myLabelLayout, lblRecyclersList, "Recyclers List                                                                                       "
                + "                                                        ", 30, 330);
    }

    /**
     * -------------------------------------------------------- Purpose: Locate
     * a single Label within the frame.
     *
     * @param Current_layout_manager, Label_name, Label_caption, X_position,
     * Y_Position (within the Frame)
     * @returns The Label.
     *
     * Discussion: Method demonstrating low coupling and high cohesion for
     * adding individual labels: - reduces overall code, especially in the
     * LocateLabels method. - makes this method re-usable with minimal
     * adjustment as it is moved from one program to another.
     * ----------------------------------------------------------
     *
     * /**
     * Method with low coupling and high cohesion for adding individual labels:
     * - reduces overall code, especially in the LocateLabels method. - makes
     * this method re-usable with minimal adjustment as it is moved from one
     * program to another.
     */
    public Label LocateALabel(SpringLayout myLabelLayout, Label myLabel, String LabelCaption, int x, int y) {
        // Instantiate the Label
        myLabel = new Label(LabelCaption);
        myLabel.setBackground(new Color(32, 114, 98));
        myLabel.setForeground(Color.white);
        Font myFont = new Font("TimesRoman", Font.BOLD, 12);
        myLabel.setFont(myFont);
        // Add the Label to the screen
        add(myLabel);
        // Set the position of the Label (From left hand side of the frame (West), and from top of frame (North)) 
        myLabelLayout.putConstraint(SpringLayout.WEST, myLabel, x, SpringLayout.WEST, this);
        myLabelLayout.putConstraint(SpringLayout.NORTH, myLabel, y, SpringLayout.NORTH, this);
        // Return the label to the calling method
        return myLabel;
    }

    //------------------------------------------------------------------------------------------
    // Method that manages the adding of multiple TextFields to the screen.
    // Each line requests the LocateATextField method to instantiate, add and place a specific TextField  
    public void LocateTextFields(SpringLayout myTextFieldLayout) {

        /**
         * -------------------------------------------------------- Purpose:
         * Method for managing the addition of multiple TextFields to the
         * screen.
         *
         * @param The layout manager being used (SpringLayout myLayout).
         * @returns nothing (void).
         * ----------------------------------------------------------
         */
        // Each line calls the LocateATextField method to establish each TextField
        txtBusinessName = LocateATextField(myTextFieldLayout, txtBusinessName, 30, 150, 65);
        txtAddress = LocateATextField(myTextFieldLayout, txtAddress, 30, 150, 105);
        txtPhone = LocateATextField(myTextFieldLayout, txtPhone, 30, 150, 145);
        txtWebsite = LocateATextField(myTextFieldLayout, txtWebsite, 30, 150, 185);
        txtFind = LocateATextField(myTextFieldLayout, txtFind, 12, 448, 14);
        txtFilterBy = LocateATextField(myTextFieldLayout, txtFilterBy, 9, 465, 300);
    }

    /**
     * -------------------------------------------------------- Purpose: Locate
     * a single TextField within the frame.
     *
     * @param Layout_manager, TextField_name, Width, X_position, Y_Position
     * @returns The TextField.
     * ----------------------------------------------------------
     */
    /**
     * Method with low coupling and high cohesion for adding individual
     * textboxes: - reduces overall code, especially in the LocateTextFields
     * method. - makes this method re-usable with minimal adjustment as it is
     * moved from one program to another.
     */
    public TextField LocateATextField(SpringLayout myTextFieldLayout, TextField myTextField, int width, int x, int y) {
        myTextField = new TextField(width);
        add(myTextField);
        myTextFieldLayout.putConstraint(SpringLayout.WEST, myTextField, x, SpringLayout.WEST, this);
        myTextFieldLayout.putConstraint(SpringLayout.NORTH, myTextField, y, SpringLayout.NORTH, this);
        return myTextField;
    }

    // method to manage unique text box sizes
    public void LocateTextArea(SpringLayout myTextAreaLayout) {
        txtRecycles = LocateATextArea(myTextAreaLayout, txtRecycles, 3, 30, 150, 225);
        txtTextArea = LocateATextArea(myTextAreaLayout, txtTextArea, 9, 72, 30, 355);

    }

    public TextArea LocateATextArea(SpringLayout myTextAreaLayout, TextArea myTextArea, int width, int height, int x, int y) {
        myTextArea = new TextArea(width, height);
        add(myTextArea);
        myTextAreaLayout.putConstraint(SpringLayout.WEST, myTextArea, x, SpringLayout.WEST, this);
        myTextAreaLayout.putConstraint(SpringLayout.NORTH, myTextArea, y, SpringLayout.NORTH, this);
        return myTextArea;
    }

    /**
     * -------------------------------------------------------- Purpose: Method
     * for managing the addition of multiple Buttons to the screen.
     *
     * @param The layout manager being used (SpringLayout myLayout).
     * @returns nothing (void).
     * ----------------------------------------------------------
     */
    //------------------------------------------------------------------------------------------
    // Method that manages the adding of multiple Buttons to the screen.
    // Each line requests the LocateAButton method to instantiate, add and place a specific Button  
    public void LocateButtons(SpringLayout myButtonLayout) {
        btnFind = LocateAButton(myButtonLayout, btnFind, "Find", 405, 40, 150, 25);
        btnNew = LocateAButton(myButtonLayout, btnNew, "New", 405, 80, 150, 25);
        btnSave = LocateAButton(myButtonLayout, btnSave, "Save", 405, 110, 150, 25);
        btnDel = LocateAButton(myButtonLayout, btnDel, "Delete", 405, 140, 150, 25);
        btnExit = LocateAButton(myButtonLayout, btnExit, "Exit", 405, 250, 150, 25);
        btnFirst = LocateAButton(myButtonLayout, btnFirst, "|<", 405, 200, 30, 25);
        btnPrev = LocateAButton(myButtonLayout, btnPrev, "<", 445, 200, 30, 25);
        btnNext = LocateAButton(myButtonLayout, btnNext, ">", 485, 200, 30, 25);
        btnLast = LocateAButton(myButtonLayout, btnLast, ">|", 525, 200, 30, 25);
        btnSortBusn = LocateAButton(myButtonLayout, btnSortBusn, "Sort by Busn Name", 30, 300, 140, 25);
        btnSearchBusn = LocateAButton(myButtonLayout, btnSearchBusn, "Binary Search by Busn Name:", 175, 300, 200, 25);
        btnFilter = LocateAButton(myButtonLayout, btnFilter, "Filter by:", 380, 300, 80, 25);

    }

    /**
     * -------------------------------------------------------- Purpose: Locate
     * a single Button within the frame.
     *
     * @param Layout_manager, Button_name, Button_caption, X_position,
     * Y_Position, Width, Height
     * @returns The Button.
     * ----------------------------------------------------------
     */
    /**
     * Method with low coupling and high cohesion for adding individual buttons:
     * - reduces overall code, especially in the LocateButtons method. - makes
     * this method re-usable with minimal adjustment as it is moved from one
     * program to another.
     */
    public Button LocateAButton(SpringLayout myButtonLayout, Button myButton, String ButtonCaption, int x, int y, int w, int h) {
        myButton = new Button(ButtonCaption);
        myButton.addActionListener(this);
        add(myButton);
        myButtonLayout.putConstraint(SpringLayout.WEST, myButton, x, SpringLayout.WEST, this);
        myButtonLayout.putConstraint(SpringLayout.NORTH, myButton, y, SpringLayout.NORTH, this);
        myButton.setPreferredSize(new Dimension(w, h));
        return myButton;
    }

    public void LocateLabel(SpringLayout myLabelLayouts) {
        lblTitle = LocateALabels(myLabelLayouts, lblTitle, "Local Recycler Contacts", 30, 15);
    }

    public Label LocateALabels(SpringLayout myLabelLayouts, Label myLabels, String LabelCaption, int x, int y) {
        myLabels = new Label(LabelCaption);
        Font myFont = new Font("TimesRoman", Font.BOLD, 20);
        myLabels.setFont(myFont);
        myLabels.setForeground(new Color(0, 95, 51));
        add(myLabels);
        myLabelLayouts.putConstraint(SpringLayout.WEST, myLabels, x, SpringLayout.WEST, this);
        myLabelLayouts.putConstraint(SpringLayout.NORTH, myLabels, y, SpringLayout.NORTH, this);
        return myLabels;
    }

    /**
     * -------------------------------------------------------- Purpose: Respond
     * to user action events, such as clicking the New button.
     *
     * @param args Array of String arguments.
     * @returns nothing (void).
     * ----------------------------------------------------------
     */
    public void actionPerformed(ActionEvent e) {
        // BUTTON FIRST
        if (e.getSource() == btnFirst) {
            // The currentEntry variable is used to define which record will be displayed
            //     on screen.
            // In this instance, set the currentEntry to 0 (ie: the index of the first entry)
            currentEntry = 0;

            // The displayEntry method will display the currentEntry on the screen
            // In this instance, display the first entry (currentEntry = 0) on the screen.   
            displayEntry(currentEntry);
        }

        // BUTTON PREVIOUS
        if (e.getSource() == btnPrev) {
            // Only go to the previous record if we have a previous entry in the array...
            if (currentEntry > 0) {
                // Reduce the value of currentEntry by 1
                currentEntry--;
                // Display the current entry
                displayEntry(currentEntry);
            } else {
            }
        }
        // Do nothing

        // BUTTON NEXT
        if (e.getSource() == btnNext) {
            // Only go the next record if we have a next existing entry in the array...    
            // NOTE: the use of numberOfEntries as opposed to maxEntries.
            if (currentEntry < numberOfEntries - 1) {
                // Increase the value of currentEntry by 1
                currentEntry++;
                // Display the current entry
                displayEntry(currentEntry);
            }
        }

        // BUTTON LAST
        if (e.getSource() == btnLast) {
            currentEntry = numberOfEntries - 1;
            displayEntry(currentEntry);
        }

        // BUTTON NEW
        if (e.getSource() == btnNew) {
            // Only if the array is large enough to store another record...
            if (numberOfEntries < maxEntries - 1) {
                // Increment the numberOfEntries
                numberOfEntries++;
                // Set the current entry to the new record
                currentEntry = numberOfEntries - 1;
                // Blank out any existing data in the 3 arrays, ready
                //       for adding the new record.
                BusnInfo[currentEntry] = new BusnDataRecord("", "", "", "", "");
                // Display this new blank entry on screen
                displayEntry(currentEntry);
            }
        }

        // BUTTON SAVE
        if (e.getSource() == btnSave) {
            // Call the saveEntry method that will copy the current
            //     TextField entries from the screen to the current
            //     record in the array in memory.
            saveEntry(currentEntry);
        }

        // BUTTON DELETE
        if (e.getSource() == btnDel) {
            // Move all the later entries up one line each in the arrays, covering over
            //      the current entry in the process
            for (int i = currentEntry; i < numberOfEntries - 1; i++) {
                BusnInfo[i].setBusnInfo(BusnInfo[i + 1].getBusnName(), BusnInfo[i + 1].getBusnAddress(), BusnInfo[i + 1].getBusnPhone(),
                        BusnInfo[i + 1].getBusnWebsite(), BusnInfo[i + 1].getBusnRecycles());
            }
            // Reduce the current total number of entries stored in the array by one.
            // Then check if the current entry is now further down the array than
            //      the last entry.  If so, reduce the value of currentEntry by 1.
            numberOfEntries--;
            if (currentEntry > numberOfEntries - 1) {
                currentEntry = numberOfEntries - 1;
            }
            // Display the currentEntry
            displayEntry(currentEntry);
        }

        // BUTTON FIND
        if (e.getSource() == btnFind) {
            // Declare a boolean valuable: found (to remember whether
            //         the required entry has been found yet.)
            boolean found = false;
            // Declare a counter (i)
            int i = 0;
            // While there are more entries to check and the 'search' entry has not been found... 
            while (i < numberOfEntries && found == false) {
                // If the current BusinessName is equal to the 'search' entry...
                if (BusnInfo[i].getBusnName().equals(txtFind.getText())) {
                    // Set found = true
                    found = true;
                }
                // Increment the counter (i) so the loop will move onto the next record
                i++;
            }
            // If the entry was found, then set the value of currentEntry and then display the entry.
            if (found) {
                currentEntry = i - 1;
                displayEntry(currentEntry);
            }
        }

        // BUTTON SORT BUSN NAME
        if (e.getSource() == btnSortBusn || e.getSource() == btnSearchBusn) {
            // You are able to sort an array of objects using a comparator.
            // This has been left as an exercise: research and implement.
            // This code will copy the PC_Names to a new sortArray and 
            //      will then sort and display the sorted list.
            // Note that this sort is also applicable to a binary search

            // Copy the PC Names to the sortArray 
            for (int i = 0; i < numberOfEntries; i++) {
                sortArray[i] = BusnInfo[i].getBusnName();
            }
            // Sort the numberOfEntries in the sortArray
            Arrays.sort(sortArray, 0, numberOfEntries);
            // Display the sorted list in the textArea
            // Note:  \n - go to a new line in the TextArea
            txtTextArea.setText("Sorted Business Names:\n");
            txtTextArea.append("--------------------------\n");
            for (int i = 0; i < numberOfEntries; i++) {
                txtTextArea.append(sortArray[i] + "\n");
            }
        }

        // BUTTON SEARCH BUSN NAME
        if (e.getSource() == btnSearchBusn) {
            // Complete a binary search for the a Busn_Name typed into the
            //    txtFind TextField.  Note that a Binary search works 
            //    best on a sorted list.  Therefore...
            // Note that the code above that sorts the list of Busn_Names
            //    will run before the binary search code below.
            // Search through the numberOfEntries in the sortArray
            // Note: the result will be >= 0 if the search string is found
            int result = Arrays.binarySearch(sortArray, 0, numberOfEntries, txtFind.getText());
            // Note:  \n - go to a new line in the TextArea
            txtTextArea.append("\nBinary Search result = " + result);
        }
        // BUTTON FILTER
        if (e.getSource() == btnFilter) {
            // You are able to sort an array of objects using a comparator.
            // This has been left as an exercise: research and implement.
            // This code will copy the Busn_Names to a new sortArray and 
            // will then sort and display the sorted list.
            // Note that this sort is also applicable to a binary search

            // Copy the BusnInfo to the sortArray 
            for (int i = 0; i < numberOfEntries; i++) {
                sortArray[i] = BusnInfo[i].getBusnName() + ";" + BusnInfo[i].getBusnAddress()
                        + ";" + BusnInfo[i].getBusnPhone() + ";" + BusnInfo[i].getBusnWebsite() + ";" + BusnInfo[i].getBusnRecycles()
                        + "\n";
            }
            // Sort the numberOfEntries in the sortArray
            Arrays.sort(sortArray, 0, numberOfEntries);
            // Display the sorted list in the textArea
            // Note:  \n - go to a new line in the TextArea
            txtTextArea.setText("Busn Name; Address; Phone; Website; Recycles \n");
            txtTextArea.append("------------------------------------" + "---------------------------- \n");
            try {

                switch (txtFilterBy.getText().toLowerCase()) {
                    case "busn name":
                        for (int i = 0; i < numberOfEntries; i++) {
                            String result = BusnInfo[i].getBusnName();
                            if (result.length() > 0) {
                                txtTextArea.append(result
                                        + "\n\n");
                            }
                        }
                        break;

                    case "address":
                        for (int i = 0; i < numberOfEntries; i++) {
                            String result = BusnInfo[i].getBusnAddress();
                            if (result.length() > 0) {
                                txtTextArea.append(result
                                        + "\n\n");
                            }
                        }
                        break;

                    case "phone":
                        for (int i = 0; i < numberOfEntries; i++) {
                            String result = BusnInfo[i].getBusnPhone();
                            if (result.length() > 0) {
                                txtTextArea.append(result
                                        + "\n\n");
                            }
                        }
                        break;

                    case "website":
                        for (int i = 0; i < numberOfEntries; i++) {
                            String result = BusnInfo[i].getBusnWebsite();
                            if (result.length() > 0) {
                                txtTextArea.append(result
                                        + "\n\n");
                            }
                        }
                        break;

                    case "recycles":
                        for (int i = 0; i < numberOfEntries; i++) {
                            String result = BusnInfo[i].getBusnRecycles();
                            if (result.length() > 0) {
                                txtTextArea.append(result
                                        + "\n\n");
                            }
                        }
                        break;

                    default:
                        txtTextArea.setText("Busn Name; Address; Phone; Website; Recycles \n");
                        if (txtFilterBy.getText().length() > 0) {
                            txtTextArea.append("Text detected in Filter by. Filter by is invalid. Use names above ^\n");
                        }
                        // Use .append to add a line under the headings
                        txtTextArea.append("---------------------------"
                                + "------------------------------ \n");
                        //Loop through the varius records and add each one
                        // to a new line within the TextArea
                        for (int i = 0; i < numberOfEntries; i++) {
                            String[] result = BusnInfo[i].getBusnInfo();
                            txtTextArea.append(result[0]
                                    + ";" + result[1]
                                    + ";" + result[2]
                                    + ";" + result[3]
                                    + ";" + result[4]
                                    + "\n\n");
                        }
                        break;
                }
            } catch (Exception err) {
                err.printStackTrace();
            }
            txtTextArea.setCaretPosition(0);
        }

        // BUTTON EXIT
        if (e.getSource() == btnExit) {
            // Write all the records that are currently in the array (in memory)
            //       to your data file on the hard drive (USB, SSD, or equivalent)
            writeFile(dataFileName);
            // Exit from the application
            System.exit(0);
        }
    }
    // Manage responses to the various Window events

    public void windowClosing(WindowEvent we) {
        // Write all the records that are currently in the array (in memory)
        //       to your data file on the hard drive (USB, SSD, or equivalent)
        writeFile(dataFileName);
        // Exit from the application
        System.exit(0);
    }

    public void windowIconified(WindowEvent we) {
    }

    public void windowOpened(WindowEvent we) {
        // Call the method below that reads the data from the data file (when the Frame first opens)
        // Read in all the records within your data file on the hard
        //      drive (USB, SSD, or equivalent), into the array (in memory)
        readFile(dataFileName);
        // Display the first data entry (record) in the Frame
        displayEntry(currentEntry);
    }

    public void windowClosed(WindowEvent we) {
    }

    public void windowDeiconified(WindowEvent we) {
    }

    public void windowActivated(WindowEvent we) {
    }

    public void windowDeactivated(WindowEvent we) {
    }
    // Display the required data entry (record) in the Frame
    // The calling method must specify the number (index) of the entry that this
    //     method needs to currently display on screen.

    public void displayEntry(int i) {
        /**
         * -------------------------------------------------------- Purpose:
         * Display the indexed entry (int i) required on screen.
         *
         * @param The index (i) of the required entry.
         * @returns nothing (void).
         * ----------------------------------------------------------
         */
        // Take the required entry from the BusinessName array and display it
        //      in the txtPCName TextField.
        txtBusinessName.setText(BusnInfo[i].getBusnName());
        txtAddress.setText(BusnInfo[i].getBusnAddress());
        txtPhone.setText(BusnInfo[i].getBusnPhone());
        txtWebsite.setText(BusnInfo[i].getBusnWebsite());
        txtRecycles.setText(BusnInfo[i].getBusnRecycles());
    }
    // Take the current record displayed on screen and save it back into the 'currentEntry' position
    //      of the 3 arrays.

    public void saveEntry(int i) {
        // Take the current entry in the txtBusinessName TextField (on screen) and copy it 
        //      into the appropriate (currentEntry) position of the txtBusinessName array.
        BusnInfo[i].setBusnInfo(txtBusinessName.getText(), txtAddress.getText(), txtPhone.getText(),
                txtWebsite.getText(), txtRecycles.getText());
        // You may also wish to write all the records that are currently in the array
        //       to your data file on the hard drive (USB, SSD, or equivalent)
        writeFile(dataFileName);

        // (If required) Call the method below that writes the data back to the data file.
        //writeFile();
    }

    // Read in the data from the data file - IPAddresses.txt - one line at a time and store in the 3 arrays.
    // Remember the number of entries read in, in the global variable: numberOfEntries.
    public void readFile(String fileName) {
        // Try to read in the data and if an exception occurs go to the Catch section 
        try {
            // Set up vaious streams for reading in the content of the data file.
            FileInputStream fstream = new FileInputStream(fileName);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            int i = 0;   // i is used as the line counter
            String line; // line is used to temporarily store the line read in from the data file

            // Read a line from the data file into the buffer and then check whether
            //      it is null.  The while loop continues until a line read in is null.
            while ((line = br.readLine()) != null) {
                // Split the line of data (from the text file) and put each entry into the
                //                                             temporary array - temp[]
                String[] temp = line.split(";");

                // Save each entry into its respective array:
                BusnInfo[i] = new BusnDataRecord(temp[0], temp[1], temp[2], temp[3], temp[4]);
                i++;  // Increment i so we can keep a count of how many entries have been read in.
            }

            numberOfEntries = i;   // Set numberOfEntries equal to i, so as to remember how many entries are now in the arrays 
            br.close();            // Close the BufferedReader
            in.close();            // Close the DataInputStream
            fstream.close();       // Close the FileInputStream
        } catch (Exception e) {
            // If an exception occurs, print an error message on the console.
            System.err.println("Error Reading File: " + e.getMessage());
        }
    }
    // Write the data back out to the data file - one line at a time
    // Note: You may wish to use a different data file name while initially
    //       developing, so as not to accidently corrupt your input file.

    public void writeFile(String fileName) {
        // Try to print out the data and if an exception occurs go to the Catch section 
        try {
            // After testing has been completed, replace the hard-coded filename: "IPAddresses_New.txt"
            //       with the parameter variable: fileName 
            // Set up a PrintWriter for printing the array content out to the data file.
            PrintWriter out = new PrintWriter(new FileWriter(fileName));

            // Print out each line of the array into your data file.
            // Each line is printed out in the format:  PCName,PCID,IPAddress
            for (int m = 0; m < numberOfEntries; m++) {
                String[] busninfo = BusnInfo[m].getBusnInfo();
                out.println(busninfo[0] + ";" + busninfo[1] + ";" + busninfo[2] + ";" + busninfo[3] + ";" + busninfo[4]);
            }

            // Close the printFile (and in so doing, empty the print buffer)
            out.close();
        } catch (Exception e) {
            // If an exception occurs, print an error message on the console.
            System.err.println("Error Writing File: " + e.getMessage());
        }
    }

    /**
     * -------------------------------------------------------- Class:
     * PCDataRecord
     *
     * @author Mark O'Reilly
     *
     * Developed: 2016-2017
     *
     * Purpose: To record the Busn_Name, Busn_Phone and Busn_Address,
     * Busn_Website & Busn_Recycles for a SINGLE business.
     *
     * Demonstrating the implementation of: - constructor overloading - Getters
     * and Setters
     *
     * ----------------------------------------------------------
     */
    class BusnDataRecord {
        // Declarations of 5 Strings, used to store the BusnName, BusnAddress, BusnPhone, BusnWebsite and BusnRecycles
        //      data in memory for EACH BusnDataRecord.  That is: when one object is instantiated
        //      from this BusnDataRecord class, it will record the detail for one PC.
        // These properties - BusnName, BusnAddress, BusnPhone, BusnWebsite and BusnRecycles - are set to private so a calling
        //      class is not able to assign or access the respective values directly.
        //      Access to these properties would be best managed through the respective
        //      getters and setters - see below.

        private String BusnName = new String();
        private String BusnAddress = new String();
        private String BusnPhone = new String();
        private String BusnWebsite = new String();
        private String BusnRecycles = new String();

        /**
         * -------------------------------------------------------- Purpose:
         * Constructor for the class:BusnDataRecord. When a BusnDataRecord is
         * instantiated, and no default entries for the 5 properties -
         * Business_Name/Business_Address/Business_Phone/Business_Website/Business_Recycles
         * - are provided, this method will set default values for each.
         *
         * @param None.
         * @returns Not applicable.
         * ----------------------------------------------------------
         */
        public BusnDataRecord() {
            BusnName = "Business_Name";
            BusnAddress = "Business_Address";
            BusnPhone = "Business_Phone";
            BusnWebsite = "Business_Website";
            BusnRecycles = "Business_Recycles";
        }

        /**
         * -------------------------------------------------------- Purpose: A
         * second constructor for the class: BusnDataRecord. When a
         * BusnDataRecord is instantiated and default entries for the 5
         * properties -
         * Business_Name/Business_Address/Business_Phone/Business_Website/Business_Recycles
         * - are provided by the calling class, this constructor will run.
         *
         * @param Business_Name,Business_Address,Business_Phone,Business_Website
         * & Business_Recycles
         * @returns Not applicable.
         * ----------------------------------------------------------
         */
        public BusnDataRecord(String name, String phone, String address, String website, String recycles) {
            BusnName = name;
            BusnPhone = phone;
            BusnAddress = address;
            BusnWebsite = website;
            BusnRecycles = recycles;
        }

        /**
         * -------------------------------------------------------- Purpose: A
         * method that will allow the calling class to set the 5 properties -
         * Business_Name/Business_Address/Business_Phone/Business_Website/Business_Recycles
         * - all at the one time.
         *
         * @param PC_Name, PC_ID and IP_Address.
         * @returns nothing (void).
         * ----------------------------------------------------------
         */
        public void setBusnInfo(String name, String phone, String address, String website, String recycles) {
            BusnName = name;
            BusnPhone = phone;
            BusnAddress = address;
            BusnWebsite = website;
            BusnRecycles = recycles;
        }

        /**
         * -------------------------------------------------------- Purpose: A
         * method that will allow the calling class to set the BusnName
         * property. This method could include code to validate incoming
         * Busn_Name data.
         *
         * @param Busn_Name.
         * @returns nothing (void).
         * ----------------------------------------------------------
         */
        public void setBusnName(String name) {
            BusnName = name;
        }

        /**
         * -------------------------------------------------------- Purpose: A
         * method that will allow the calling class to set the phone property.
         * This method could include code to validate incoming Busn_Phone data.
         *
         * @param Busn_Phone
         * @returns nothing (void).
         * ----------------------------------------------------------
         */
        public void setBusnPhone(String phone) {
            BusnPhone = phone;
        }

        /**
         * -------------------------------------------------------- Purpose: A
         * method that will allow the calling class to set the BusnAddress
         * property. This method could include code to validate incoming
         * Busn_Address data.
         *
         * @param Busn_Address.
         * @returns nothing (void).
         * ----------------------------------------------------------
         */
        public void setBusnAddress(String address) {
            BusnAddress = address;
        }

        /**
         * -------------------------------------------------------- Purpose: A
         * method that will allow the calling class to set the website property.
         * This method could include code to validate incoming IP_Address data.
         *
         * @param Busn_Website
         * @returns nothing (void).
         * ----------------------------------------------------------
         */
        public void setBusnWebsite(String website) {
            BusnWebsite = website;
        }

        /**
         * -------------------------------------------------------- Purpose: A
         * method that will allow the calling class to set the recycles
         * property. This method could include code to validate incoming
         * Busn_Recycles data.
         *
         * @param Busn_Recycles.
         * @returns nothing (void).
         * ----------------------------------------------------------
         */
        public void setBusnRecycles(String recycles) {
            BusnRecycles = recycles;
        }

        public String[] getBusnInfo() {
            return new String[]{
                BusnName, BusnAddress, BusnPhone, BusnWebsite, BusnRecycles
            };
        }

        /**
         * -------------------------------------------------------- Purpose: A
         * method that will allow this BusnDataRecord class to provide the
         * calling class with the Busn_Name data. This method allows this class
         * to manage outgoing Busn_Name.
         *
         * @param None.
         * @returns BusnName (String).
         * ----------------------------------------------------------
         */
        public String getBusnName() {
            return BusnName;
        }

        /**
         * -------------------------------------------------------- Purpose: A
         * method that will allow this BusnDataRecord class to provide the
         * calling class with the Busn_Phone data. This method allows this class
         * to manage outgoing Busn_Phone.
         *
         * @param None.
         * @returns BusnPhone (String).
         * ----------------------------------------------------------
         */
        public String getBusnPhone() {
            return BusnPhone;
        }

        /**
         * -------------------------------------------------------- Purpose: A
         * method that will allow this BusnDataRecord class to provide the
         * calling class with the Busn_Address data. This method allows this
         * class to manage outgoing Busn_Address.
         *
         * @param None.
         * @returns BusnAddress (String).
         * ----------------------------------------------------------
         */
        public String getBusnAddress() {
            return BusnAddress;
        }

        /**
         * -------------------------------------------------------- Purpose: A
         * method that will allow this BusnDataRecord class to provide the
         * calling class with the Busn_Website data. This method allows this
         * class to manage outgoing Busn_Website.
         *
         * @param None.
         * @returns BusnWebsite (String).
         * ----------------------------------------------------------
         */
        public String getBusnWebsite() {
            return BusnWebsite;
        }

        /**
         * -------------------------------------------------------- Purpose: A
         * method that will allow this BusnDataRecord class to provide the
         * calling class with the Busn_Recycles data. This method allows this
         * class to manage outgoing Busn_Recycles.
         *
         * @param None.
         * @returns BusnRecycles (String).
         * ----------------------------------------------------------
         */
        public String getBusnRecycles() {
            return BusnRecycles;
        }

    }
}
