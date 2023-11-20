package com.example.demo.Services;

import com.example.demo.Employee;
import com.example.demo.Exeptions.EmployeeAlreadyAddedException;
import com.example.demo.Exeptions.EmployeeNotFoundException;
import com.example.demo.Exeptions.EmployeeStorageIsFullException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

// @Service - аннотация для создания функций приложения
@Service
public class EmployeeService {
    ObjectMapper mapper = new ObjectMapper();
    private final Integer MAX_COUNT_EMPLOYEES = 7;
    private final ArrayList<Object> employeesList = new ArrayList<>();

    public ArrayList<Object> add(String firstName, String lastName) throws JsonProcessingException {
        try{
            validateMaxCountEmployee(employeesList.size());
            validateEmployeeName(firstName,lastName);
            employeesList.add(employeesList.size(),mapper.writeValueAsString(new Employee(firstName,lastName)));
        } catch (EmployeeStorageIsFullException | EmployeeAlreadyAddedException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return employeesList;
    }
    public String remove(String firstName, String lastName) throws JsonProcessingException {
        try {
            validateSearch(firstName,lastName);
            for (int i = 0; i < employeesList.size();i++){
                if(employeesList.get(i).equals(mapper.writeValueAsString(new Employee(firstName,lastName)))){
                    employeesList.remove(mapper.writeValueAsString(new Employee(firstName,lastName)));
                }
            }
        } catch (EmployeeNotFoundException e){
            System.out.println("Ошибка: " + e.getMessage());
        }
        return employeesList.toString();
    }
    public String find(String firstName, String lastName) throws JsonProcessingException {
        String employee = null;
        try {
            validateSearch(firstName,lastName);
            for (Object o : employeesList) {
                if (o.equals(mapper.writeValueAsString(new Employee(firstName, lastName)))) {
                    employee = (String) o;
                }
            }
        }catch (EmployeeNotFoundException e){
            System.out.println("Ошибка: " + e.getMessage());
        }
        return employee;
    }
    private void validateMaxCountEmployee(Integer max) throws EmployeeStorageIsFullException {
        if (max >= MAX_COUNT_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Превышено допустимое количество сотрудников");
        }
    }
    private void validateEmployeeName(String firstName, String lastName) throws EmployeeAlreadyAddedException, JsonProcessingException {
        for (Object o : employeesList) {
            if (o.equals(mapper.writeValueAsString(new Employee(firstName, lastName)))) {
                throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть.");
            }
        }
    }
    private void validateSearch(String firstName, String lastName) throws EmployeeNotFoundException, JsonProcessingException {
        for (Object o : employeesList) {
            if (!o.equals(mapper.writeValueAsString(new Employee(firstName, lastName)))) {
                throw new EmployeeNotFoundException("Такого сотрудника нет.");
            }
        }
    }
}
