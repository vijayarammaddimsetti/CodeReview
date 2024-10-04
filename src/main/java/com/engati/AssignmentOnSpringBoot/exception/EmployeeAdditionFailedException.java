package com.engati.AssignmentOnSpringBoot.exception;

public class EmployeeAdditionFailedException extends RuntimeException{
    public EmployeeAdditionFailedException(String message) {
        super(message);
    }
}
