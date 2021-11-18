package se.iths.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {
        super(message);
    }
}
