package se.iths.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class EmptyListException extends RuntimeException{
    public EmptyListException(String message) {
        super(message);
    }
}
