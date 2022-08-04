package com.visualnuts.technicaltest.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.visualnuts.technicaltest.base.BaseTestSuite;

public class JsonUtilsTest extends BaseTestSuite {

	@Test
	void isValid() {
		assertTrue(JsonUtils.isValid("{\"name\": \"Visal Nuts\"}"));
		assertFalse(JsonUtils.isValid("{\"name:\" \"Visal Nuts }"));
	}

}
