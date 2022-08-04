package com.visualnuts.technicaltest.exception;

import static com.visualnuts.technicaltest.exception.ExceptionCode.INVALID_DATA;
import static com.visualnuts.technicaltest.exception.ExceptionCode.INVALID_PARAMETER;
import static com.visualnuts.technicaltest.exception.ExceptionCode.NON_UNIQUE_RESULT;
import static com.visualnuts.technicaltest.exception.ExceptionCode.NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

import com.visualnuts.technicaltest.base.BaseTestSuite;

class ExceptionCodeTest extends BaseTestSuite {

	@Test
	void values() {
		assertArrayEquals(ExceptionCode.values(), Arrays.array(INVALID_PARAMETER, INVALID_DATA, NOT_FOUND, NON_UNIQUE_RESULT));
	}

	@Test
	void getCode() {
		assertEquals(1000, INVALID_PARAMETER.getCode());
		assertEquals(1001, INVALID_DATA.getCode());
		assertEquals(1002, NOT_FOUND.getCode());
		assertEquals(1003, NON_UNIQUE_RESULT.getCode());
	}
	
	@Test
	void getMessage() {
		assertEquals("Invalid parameter - Parameter should be %s", INVALID_PARAMETER.getMessage());
		assertEquals("Invalid data", INVALID_DATA.getMessage());
		assertEquals("Not found", NOT_FOUND.getMessage());
		assertEquals("The result is not a unique value", NON_UNIQUE_RESULT.getMessage());
	}

}
