/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.southerncreditrecoveryautomationtool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author fonta
 */
public class ThibodauxNotesConverterTool {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    
        System.out.println("Enter the path to the file: ");
         String path = sc.nextLine();
    
         FileInputStream file;
         
        try {
             file = new FileInputStream(new File(path));
             Workbook workbook = new XSSFWorkbook(file);
             Sheet sheet = (Sheet) workbook.getSheetAt(0);

        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(ThibodauxNotesConverterTool.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ThibodauxNotesConverterTool.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    Map<Integer, List<String>> data = new HashMap<>();

    }
}

