package com.visualnuts.technicaltest.exception;

import static com.visualnuts.technicaltest.exception.ExceptionCode.INVALID_PARAMETER;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.visualnuts.technicaltest.base.BaseTestSuite;

public class ApplicationExceptionTest extends BaseTestSuite {
	
	@Test
	void constructor() {
		ApplicationException applicationException = new ApplicationException(INVALID_PARAMETER, "test");
		assertEquals(INVALID_PARAMETER, applicationException.getCode());
		assertEquals("Exception code: 1000 - Invalid parameter - Parameter should be test", applicationException.getMessage());
	}
}
