package com.visualnuts.technicaltest.app;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.visualnuts.technicaltest.app.Orchestrator;
import com.visualnuts.technicaltest.base.BaseTestSuite;
import com.visualnuts.technicaltest.service.LanguagesByCountryService;
import com.visualnuts.technicaltest.service.VisualNutsAlgorithmService;

public class OrchestratorTest extends BaseTestSuite {
	
	@Test
	void initialize() {
		orchestrator.initialize();
		verify(visualNutsAlgorithm).printNumberUpToLimit(Mockito.eq(100));
		verify(languagesByCountryService).countNumbersOfCountriesInTheWorld();
		verify(languagesByCountryService).findCountryWithMostOfficialLanguageWhereSpeakGerman();
		verify(languagesByCountryService).countAllOfficialLanguages();
		verify(languagesByCountryService).findCountryWithHighestNumberOfOfficialLanguages();
		verify(languagesByCountryService).findMostCommonOfficialLanguage();
	}
	
	

	@Mock
	private VisualNutsAlgorithmService visualNutsAlgorithm;

	@Mock
	private LanguagesByCountryService languagesByCountryService;
	
	@InjectMocks
	private Orchestrator orchestrator;

}
