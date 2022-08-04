package com.visualnuts.technicaltest.exception;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final ExceptionCode exceptionCode;
	private final String[] interpoledParameters;

	public ApplicationException(ExceptionCode code, String... interpoledParameters) {
		this.exceptionCode = code;
		this.interpoledParameters = interpoledParameters;
	}

	@Override
	public String getMessage() {
		return String.format("Exception code: %s - %s", this.exceptionCode.getCode(),
				String.format(this.exceptionCode.getMessage(), this.interpoledParameters));
	}

	public ExceptionCode getCode() {
		return exceptionCode;
	}

}
