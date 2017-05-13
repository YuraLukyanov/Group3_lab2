package controller.exceptions;

/**
 * Created by Nikolion on 13.05.2017.
 */
public class WebServerException extends Exception{
    public WebServerException(){
        super();
    }
    public WebServerException(Throwable e){
        super(e);
    }
    public WebServerException(String message, Throwable e){
        super(message, e);
    }
    public WebServerException(String message){
        super(message);
    }
}

