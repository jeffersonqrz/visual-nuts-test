package com.visualnuts.technicaltest.util;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.visualnuts.technicaltest.base.BaseTestSuite;
import com.visualnuts.technicaltest.exception.ApplicationException;
import com.visualnuts.technicaltest.exception.ExceptionCode;

public class AssertTest extends BaseTestSuite {
	
	@Test
	void isTrue() {
		assertTrue(Assert.isTrue(true, ExceptionCode.INVALID_DATA));
		assertThrows(ApplicationException.class, () -> Assert.isTrue(false, ExceptionCode.INVALID_DATA));
	}
}
