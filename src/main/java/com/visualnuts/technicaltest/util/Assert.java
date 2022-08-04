package com.visualnuts.technicaltest.util;

import com.visualnuts.technicaltest.exception.ApplicationException;
import com.visualnuts.technicaltest.exception.ExceptionCode;

public class Assert {

	private Assert() {
		// Utility class
	}

	public static boolean isTrue(boolean expression, ExceptionCode exceptionCode, String... interpoledParameters) {
		if (!expression) {
			throw new ApplicationException(exceptionCode, interpoledParameters);
		}
		return true;
	}

}
