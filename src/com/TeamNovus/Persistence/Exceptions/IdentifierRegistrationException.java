package com.TeamNovus.Persistence.Exceptions;

public class IdentifierRegistrationException extends Exception {
	private static final long serialVersionUID = 1L;

	public IdentifierRegistrationException(String message) {
		super(message);
	}

	public IdentifierRegistrationException(String message, Throwable cause) {
		super(message, cause);
	}

	public IdentifierRegistrationException(Throwable cause) {
		super(cause);
	}
}
