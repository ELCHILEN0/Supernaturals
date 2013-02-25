package com.TeamNovus.Persistence.Exceptions;

public class TableRegistrationException extends Exception {
	private static final long serialVersionUID = 1L;

	public TableRegistrationException(String message) {
		super(message);
	}

	public TableRegistrationException(String message, Throwable cause) {
		super(message, cause);
	}

	public TableRegistrationException(Throwable cause) {
		super(cause);
	}
}
