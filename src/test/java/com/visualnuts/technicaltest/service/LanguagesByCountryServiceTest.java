package com.visualnuts.technicaltest.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.visualnuts.technicaltest.base.BaseTestSuite;
import com.visualnuts.technicaltest.exception.ApplicationException;
import com.visualnuts.technicaltest.exception.ExceptionCode;

@ExtendWith(OutputCaptureExtension.class)
public class LanguagesByCountryServiceTest extends BaseTestSuite {

	private static final String EXPECTED_FILE_NAME = "countries_data.json";

	@BeforeEach
	void initialize() throws IOException {
		loadResource(EXPECTED_FILE_NAME);
	}

	@Test
	void loadResourceData() {
		try {
			languagesByCountryService.loadResourceData();
		} catch (Exception e) {
			fail("Error on load resource");
		}
	}

	@Test
	void loadResourceDataWhenResourceIsInvalid() throws IOException {
		loadResource("countries_data_invalid.json");
		ApplicationException thrown = assertThrows(ApplicationException.class,
				() -> languagesByCountryService.loadResourceData(), "any exception");
		assertEquals(ExceptionCode.INVALID_DATA, thrown.getCode());
		assertEquals("Exception code: 1001 - Invalid data", thrown.getMessage());
	}

	@Test
	void getNumbersOfCountriesInTheWorld() {
		loadResourceData();
		assertEquals(9, languagesByCountryService.countNumbersOfCountriesInTheWorld());
	}

	@Test
	void findCountryWithMostOfficialLanguageWhereSpeakGerman() {
		loadResourceData();
		assertEquals("DE", languagesByCountryService.findCountryWithMostOfficialLanguageWhereSpeakGerman());
	}

	@Test
	void findCountryWithMostOfficialLanguageWhereSpeakGermanWhenNoDataFound() {
		loadResource("countries_data2.json");
		loadResourceData();
		ApplicationException thrown = assertThrows(ApplicationException.class,
				() -> languagesByCountryService.findCountryWithMostOfficialLanguageWhereSpeakGerman(), "any exception");
		assertEquals(ExceptionCode.NOT_FOUND, thrown.getCode());
		assertEquals("Exception code: 1002 - Not found", thrown.getMessage());
	}

	@Test
	void countAllOfficialLanguages() {
		loadResourceData();
		assertEquals(6, languagesByCountryService.countAllOfficialLanguages());
	}

	@Test
	void findCountryWithHighestNumberOfOfficialLanguages() {
		loadResourceData();
		assertEquals("SD", languagesByCountryService.findCountryWithHighestNumberOfOfficialLanguages());
	}

	@Test
	void findMostCommonOfficialLanguage(CapturedOutput output) {
		loadResourceData();
		assertEquals("es", languagesByCountryService.findMostCommonOfficialLanguage());
		String firstLogLine = output.getAll().lines().filter(m -> m.contains("WARN")).findFirst().get();
		assertThat(firstLogLine).contains(
				"More than a single result was found! A specific rule to treat these behaviours is not defined.");
	}

	@Test
	void findMostCommonOfficialLanguageWhenResultIsMoreThanOne() {
		loadResource("countries_data3.json");
		loadResourceData();
		assertEquals("en", languagesByCountryService.findMostCommonOfficialLanguage());
		
	}

	private void loadResource(String resourceName) {
		ReflectionTestUtils.setField(languagesByCountryService, "dataFileName", resourceName);
	}

	@InjectMocks
	private LanguagesByCountryService languagesByCountryService;

}
