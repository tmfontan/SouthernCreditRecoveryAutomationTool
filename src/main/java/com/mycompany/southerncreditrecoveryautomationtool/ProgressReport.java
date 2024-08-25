/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.southerncreditrecoveryautomationtool;

import java.util.ArrayList;

/**
 *
 * @author fonta
 */
public class ProgressReport {
    
    public ArrayList<PaymentRecord> LIST_OF_RECORDS;
    public String COMPANY_NAME;
    public int RECORD_COUNT;
    
    public ProgressReport() {
        // Empty Constructor
    }
    
    public ProgressReport(ArrayList<PaymentRecord> list, String name, int number) {
        this.LIST_OF_RECORDS = list;
        this.COMPANY_NAME = name;
        this.RECORD_COUNT = number;
    }
    
    public ArrayList<PaymentRecord>  getRecordsList() {
        return this.LIST_OF_RECORDS;
    }
    
    public String getCompanyName() {
        return this.COMPANY_NAME;
    }
    
    public int getRecordCount() {
        return this.RECORD_COUNT;
    }
    
    public void setRecordsList(ArrayList<PaymentRecord> list) {
        this.LIST_OF_RECORDS = list;
    }
    
    public void setCompanyName(String name) {
        this.COMPANY_NAME = name;
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
