/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.southerncreditrecoveryautomationtool;

import com.opencsv.CSVReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author fonta
 */
public class SouthernCreditRecoveryAutomationTool {

    private static final String SAMPLE_CSV_FILE_PATH = "C:\\Users\\fonta\\Downloads\\Tester.csv.CSV";

    public static void main(String[] args) throws IOException {
        
             try {
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
                CSVReader csvReader = new CSVReader(reader);

                // Reading Records One by One in a String array
               String[] nextRecord;
                String[] csvFormat;

                while ((nextRecord  = csvReader.readNext()) != null) {
                    System.out.println("==========================");
                }

                System.out.println("Start: " + nextRecord.length);
        } 
        catch (NullPointerException n) {
            System.out.println(n.fillInStackTrace());
        }
        catch (EOFException e) {
            System.out.println(e.fillInStackTrace());
        }
                /* Reading Records One by One in a String array
                     String[] nextRecord;
                     for  (int i = 0; (nextRecord = csvReader.readNext()) != null;  i++) {
                            System.out.println(""  + nextRecord[ i ].split("|").toString());
                            System.out.println("==========================");
                     }*/
    }
}
