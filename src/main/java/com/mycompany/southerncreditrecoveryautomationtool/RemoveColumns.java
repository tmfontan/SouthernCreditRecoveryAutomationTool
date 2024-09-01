/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.southerncreditrecoveryautomationtool;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author fonta
 */
public class RemoveColumns {
    public static void main(String[] args) { 
            Scanner SCANNER = new Scanner(System.in);
            Scanner DELIMITER_CHECK;

            System.out.print("Please enter the file path: ");
            String FILE_PATH = SCANNER.nextLine();

            while (FILE_PATH.contains("\"") || FILE_PATH.contains("\'") || FILE_PATH.contains("/")) {
                if (FILE_PATH.contains("\"") || FILE_PATH.contains("\'")) {
                    FILE_PATH = FILE_PATH.substring(1, (FILE_PATH.length() - 1));
                }
                if (FILE_PATH.contains("/")) {
                    FILE_PATH.replaceAll("/", "\\");
                }
            }
            
            try {
                File DESOTO_TXT = new File(FILE_PATH);
                DELIMITER_CHECK = new Scanner(DESOTO_TXT).useDelimiter("\t");

                while (DELIMITER_CHECK.hasNextLine()) {
                    //Print the contents of a file by line .
                    //System.out.print(DELIMITER_CHECK.nextLine());
                    String TEMP = DELIMITER_CHECK.nextLine();
                    TEMP = TEMP.substring(0, (TEMP.length() - 6));
                    
                    System.out.println(TEMP);
                 }
            }
            catch (FileNotFoundException e) {
                
            }
    }
}
