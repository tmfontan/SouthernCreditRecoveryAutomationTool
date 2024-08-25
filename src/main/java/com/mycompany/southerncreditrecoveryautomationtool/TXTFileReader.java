/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.southerncreditrecoveryautomationtool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author fonta
 */
public class TXTFileReader {

   public static void main(String[] args) throws Exception {

        File DESOTO_TXT = new File("C:\\Users\\fonta\\Downloads\\DESOTO19.TXT");
                
        ProgressReport PROGRESS_REPORT;
        PaymentRecord PAYMENT_RECORD;
        
        CharSequence[] HEADERS_AND_FOOTERS = new CharSequence[5];
        
        HEADERS_AND_FOOTERS[0] = new StringBuilder("====================================================================================================================================");
        HEADERS_AND_FOOTERS[1] = new StringBuilder("DATE     AMOUNT    DATE      TOTAL");
        HEADERS_AND_FOOTERS[2] = new StringBuilder("YOUR ACCOUNT NUMBER AND NAME         LISTED    LISTED  LAST PAY    PAY/ADJ BALANCE  CALLS/LETTERS  ACCOUNT STATUS");
        HEADERS_AND_FOOTERS[3] = new StringBuilder("SOUTHERN CREDIT RECOVERY, INC.                                                   PAGE");
        HEADERS_AND_FOOTERS[4] = new StringBuilder("SLP                              PROGRESS/STATUS REPORT FOR ");

        String[] BLANK_SPACE = new String[5];
        
        BLANK_SPACE[0] = "                 ";
        BLANK_SPACE[1] = "    ";
        BLANK_SPACE[2] = "    ";
        
        String[] ROW_DATA;
        String[] LINE_SPLIT = null;

        String LINE = "";
        
        int ROW_COUNT = 0;
        int INDEX_TO_LINE = 0;
        int RECORD_COUNT = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(DESOTO_TXT))) {
            while ((LINE = br.readLine()) != null) {
                    ROW_COUNT++;
            }
        } 
        catch (IOException ioe) {
            System.out.println("File Input/Output Exception has Occured.");
            ioe.printStackTrace();
        }
        
       RECORD_COUNT = ((ROW_COUNT - 2) - 9);
        System.out.println("This is the Row Count: " +  ROW_COUNT);
        
        try (BufferedReader br2 = new BufferedReader(new FileReader(DESOTO_TXT))) {
            
            ROW_DATA = new String[ROW_COUNT];
            
            while((LINE = br2.readLine()) != null) {
                
                LINE_SPLIT = LINE.trim().split("\n", 2);
                
                 if ((LINE_SPLIT[0].isBlank()) ||( LINE_SPLIT[0].isEmpty()) || LINE_SPLIT[0] == null) {
                    // Ignore the Current Row.
                } 
                 else if (LINE_SPLIT[0].contains(HEADERS_AND_FOOTERS[0]) || LINE_SPLIT[0].contains(HEADERS_AND_FOOTERS[1]) || LINE_SPLIT[0].contains(HEADERS_AND_FOOTERS[2]) || LINE_SPLIT[0].contains(HEADERS_AND_FOOTERS[3]) || LINE_SPLIT[0].contains(HEADERS_AND_FOOTERS[4])) {
                    // Ignore the Current Row.
                } 
                 else {
                    ROW_DATA[INDEX_TO_LINE] = LINE_SPLIT[0];
                }
                INDEX_TO_LINE++;
            }
            
            PROGRESS_REPORT = new ProgressReport();
            
            for (int i = 0; i < ROW_DATA.length; i++) {
                    if (ROW_DATA[i] == null) {
                        // Ignore the Current Row
                    }
                    else {
                        PAYMENT_RECORD = new PaymentRecord();
                        
                        // Break apart the String containing the current row's data to isolate the date present in the LISTED_DATE column.
                        LocalDate LISTED_DATE =  LocalDate.of(       Integer.parseInt(ROW_DATA[i].split(BLANK_SPACE[0])[1].substring(0,8).split("/")[2]),
                                                                                                                   Integer.parseInt(ROW_DATA[i].split(BLANK_SPACE[0])[1].substring(0,8).split("/")[0]), 
                                                                                                        Integer.parseInt(ROW_DATA[i].split(BLANK_SPACE[0])[1].substring(0,8).split("/")[1]));
                                                
                        // If the YEAR set as the year inthe  "Listed Date" column of the current row  is EQUAL to the current physical year, then create a new LocalDate Object
                        // with the YEAR value of 2024.
                        if ((Integer.parseInt(ROW_DATA[i].split(BLANK_SPACE[0])[1].substring(0,8).split("/")[2]) + 2000) == (Integer.parseInt(Year.now().toString()))) {
                                System.out.println("The Modulus and Year Values are Equal");
                                LISTED_DATE = LocalDate.of(    Integer.parseInt(Year.now().toString()),
                                                                                               Integer.parseInt(ROW_DATA[i].split(BLANK_SPACE[0])[1].substring(0,8).split("/")[0]), 
                                                                                    Integer.parseInt(ROW_DATA[i].split(BLANK_SPACE[0])[1].substring(0,8).split("/")[1]));  
                                PAYMENT_RECORD.setListedDate(LISTED_DATE);

                        }
                        // IF THE EXPERIMENTAL VALUE OF YEAR.NOW() + 2000 IS GREATER THAN THE CURRENT YEAR, THEN THE YEAR VALUE ON FILE HAS TO TAKE PLACE DURING
                        // THE 1900s. THUS, CREATE A LOCALDATE OBJECT WITH THE CORRECT YEAR.
                        else if ((Integer.parseInt(ROW_DATA[i].split(BLANK_SPACE[0])[1].substring(0,8).split("/")[2]) + 2000) < (Integer.parseInt(Year.now().toString()))) {
                            System.out.println("The Hypothetical Year is greater than the current Year.");
                            LISTED_DATE = LocalDate.of(                Integer.parseInt(ROW_DATA[i].split(BLANK_SPACE[0])[1].substring(0,8).split("/")[2]) + 1900,
                                                                                               Integer.parseInt(ROW_DATA[i].split(BLANK_SPACE[0])[1].substring(0,8).split("/")[0]), 
                                                                                    Integer.parseInt(ROW_DATA[i].split(BLANK_SPACE[0])[1].substring(0,8).split("/")[1]));
                            PAYMENT_RECORD.setListedDate(LISTED_DATE);

                        }
                        // IF THE EXPERIMENTAL VALUE OF YEAR.NOW() + 2000 IS LESS THAN THE CURRENT YEAR, THEN THE YEAR VALUE ON FILE HAS TO TAKE PLACE DURING
                        // THE 2000s. THUS, CREATE A LOCALDATE OBJECT WITH THE CORRECT YEAR.
                        else {
                            System.out.println("The Hypothetical Year is less than the current Year.");
                            LISTED_DATE = LocalDate.of(                Integer.parseInt(ROW_DATA[i].split(BLANK_SPACE[0])[1].substring(0,8).split("/")[2]) + 2000,
                                                                                               Integer.parseInt(ROW_DATA[i].split(BLANK_SPACE[0])[1].substring(0,8).split("/")[0]), 
                                                                                    Integer.parseInt(ROW_DATA[i].split(BLANK_SPACE[0])[1].substring(0,8).split("/")[1]));  
                            PAYMENT_RECORD.setListedDate(LISTED_DATE);
                        }
                        
                        System.out.println("Row DATA: " +  ROW_DATA[i]);
                        
                        LocalDate LAST_PAYMENT =  LocalDate.of(       Integer.parseInt(ROW_DATA[i].split(BLANK_SPACE[0])[1].substring(0,8).split("/")[2]),
                                                                                                                       Integer.parseInt(ROW_DATA[i].split(BLANK_SPACE[0])[1].substring(0,8).split("/")[0]), 
                                                                                                            Integer.parseInt(ROW_DATA[i].split(BLANK_SPACE[0])[1].substring(0,8).split("/")[1]));
                        
                        
                        /*
                        System.out.println("Account Number: " + ROW_DATA[i].split(" ")[0]);
                        System.out.println("Account Name: " + ROW_DATA[i].split(" ")[1].split("\t")[0] + " " + ROW_DATA[i].split(BLANK_SPACE[0])[0].substring( ROW_DATA[i].split(BLANK_SPACE[0])[0].length() - 1));
                        System.out.println("Listed Month: " + ROW_DATA[i].split(BLANK_SPACE[0])[1].substring(0,8).split("/")[0]);
                        System.out.println("Listed Day: " + ROW_DATA[i].split(BLANK_SPACE[0])[1].substring(0,8).split("/")[1]);
                        System.out.println("Listed Year: " + ROW_DATA[i].split(BLANK_SPACE[0])[1].substring(0,8).split("/")[2]);
                        System.out.println("Listed Date: " + LocalDate.of(          Integer.parseInt(ROW_DATA[i].split(BLANK_SPACE[0])[1].substring(0,8).split("/")[2]),
                                                                                                                                                Integer.parseInt(ROW_DATA[i].split(BLANK_SPACE[0])[1].substring(0,8).split("/")[0]), 
                                                                                                                                     Integer.parseInt(ROW_DATA[i].split(BLANK_SPACE[0])[1].substring(0,8).split("/")[1])));*/
                        
                        
                        
                        PAYMENT_RECORD.setAccountNumber(Integer.parseInt(ROW_DATA[i].split(" ")[0]));
                        PAYMENT_RECORD.setAccountName(ROW_DATA[i].split(" ")[1].split("\t")[0] + " " + ROW_DATA[i].split(BLANK_SPACE[0])[0].substring( ROW_DATA[i].split(BLANK_SPACE[0])[0].length() - 1));
                        PAYMENT_RECORD.setListedPrice(Double.parseDouble(ROW_DATA[i].split("\t")[3]));
                        PAYMENT_RECORD.setListedDate(LocalDate.parse(ROW_DATA[i].split("\t")[4], DateTimeFormatter.BASIC_ISO_DATE));
                        PAYMENT_RECORD.setRemainingBalance(Double.parseDouble(ROW_DATA[i].split("\t")[5]));
                        PAYMENT_RECORD.setAttemptedCalls(Integer.parseInt(ROW_DATA[i].split("\t")[6]));
                        PAYMENT_RECORD.setLettersMailed(Integer.parseInt(ROW_DATA[i].split("\t")[7]));
                        PAYMENT_RECORD.setAccountStatus(ROW_DATA[i].split("\t")[8]);
                        
                        PROGRESS_REPORT.addPaymentRecord(PAYMENT_RECORD);
                        
                        System.out.println("Line #" + i + ": " + ROW_DATA[i]);
                    }
             }
            
            System.out.println("The Number of Records Present are: " + PROGRESS_REPORT.getRecordCount());
            
            //System.out.println("The length of the rowData String Array is: " + ROW_DATA.length);
            
            }
            catch (IOException e) {
                System.out.println("Input / Output Exception");
                e.printStackTrace();
        }
        catch (NumberFormatException nfe) {
            System.out.println("Number Format Exception");
            nfe.printStackTrace();
        }
        //catch (ExecuteException ee) {
            
       // }
    }
}
