/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.southerncreditrecoveryautomationtool;

import com.monitorjbl.xlsx.exceptions.ParseException;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fonta
 */
public class FACSSystemToXLSXConverter {

    public static void main(String[] args) {

        SimpleDateFormat  SIMPLE_DATE_FORMAT = new SimpleDateFormat("MM/dd/yy");
        SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
        
        Calendar CALENDAR = Calendar.getInstance();
        CALENDAR.add(Calendar.YEAR, -100);
        SIMPLE_DATE_FORMAT.set2DigitYearStart(CALENDAR.getTime());
        
        /*
         * Create a String Variable meant to  store the absolute path of the selected file.
         * Absolute File Path Example: [  "C:\Users\Mlombardi92\Downloads\DES0823.csv" ] *NO SPACES *
         */
        String FILE_PATH = "";
        File DESOTO_TXT = new File("");
        
        int TOTAL_ROWS_START_PAGE = 53;
        int TOTAL_ROWS_MIDDLE_PAGE = 51;
        int TOTAL_ROWS_LAST_PAGE = 0;
        
        int RECORDS_START_START_PAGE = 10;
        int RECORDS_END_START_PAGE = 53;
        
        int RECORDS_START_MIDDLE_PAGE = 62;
        int RECORDS_END_MIDDLE_PAGE = 157;
        
        int RECORDS_START_FINAL_PAGE = 0;
        int RECORDS_END_FINAL_PAGE = 0;
        
        int MAX_RECORDS_PER_PAGE = 43;
        
        int TOTAL_LINES_REMOVE_START = 7
                
                ;
        int TOTAL_LINES_REMOVE_MIDDLE = 8;
        int TOTAL_LINES_REMOVE_END = 15;
        
        int ROW_COUNT = 0;
        int PAGE = 1;

        /*
         * Initialize a new File Object which will be declared after recieveing
         * the FILE_PATH input String.
         */
        File FACS_REPORT = new File("");

        /*
         * Initialize a new custom ProgressReport Object which is purposed towards holding the array
         *  of PaymentRecord objects that make up the entirety of the FACSSystem Progress Report.
         */
        ProgressReport PROGRESS_REPORT = new ProgressReport();
        /*
         *Initialize a new custom PaymentRecord Object which is designed to hold each data field
         * represented as  columns in newly generated FACS System Reports.
         */
        PaymentRecord PAYMENT_RECORD = new PaymentRecord();

        /*
         * Create a new Scanner Object specifically designed to break apart incoming Strings according
         * to the type of character/text seperating each individual field of report data
         */
        Scanner DELIMITER_CHECK;

        /*
         * Create a String Array of phrases and expressions typically found in FACS System Reports
         * as Headers/Footers or Page Break Elements. These Strings will are used to determine if
         * the current line  being read from the file needs to be ommited or not.
         */
        String[] DELIMITER_IGNORE = {
            "SOUTHERN",
            "CREDIT",
            "RECOVERY,",
            "INC.",
            "PAGE",
            "AM",
            "PM",
            "SLP",
            "PROGRESS/STATUS",
            "REPORT",
            "FOR",
            "-",
            "DESOTO",
            "REGIONAL",
            "HOSPITAL",
            "DATE",
            "AMOUNT",
            "DATE",
            "TOTAL",
            "YOUR",
            "ACCOUNT",
            "NUMBER",
            "AND",
            "NAME",
            "LISTED",
            "LISTED",
            "LAST",
            "PAY",
            "PAY/ADJ",
            "BALANCE",
            "CALLS/LETTERS",
            "ACCOUNT",
            "STATUS",
            "===================================================================================================================================="
        };

        /*
         * Create a Scanner Object which will be used
         * to recive our File Path String Input
         */
        Scanner SCANNER = new Scanner(System.in);

        // Display Toolkit information.
        System.out.println("|==========================================================================================|\n" + "|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|\n" + "|==========================================================================================|\n" + "|                                                                                          |\n" + "|------------------ FACS System Progress Report to Excel Spreadsheet ----------------------|\n" + "|                                                                                          |\n" + "|\t\tWelcome to the Southern Credit Recovery Automation Toolkit.   |\n" + "|\tYour current application is intended to convert FACS System Reports                |\n" + "|\tinto a structured and organized CSV or XLSX (Microsoft Excel) file.                |\n" + "|\tAll output files will be generated in the same directory as the input              |\n" + "|\tfile. Please begin by entering the input file path in the prompt                   |\n" + "|\tshown below.                                                                       |\n" + "|                                                                                          |\n" + "| **PLEASE NOTE:**                                           |\n" + "|                                                                                          |\n" + "|\tThe proper format for a file's path is one of the following:                       |\n" + "|                                                                                          |\n" + "| C:\\Users\\[USERNAME]\\Documents\\[CLIENTFOLDER]\\[MYFILE].csv                  |\n" + "| C:\\Users\\[USERNAME]\\Downloads\\[DATAFILE].txt)                              |\n" + "|                                                                                   |\n" + "|\tIf the user inputs a file that doesn't follow the previously stated\t\t   |\n" + "|\tnaming convention or a file that contains illegal characters such as the           |\n" + "|\tfollowing: [ ( \"\" ), ( '' ), ( < ), ( > ), ( | ), ( \\ ), ( / ), ( ? )] in          |\n" + "|\tits name; then the user will be prompted to enter a new file path instead          |\n" + "|\tor completely exit the program.                                                    |\n" + "|                                                                                          |\n" + "|==========================================================================================|\n" + "|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|\n" + "|==========================================================================================|");
        // Prompt the current user to input thier specified File Path
        System.out.print("\nPlease enter the file path: ");
        // Wait for and record the user's File Path String input
        FILE_PATH = SCANNER.nextLine();

        /*
         *   The next section is an error checking segment where we ensure the
         *   current user provides the application with a valid, traceable file path string.
         *  
         *   Firstly, we check to see if the file path that the user inputed contains any illegal characters that shouldn't be part of the String.
         *   If the user inputted file path String contains any of the following characters: [ ( "" ), ( '' ), ( < ), ( > ), ( : ), ( | ), ( ? ), ( * ) ];
         *   then we need to remove these illegal characters from the String.
         *  
         */
        while (FILE_PATH.contains("\"") || FILE_PATH.contains("\'") || FILE_PATH.contains("/") || FILE_PATH.contains("<") || FILE_PATH.contains(">") ||
            FILE_PATH.contains(":") || FILE_PATH.contains("?") || FILE_PATH.contains("|") || FILE_PATH.contains("*")) {

            // Should the file name contain either parenthesis or quote mark characters then
            // remove them from the inputted String variable.
            if (FILE_PATH.contains("\"") || FILE_PATH.contains("\'")) {
                FILE_PATH = FILE_PATH.substring(1, (FILE_PATH.length() - 1));
            }
            // If the file path contains the forward slash character and its being used to
            // represent directory traversal, then replace the character with the required
            // backslash character instead.
            else if (FILE_PATH.contains("/")) {
                FILE_PATH.replaceAll("/", "\\");
            }
            /*
             *   Should the user input a file path containing illegal characters in its name,
             *   or an invalid file path, then prompt them to choose between inputing a valid
             *   path objwct or exiting out of the program.
             */
            else {
                break;
            }
        }

        ArrayList < String > LINE_LIST = new ArrayList < > ();

        DELIMITER_CHECK = new Scanner(System.in);
        boolean KILL_LOOP = false;

        try {
            DESOTO_TXT = new File(FILE_PATH);
            DELIMITER_CHECK = new Scanner(DESOTO_TXT).useDelimiter("\t");
            while (DELIMITER_CHECK.hasNextLine() == true) {
                LINE_LIST.add(DELIMITER_CHECK.nextLine());
                ROW_COUNT++;
            }
            
            LocalDate DATE;

            for (int CURRENT_ROW = 1; CURRENT_ROW < LINE_LIST.size(); CURRENT_ROW++) {
                if (PAGE == 1) {
                     if (CURRENT_ROW  % TOTAL_ROWS_START_PAGE == 1) {
                         LINE_LIST.removeFirst();
                     }
                     else  if ((CURRENT_ROW %TOTAL_ROWS_START_PAGE) == 2) {
                        String[] DATESTAMP = LINE_LIST.removeFirst().split("                                                 ");
                        System.out.println("i = " + CURRENT_ROW + " and the DATESTAMP is: " + DATESTAMP[0]);

                        DATE = LocalDate.parse(FORMATTER.format(SIMPLE_DATE_FORMAT.parse(DATESTAMP[0].trim())).toString());
                        PROGRESS_REPORT.setFileCreationDate(DATE);
                    } 
                    else if ((CURRENT_ROW % TOTAL_ROWS_START_PAGE) == 3) {
                        String[] TIMESTAMP = LINE_LIST.removeFirst().split("                              ");
                        PROGRESS_REPORT.setFileCreationTimestamp(TIMESTAMP[0].trim());
                        System.out.println("i = " + CURRENT_ROW + " and the TIMESTAMP is: " + TIMESTAMP[0]);
                    } 
                    else if (((CURRENT_ROW % TOTAL_ROWS_START_PAGE) >= 4) && ((CURRENT_ROW % TOTAL_ROWS_START_PAGE)<= 9)) {
                        // We don't need any Data from these rows
                        LINE_LIST.removeFirst();
                    } 
                    else if (((CURRENT_ROW % TOTAL_ROWS_START_PAGE) >= 10) && ((CURRENT_ROW % TOTAL_ROWS_START_PAGE) <= 53)) {
                        String RECORD = LINE_LIST.removeFirst();
                        System.out.println("Record: " + RECORD);
                        
                        if (CURRENT_ROW == 53) {
                            PAGE++;
                        }
                    }
                }
                if (PAGE >= 2) {
                   if (((CURRENT_ROW % TOTAL_ROWS_MIDDLE_PAGE) >= 1) && ((CURRENT_ROW % TOTAL_ROWS_MIDDLE_PAGE)<= 8)) {
                        // We don't need any Data from these rows
                        LINE_LIST.removeFirst();
                    } 
                    else if (((CURRENT_ROW % TOTAL_ROWS_MIDDLE_PAGE) >= 9) && ((CURRENT_ROW % TOTAL_ROWS_MIDDLE_PAGE) <= 53)) {
                        String RECORD = LINE_LIST.removeFirst();
                        System.out.println("Record: " + RECORD);
                    }
                    else if ((CURRENT_ROW % TOTAL_ROWS_MIDDLE_PAGE) >= 53) {
                        LINE_LIST.removeFirst();
                    }
                    else {
                        PAGE++;
                    }
                }
            }
            
            TOTAL_ROWS_LAST_PAGE = (ROW_COUNT - TOTAL_LINES_REMOVE_START - (TOTAL_LINES_REMOVE_MIDDLE  * PAGE)- TOTAL_LINES_REMOVE_END) % MAX_RECORDS_PER_PAGE;
            
            System.out.println("Records on Last Page: " + TOTAL_ROWS_LAST_PAGE);
            System.out.println("Row Count: " + ROW_COUNT);
            
        } 
        catch (FileNotFoundException e) {
            System.out.println("File Not Found Exception");
        }
        catch (java.text.ParseException ex) {
            System.out.println("ParseException Occured");
            Logger.getLogger(FACSSystemToXLSXConverter.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (KILL_LOOP == false && DELIMITER_CHECK.hasNextLine() == true) {
            try {
                //TEMP = DELIMITER_CHECK.nextLine();
            } 
            catch (ArrayIndexOutOfBoundsException e) {
                //
            }
        }

        System.out.println("|==========================================================================================|\n" + "|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|\n" + "|==========================================================================================|\n" + "|                                                                                          |\n" + "|------------------ FACS System Progress Report to Excel Spreadsheet ----------------------|\n" + "|                                                                                          |\n" + "| Welcome to the Southern Credit Recovery Automation Toolkit.               |\n" + "| Your current application is intended to convert FACS System Reports                |\n" + "| into a structured and organized CSV or XLSX (Microsoft Excel) file.                |\n" + "| All output files will be generated in the same directory as the input              |\n" + "| file. Please begin by entering the input file path in the prompt                   |\n" + "| shown below.                                                                       |\n" + "|                                                                                          |\n" + "| **PLEASE NOTE:**                                       |\n" + "|                                                                                          |\n" + "| The proper format for a file's path is one of the following:                       |\n" + "|                                                                                          |\n" + "| C:\\Users\\[USERNAME]\\Documents\\[CLIENTFOLDER]\\[MYFILE].csv                          |\n" + "| C:\\Users\\[USERNAME]\\Downloads\\[DATAFILE].txt)                                      |\n" + "|                                                                                       |\n" + "| If the user inputs a file that doesn't follow the previously stated            |\n" + "| naming convention or a file that contains illegal characters such as the           |\n" + "| following: [ ( \"\" ), ( '' ), ( < ), ( > ), ( | ), ( \\ ), ( / ), ( ? )] in          |\n" + "| its name; then the user will be prompted to enter a new file path instead          |\n" + "| or completely exit the program.                                                    |\n" + "|                                                                                          |\n" + "|==========================================================================================|\n" + "|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|\n" + "|==========================================================================================|");

    }
}
