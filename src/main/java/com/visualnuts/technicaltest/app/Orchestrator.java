package com.visualnuts.technicaltest.app;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.visualnuts.technicaltest.service.LanguagesByCountryService;
import com.visualnuts.technicaltest.service.VisualNutsAlgorithmService;

@Component
public class Orchestrator {

	private static final Logger LOG = LoggerFactory.getLogger(Orchestrator.class);

	@PostConstruct
	public void initialize() {
		LOG.info("--------- Exercise 1 ------------" + System.lineSeparator());
		visualNutsAlgorithm.printNumberUpToLimit(100);
		LOG.info("--------- Exercise 1 ------------" + System.lineSeparator());

		LOG.info("--------- Exercise 2 ------------" + System.lineSeparator());
		LOG.info("The number of countries in the world is {}",
				languagesByCountryService.countNumbersOfCountriesInTheWorld());
		LOG.info("The country with the most official languages, where they officially speak German (de) is {}",
				languagesByCountryService.findCountryWithMostOfficialLanguageWhereSpeakGerman());
		LOG.info("Counts all the official languages spoken in the listed countries is {}",
				languagesByCountryService.countAllOfficialLanguages());
		LOG.info("The country with the highest number of official languages is {}",
				languagesByCountryService.findCountryWithHighestNumberOfOfficialLanguages());
		LOG.info("The most common official languages is {}",
				languagesByCountryService.findMostCommonOfficialLanguage());
		LOG.info("--------- Exercise 2 ------------" + System.lineSeparator());
	}

	@Autowired
	private VisualNutsAlgorithmService visualNutsAlgorithm;

	@Autowired
	private LanguagesByCountryService languagesByCountryService;

}
