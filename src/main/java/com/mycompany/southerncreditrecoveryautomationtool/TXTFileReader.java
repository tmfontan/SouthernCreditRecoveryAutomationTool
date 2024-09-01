/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.southerncreditrecoveryautomationtool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author fonta
 */
public class TXTFileReader {

    public static void main(String[] args) throws Exception {

        String FILE_PATH = "";
        File DESOTO_TXT = new File("");

        ProgressReport PROGRESS_REPORT;
        PaymentRecord PAYMENT_RECORD;

        CharSequence[] HEADERS_AND_FOOTERS = new CharSequence[6];
        Scanner DELIMITER_CHECK;
        Scanner SCANNER;

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
        
        String[] HEADER_FOOTER_VALUES = {
            "PROGRESS/STATUS REPORT FOR ",
            " - DESOTO REGIONAL HOSPITAL",
            "  SLP", "   ",
            "===================================================================================================================================="
        };
        
        String[] ACCOUNT_STATUS_OPTIONS = {
            "COLLECTION EFFORTS EXHAUSTED",
            "ACCOUNT PAID IN FULL",
            "ATTEMPTING INITIAL CONTACT",
            "CONTACT EFFORTS CONTINUE",
            "UNRESPONSIVE TO CALLS/MAIL",
        };
        
        ArrayList<String> PAYMENT_RECORD_LIST = new ArrayList<>();
        String REMAINDER = "";
        
        HEADERS_AND_FOOTERS[0] = new StringBuilder("====================================================================================================================================");
        HEADERS_AND_FOOTERS[1] = new StringBuilder("DATE     AMOUNT    DATE      TOTAL");
        HEADERS_AND_FOOTERS[2] = new StringBuilder("YOUR ACCOUNT NUMBER AND NAME         LISTED    LISTED  LAST PAY    PAY/ADJ BALANCE  CALLS/LETTERS  ACCOUNT STATUS");
        HEADERS_AND_FOOTERS[3] = new StringBuilder("SOUTHERN CREDIT RECOVERY, INC.                                                   PAGE");
        HEADERS_AND_FOOTERS[4] = new StringBuilder("SLP                              PROGRESS/STATUS REPORT FOR ");
        HEADERS_AND_FOOTERS[5] = new StringBuilder("                                                                                                                        ");

        String[] ROW_DATA;
        String[] LINE_SPLIT = null;

        String LINE = "";

        int ROW_COUNT = 0;
        int INDEX_TO_LINE = 0;
        int RECORD_COUNT = 0;
        
        boolean FILE_EXISTS = false;

        SCANNER = new Scanner(System.in);
        
        System.out.print("Please enter the file path: ");
        FILE_PATH = SCANNER.nextLine();

        while (FILE_EXISTS == false) {
            
        
            while (FILE_PATH.contains("\"") || FILE_PATH.contains("\'") || FILE_PATH.contains("/")) {
                if (FILE_PATH.contains("\"") || FILE_PATH.contains("\'")) {
                    FILE_PATH = FILE_PATH.substring(1, (FILE_PATH.length() - 1));
                }
                if (FILE_PATH.contains("/")) {
                    FILE_PATH.replaceAll("/", "\\");
                }
            }
        }

        DESOTO_TXT = new File(FILE_PATH);
        DELIMITER_CHECK = new Scanner(DESOTO_TXT).useDelimiter("\t");

        boolean CASE = true;
        
        while (DELIMITER_CHECK.hasNextLine() && (CASE = true)) {
            
            //Print the contents of a file by line .
            //System.out.print("next(): " + DELIMITER_CHECK.nextLine());
            
            String TEMP[] = DELIMITER_CHECK.nextLine().split("\t");
            
            for (int COUNT = 0;  COUNT < TEMP.length; COUNT++) {
                
                //System.out.println("");
                //System.out.println("BROKEN STRING PART: " + TEMP[COUNT]);
                
                String[] TESTER = TEMP[COUNT].split("  ");
                
                System.out.println("");
                
                for (int i = 0; i < TESTER.length; i++) {
                      //System.out.println("TESTER: " +TESTER[i].isBlank() + " " + TESTER[i]);
                      if (TESTER[i].isBlank() || TESTER[i].isEmpty()) {
                          // Skip line seeing as there is no data present in the Split String.
                      }
                      /*else if ((Character.isAlphabetic(TESTER[i])) && (Character.isDigit(TESTER[i])) {
                          String DATA_SECLUSION = TESTER[i].strip();
                          System.out.println("TRIMMED STRING: " + DATA_SECLUSION);
                      }*/
                }
                /*
                if (CASE = false)  {
                    COUNT = TEMP.length;
                }
                else {
                    for (int COUNTER = 0; COUNTER < DELIMITER_IGNORE.length; COUNTER++) {
                        if (TEMP[COUNT].contains(DELIMITER_IGNORE[COUNTER])) {
                            // Ignore the Current Cell / Row Segment
                            CASE = false;
                            break;
                        }
                        else if (TEMP[COUNT].isBlank() || TEMP[COUNT].isEmpty()) {
                            CASE = false;
                            break;
                        }
                }*/
                }
            }
            /*
            // START THE WHILE LOOP AT INDEX FOUR SO IT WILL IGNORE THE FIRST FIVE LINES OF THE REPORT
            for (int COUNTER = 4; COUNTER < HEADERS_AND_FOOTERS.length; COUNTER++) {
                // IF THE CURRENT LINE / WORD IS ANY OF THE STRINGS PRESENT IN THE DELIMITER_IGNORE ARRAY ABOVE THEN 
                // IGNORE THE CURRENT LINE. ELSE, BREAK APART THE CURRENT STRING INTO ITS INDIVIDUAL DATA PARTS
                if (TEMP.equals(HEADERS_AND_FOOTERS[COUNTER]) == true) {
                    // IF THE CURRENT LINE CONTAINS A PROHIBITED STRING THEN SKIP THE CURRENT DATA ENTRY
                    //System.out.println("The current line contains a Ignored String: " + DELIMITER_IGNORE[COUNTER]);
                    COUNTER = HEADERS_AND_FOOTERS.length;
                }
                /*else if (TEMP.isBlank() == true || TEMP.isEmpty() == true) {
                    // THE CURRENT DATA ENTRY IS BLANK, SO SKIP TO THE END OF THIS FOR LOOP AND MOVE ON TO THE NEXT
                    // LINE OF DATA FROM THE FILE.
                    //System.out.println("The current line is Blank or Empty: " + DELIMITER_IGNORE[COUNTER]);
                    COUNTER = HEADERS_AND_FOOTERS.length;
                }
                else {
                    //System.out.println(TEMP);
                    PAYMENT_RECORD = new PaymentRecord();

                   //"C:\Users\fonta\Downloads\DES0823.csv""C:\Users\fonta\Downloads\DES0823.csv""C:\Users\fonta\Downloads\DES0823.csv" PAYMENT_RECORD.setAccountName(TEMP);
                    COUNTER = HEADERS_AND_FOOTERS.length;
                }
                //System.out.println("This is a Line: " + TEMP);
            }
            //System.out.println("GETTING HERE: " + TEMP);
        }*/
    }
}
