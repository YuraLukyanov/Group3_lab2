package model.implementetion.oracle.exceptions;

public class WrongIDException extends IllegalArgumentException {
    public WrongIDException() {
        super();
    }

    public WrongIDException(String s) {
        super(s);
    }
}
