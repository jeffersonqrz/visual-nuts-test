package com.visualnuts.technicaltest.service;

import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.visualnuts.technicaltest.exception.ExceptionCode;
import com.visualnuts.technicaltest.util.Assert;

@Service
public class VisualNutsAlgorithmService {

	private static final int STR_VISUAL = 3;
	private static final int STR_NUTS = 5;
	private static final Logger LOG = LoggerFactory.getLogger(VisualNutsAlgorithmService.class);

	public void printNumberUpToLimit(int limit) {
		validateParameter(limit);
		printNumbers(limit);
	}

	private void validateParameter(int limit) {
		Assert.isTrue(limit > 0, ExceptionCode.INVALID_PARAMETER, "positive");
	}

	private void printNumbers(int limit) {
		IntStream.rangeClosed(1, limit) //
				.mapToObj(i -> isDivisibleBy(i, STR_VISUAL) ? isDivisibleBy(i, STR_NUTS) ? "Visual Nuts" : "Visual"
						: isDivisibleBy(i, STR_NUTS) ? "Nuts" : String.valueOf(i)) //
				.forEach(LOG::info);

	}

	private boolean isDivisibleBy(int number, int divider) {
		return number % divider == 0;
	}

}
