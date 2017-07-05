package model.implementetion.oracle.exceptions;

public class DBConnectionException extends Exception {
    public DBConnectionException() {
    }

    public DBConnectionException(String message) {
        super(message);
    }
}
