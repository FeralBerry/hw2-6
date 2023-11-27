package com.example.demo.Exeptions;

public class EmployeeStorageIsFullException extends Exception{
    public EmployeeStorageIsFullException(String message){
        super(message);
    }
}
