package com.ktdsuniversity.edu.exceptions.handler;

public class HelloSpringException extends RuntimeException {
	
private static final long serialVersionUID = 5344517791626726005L;
	
	private String errorPage;
	
	private Object object;
	
	public HelloSpringException(String message, String errorPage) {
		super(message);
		this.errorPage = errorPage;
	}

	public HelloSpringException(String message, String errorPage, Object object) {
		super(message);
		this.errorPage = errorPage;
		this.object = object;
	}

	public String getErrorPage() {
		return this.errorPage;
	}

	public Object getObject() {
		return this.object;
	}

}
