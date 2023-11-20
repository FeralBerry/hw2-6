package com.example.demo;

public class Employee {
    private final String lastName;
    private final String firstName;
    public Employee(String lastName,String firstName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
    @Override
    public String toString() {
        return "firstName: " + firstName + " lastName: " + lastName;
    }
    @Override
    public int hashCode() {
        return java.util.Objects.hash(this.firstName+this.lastName);
    }
    @Override
    public boolean equals(Object other) {
        if (this.getClass() != other.getClass()) {
            return false;
        }
        Employee c2 = (Employee) other;
        return this.firstName.equals(c2.firstName) && this.lastName.equals(c2.lastName);
    }
}
