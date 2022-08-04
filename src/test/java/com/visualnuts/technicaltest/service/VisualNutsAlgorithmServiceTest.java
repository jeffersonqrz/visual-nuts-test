package com.visualnuts.technicaltest.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import com.visualnuts.technicaltest.base.BaseTestSuite;
import com.visualnuts.technicaltest.exception.ApplicationException;
import com.visualnuts.technicaltest.exception.ExceptionCode;

@ExtendWith(OutputCaptureExtension.class)
class VisualNutsAlgorithmServiceTest extends BaseTestSuite {

	@Test
	void printNumbersUpToLimit(CapturedOutput output) {
		int limit = 100;
		visualNutsAlgorithm.printNumberUpToLimit(limit);
		assertThat(output.getErr()).isBlank();
		List<String> lines = output.getOut().lines().collect(Collectors.toList());
		for (int i = 0; i < lines.size(); i++) {
			if ((i + 1) % 3 == 0 && (i + 1) % 5 == 0) {
				assertThat(lines.get(i)).endsWith("Visual Nuts");
			} else if ((i + 1) % 3 == 0) {
				assertThat(lines.get(i)).endsWith("Visual");
			} else if ((i + 1) % 5 == 0) {
				assertThat(lines.get(i)).endsWith("Nuts");
			} else {
				assertThat(lines.get(i)).endsWith(String.valueOf(i + 1));
			}
		}
	}
	

	@Test
	void printNumbersUpToLimitWhenLimitIsNegative() {
		ApplicationException thrown = assertThrows(ApplicationException.class,
				() -> visualNutsAlgorithm.printNumberUpToLimit(-1));
		assertEquals(ExceptionCode.INVALID_PARAMETER, thrown.getCode());
		assertEquals("Exception code: 1000 - Invalid parameter - Parameter should be positive", thrown.getMessage());
	}

	@Test
	void printNumbersUpToLimitWhenLimitIsZero() {
		ApplicationException thrown = assertThrows(ApplicationException.class,
				() -> visualNutsAlgorithm.printNumberUpToLimit(0));
		assertEquals(ExceptionCode.INVALID_PARAMETER, thrown.getCode());
		assertEquals("Exception code: 1000 - Invalid parameter - Parameter should be positive", thrown.getMessage());
	}

	@InjectMocks
	private VisualNutsAlgorithmService visualNutsAlgorithm;

}
