package com.example.demo.Exeptions;

public class EmployeeAlreadyAddedException extends Exception{
    public EmployeeAlreadyAddedException(String message){
        super(message);
    }
}
