/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.southerncreditrecoveryautomationtool;

import java.time.LocalDate;

/**
 *
 * @author fonta
 */
public class PaymentRecord {
    
    public int ACCOUNT_NUMBER;
    public String ACCOUNT_NAME;
    public LocalDate LISTED_DATE;
    public double LISTED_PRICE;
    public LocalDate LAST_PAYMENT;
    public double REMAINING_BALANCE;
    public int ATTEMPTED_CALLS;
    public int LETTERS_MAILED;
    public String ACCOUNT_STATUS;
    
    public PaymentRecord() {
        //Empty Constructor
    }
    
     public PaymentRecord(int acc_num, String name, LocalDate list_date, double list_price, LocalDate last_pay, double balance, int calls, int letter,  String account_status) {
        this.ACCOUNT_NUMBER = acc_num;
        this.ACCOUNT_NAME = name;
        this.LISTED_DATE = list_date;
        this.LISTED_PRICE = list_price;
        this.LAST_PAYMENT = last_pay;
        this.REMAINING_BALANCE = balance;
        this.ATTEMPTED_CALLS = calls;
        this.LETTERS_MAILED = letter;
        this.ACCOUNT_STATUS = account_status;
    }
    
     public int getAccountNumber() {
         return this.ACCOUNT_NUMBER;
     }
     
     public String getAccountName() {
         return this.ACCOUNT_NAME;
     }
     
     public LocalDate getListDate() {
         return this.LISTED_DATE;
     }
     
     public double getListPrice() {
         return this.LISTED_PRICE;
     }
     
     public LocalDate getLastPaymentDate() {
         return this.LAST_PAYMENT;
     }
     
     public double getRemainingBalance() {
         return this.REMAINING_BALANCE;
     }
     
     public int getNumberOfAttemptedCalls() {
         return this.ATTEMPTED_CALLS;
     }
     
     public int getNumberOfLettersMailed() {
         return this.LETTERS_MAILED;
     }
     
     public String getAccountStatus() {
         return this.ACCOUNT_STATUS;
     }
     
     public void setAccountNumber(int number) {
         this.ACCOUNT_NUMBER = number;
     }
     
     public void setAccountName(String name) {
         this.ACCOUNT_NAME = name;
     }
     
     public void setListedDate(LocalDate date) {
         this.LISTED_DATE = date;
     }
     
     public void setListedPrice(double number) {
         this.LISTED_PRICE = number;
     }
     
     public void setLastPaymentDate(LocalDate date) {
         this.LISTED_DATE = date;
     }
     
     public void setRemainingBalance(double balance) {
         this.REMAINING_BALANCE = balance;
     }
     
     public void setAttemptedCalls(int number) {
         this.ATTEMPTED_CALLS = number;
     }
     
     public void setLettersMailed(int number) {
         this.LETTERS_MAILED = number;
     }
     
     public void setAccountStatus(String status) {
         this.ACCOUNT_STATUS = status;
     }
}
