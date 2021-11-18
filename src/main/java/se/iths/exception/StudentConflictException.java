package se.iths.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class StudentConflictException extends RuntimeException {
    public StudentConflictException(String message) {
        super(message);
    }
}
