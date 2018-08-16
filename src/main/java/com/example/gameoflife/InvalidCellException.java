package com.example.gameoflife;

public class InvalidCellException extends RuntimeException{
    public InvalidCellException(Throwable cause) {
        super(cause);
    }
}
