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
import java.util.regex.Pattern;

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
                else  if (FILE_PATH.contains("/")) {
                    FILE_PATH.replaceAll("/", "\\");
                }
            }
            FILE_EXISTS = true;
        }

        DESOTO_TXT = new File(FILE_PATH);
        DELIMITER_CHECK = new Scanner(DESOTO_TXT).useDelimiter("\t");

        boolean CASE = true;
        
         while (DELIMITER_CHECK.hasNext() && (CASE = true)) {
           
            //Print the contents of a file by line .
            //System.out.print("next(): " + DELIMITER_CHECK.nextLine());
           
            String TEMP[] = DELIMITER_CHECK.nextLine().split("\t");
           
            for (int COUNT = 0;  COUNT < TEMP.length; COUNT++) {
               
                //System.out.println("");
                //System.out.println("BROKEN STRING PART: " + TEMP[COUNT]);
               
                String[] TESTER = TEMP[COUNT].split("  ");
               
                System.out.println("The Value of Temp[" + COUNT + "] is: " + TEMP[COUNT]);
                //C:\Users\Tyler Fontana\Downloads\DESOTO20.txt
               
                for (int i = 0; i < TESTER.length; i++) {
                      //System.out.println("TESTER: " +TESTER[i].isBlank() + " " + TESTER[i]);
                      if (TESTER[i].isBlank() || TESTER[i].isEmpty() || TESTER[i] == null) {
                          // Skip line seeing as there is no data present in the Split String.
                          //System.out.println("The line is Blank");
                      }
                      else {
                          for (int j = 0; j < DELIMITER_IGNORE.length; j++) {
                              for (int k = 0; k < TESTER.length; k++) {
                                TESTER[k] = TESTER[k].trim();
                                if (TESTER[i].isBlank() || TESTER[i].isEmpty() || TESTER[i] == null) {
                                    // Skip line seeing as there is no data present in the Split String.
                                    //System.out.println("The line is Blank");
                                }
                                else if (Pattern.compile(Pattern.quote(DELIMITER_IGNORE[j]),Pattern.CASE_INSENSITIVE).matcher(TESTER[k].trim().toLowerCase()).find() == false) {
                                    //System.out.println("The Value of TESTER[" + k + "] is: "+ TESTER[k]);
                                    if (Pattern.compile(Pattern.quote(ACCOUNT_STATUS_OPTIONS[0]),Pattern.CASE_INSENSITIVE).matcher(TESTER[k].trim().toLowerCase()).find() == false){
                                        System.out.println("Getting here in COLLECTION EFFORTS EXHAUSTED");
                                    }
                                    else if (Pattern.compile(Pattern.quote(ACCOUNT_STATUS_OPTIONS[1]),Pattern.CASE_INSENSITIVE).matcher(TESTER[k].trim().toLowerCase()).find() == false){
                                        System.out.println("Getting here in ACCOUNT PAID IN FULL");
                                    }
                                    else if (Pattern.compile(Pattern.quote(ACCOUNT_STATUS_OPTIONS[2]),Pattern.CASE_INSENSITIVE).matcher(TESTER[k].trim().toLowerCase()).find() == false){
                                        System.out.println("Getting here in ATTEMPTING INITIAL CONTACT");
                                    }
                                    else if (Pattern.compile(Pattern.quote(ACCOUNT_STATUS_OPTIONS[3]),Pattern.CASE_INSENSITIVE).matcher(TESTER[k].trim().toLowerCase()).find() == false){
                                        System.out.println("Getting here in CONTACT EFFORTS CONTINUE");
                                    }
                                    else if (Pattern.compile(Pattern.quote(ACCOUNT_STATUS_OPTIONS[4]),Pattern.CASE_INSENSITIVE).matcher(TESTER[k].trim().toLowerCase()).find() == false){
                                        System.out.println("Getting here in UNRESPONSIVE TO CALLS/MAIL");
                                    }
                                }
                              }
                          }
                          System.out.println("DELIMITER_IGNORE FINISHED");
                      }
                }
            }
         }
                
    }
}
         
