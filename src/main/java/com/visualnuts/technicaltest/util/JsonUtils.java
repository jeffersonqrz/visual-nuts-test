package com.visualnuts.technicaltest.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtils {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	private JsonUtils() {
		// Utility class
	}

	public static boolean isValid(String json) {
		try {
			MAPPER.readTree(json);
		} catch (JacksonException e) {
			return false;
		}
		return true;
	}

}
