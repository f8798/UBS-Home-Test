package com.jyh.othello.exception;

public class InvalidPositionException extends Exception {
	
	public InvalidPositionException() {
		super();
	}
	
	public InvalidPositionException(String msg) {
		super(msg);
	}
}
