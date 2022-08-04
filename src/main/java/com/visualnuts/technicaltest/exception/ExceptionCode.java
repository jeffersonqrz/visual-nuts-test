package com.visualnuts.technicaltest.exception;

public enum ExceptionCode {

	INVALID_PARAMETER(1_000, "Invalid parameter - Parameter should be %s"),
	INVALID_DATA(1_001, "Invalid data"),
	NOT_FOUND(1_002, "Not found"),
	NON_UNIQUE_RESULT(1_003, "The result is not a unique value");

	ExceptionCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	private int code;
	private String message;

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
