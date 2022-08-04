package com.visualnuts.technicaltest.service;

import static com.visualnuts.technicaltest.exception.ExceptionCode.NOT_FOUND;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.visualnuts.technicaltest.exception.ApplicationException;
import com.visualnuts.technicaltest.exception.ExceptionCode;
import com.visualnuts.technicaltest.model.CountryData;
import com.visualnuts.technicaltest.util.Assert;
import com.visualnuts.technicaltest.util.JsonUtils;

@Service
public class LanguagesByCountryService {

	private Set<CountryData> countries = Collections.emptySet();
	private static final Logger LOG = LoggerFactory.getLogger(LanguagesByCountryService.class);

	@PostConstruct
	void loadResourceData() throws IOException {
		InputStream inputStream = new ClassPathResource(dataFileName).getInputStream();
		String jsonData = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
		Assert.isTrue(JsonUtils.isValid(jsonData), ExceptionCode.INVALID_DATA);
		countries = new ObjectMapper().readValue(jsonData, new TypeReference<Set<CountryData>>() {
			// Nothing here
		});
	}

	public Long countNumbersOfCountriesInTheWorld() {
		return countries.stream().map(CountryData::country).count();
	}

	public String findCountryWithMostOfficialLanguageWhereSpeakGerman() {
		Optional<CountryData> country = countries.stream()
				.filter(i -> i.languages().stream().anyMatch("de"::equalsIgnoreCase)).max(maxOfficialLanguagesCount());
		return country.orElseThrow(() -> new ApplicationException(NOT_FOUND)).country();
	}

	public Long countAllOfficialLanguages() {
		return countries.stream().flatMap(country -> country.languages().stream()).collect(Collectors.toSet()).stream()
				.count();
	}

	public String findCountryWithHighestNumberOfOfficialLanguages() {
		return countries.stream().max(maxOfficialLanguagesCount()).get().country();
	}

	public String findMostCommonOfficialLanguage() {
		Supplier<Stream<Entry<String, Long>>> result = () -> countries.stream()
				.flatMap(country -> country.languages().stream())
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.sorted((o1, o2) -> Long.compare(o2.getValue(), o1.getValue()));

		if (result.get().count() > 1) {
			// TODO Fix this on next releases
			LOG.warn("More than a single result was found! A specific rule to treat these behaviours is not defined.");
		}
		// When more than one result is found, I'm retrieving the first result
		return result.get().findFirst().get().getKey();
	}

	private Comparator<CountryData> maxOfficialLanguagesCount() {
		return Comparator.comparingInt(c -> c.languages().size());
	}
	
	@Value("countries_data.json")
	private String dataFileName;
}
