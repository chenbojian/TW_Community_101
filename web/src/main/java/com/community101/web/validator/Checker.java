package com.community101.web.validator;

/**
 * Created by MiffyLiye on 27/07/2015.
 */
public abstract class Checker {
    public abstract boolean checkError(Object obj);
    public abstract DTOErrorType getErrorType();
}