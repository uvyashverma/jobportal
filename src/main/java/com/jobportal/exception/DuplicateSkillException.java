package com.jobportal.exception;

public class DuplicateSkillException extends RuntimeException {

	private static final long serialVersionUID = -8415532512576546747L;

	public DuplicateSkillException() {
		super();
	}

	public DuplicateSkillException(String message) {
		super(message);
	}

}
