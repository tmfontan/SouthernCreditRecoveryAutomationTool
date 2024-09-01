/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.southerncreditrecoveryautomationtool;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author fonta
 */
public class FACSSystemToXLSXConverter {
    
    public static void main(String[] args) {
        
        /*
        * Create a String Variable meant to  store the absolute path of the selected file. 
        * Absolute File Path Example: [  "C:\Users\Mlombardi92\Downloads\DES0823.csv" ] *NO SPACES *
        */
        String FILE_PATH = "";
        
        /*
        * Initialize a new File Object which will be declared after recieveing
        * the FILE_PATH input String.
        */
        File FACS_REPORT = new File("");

        /*
        * Initialize a new custom ProgressReport Object which is purposed towards holding the array
        *  of PaymentRecord objects that make up the entirety of the FACSSystem Progress Report.
        */
        ProgressReport PROGRESS_REPORT;
        /*
        *Initialize a new custom PaymentRecord Object which is designed to hold each data field 
        * represented as  columns in newly generated FACS System Reports.
        */
        PaymentRecord PAYMENT_RECORD;

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
        System.out.println("================================================================================\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n================================================================================\n\nWelcome to the Southern Credit Recovery Automation Toolkit. \nYour current application is intended to convert FACS System Reports\ninto a structured and organized CSV or XLSX (Microsoft Excel) file.\nAll output files will be generated in the same directory as the input file.\nPlease begin by entering the input file path in the prompt shown below.\n" + "\n" + "PLEASE NOTE:\n\n" + "The proper format for a file's path is one of the following: \n" + "\n\tC:\\Users\\[USERNAME]\\Documents\\[CLIENTFOLDER]\\[MYFILE].csv\n" + "\tC:\\Users\\[USERNAME]\\Downloads\\[DATAFILE].txt)\n\n" + "If the user inputs a file that doesn't follow the previously stated naming\nconvention or a file that contains illegal characters such as the following:\n[ ( \"\" ), ( '' ), ( < ), ( > ), ( | ), ( \\ ), ( / ), ( ? )] in its name;\nthen the user will be prompted to enter a new file path instead\nor completely exit the program.\n\n================================================================================\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n================================================================================\n");
        // Prompt the current user to input thier specified File Path
        System.out.print("Please enter the file path: ");
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
        while (     FILE_PATH.contains("\"") || FILE_PATH.contains("\'") || FILE_PATH.contains("/") || FILE_PATH.contains("<") || FILE_PATH.contains(">") || 
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
            else {
                System.out.println("\nThe file path you've entered either contains illegal characters\nor the file doesn't exist. Would you like to try entering another\nfile path or completely exit the program?\n\n\tEnter [1] to input another file path. \n\tEnter [0] to exit the program. ");
            }
        }
        
        
    }
}
