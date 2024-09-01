/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.southerncreditrecoveryautomationtool;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author fonta
 */
public class ProgressReport {
    
    public ArrayList<PaymentRecord> LIST_OF_RECORDS;
    public int CLIENT_ID;
    public String CLIENT_NAME;
    public int RECORD_COUNT;
    public LocalDate REPORT_DATE;
    public String TIMESTAMP;
    
    
    public ProgressReport() {
        // Empty Constructor
    }
    
    public ProgressReport(ArrayList<PaymentRecord> list, int id, String name, int number, LocalDate date, String time) {
        this.LIST_OF_RECORDS = list;
        this.CLIENT_ID = id;
        this.CLIENT_NAME = name;
        this.RECORD_COUNT = number;
        this.REPORT_DATE = date;
        this. TIMESTAMP = time;
    }
    
    public ArrayList<PaymentRecord>  getRecordsList() {
        return this.LIST_OF_RECORDS;
    }
    
    public int getClientID() {
        return this.CLIENT_ID;
    }
    
    public String getClientName() {
        return this.CLIENT_NAME;
    }
    
    public int getRecordCount() {
        return this.RECORD_COUNT;
    }
    
    public LocalDate getFileCreationDate() {
        return this.REPORT_DATE;
    }
    
    public String getFileCreationTimestamp() {
        return this.TIMESTAMP;
    }
    
    public void setRecordsList(ArrayList<PaymentRecord> list) {
        this.LIST_OF_RECORDS = list;
    }
    
    public void setClientID(int id) {
        this.CLIENT_ID = id;
    }
    
    public void setFileCreationDate(LocalDate date) {
        this.REPORT_DATE = date;
    }
    
    public void setFileCreationTimestamp(String time) {
        this.TIMESTAMP = time;
    }
    
    public void setClientName(String name) {
        this.CLIENT_NAME = name;
    }
    
    public void setRecordCount(int number) {
        this.RECORD_COUNT = number;
    }
        
    public void addPaymentRecord(PaymentRecord record) {
        this.LIST_OF_RECORDS.add(record);
    }
    
    public void increaseRecordCount() {
        this.RECORD_COUNT++;
    }
}
