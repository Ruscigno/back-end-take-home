package com.ruscigno.guestlogix.utils;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CsvLoader {

	private static final Logger log = LoggerFactory.getLogger(CsvLoader.class);
	
	public static <T> List<T> loadObjectList(Class<T> type,  String fileName) {
		try {
			CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
			CsvMapper mapper = new CsvMapper();
			File file = new ClassPathResource(fileName).getFile();
			MappingIterator<T> readValues = mapper.readerFor(type).with(bootstrapSchema).readValues(file);
			return readValues.readAll();
		} catch (IOException e) {
			log.error("Error occurred while loading object list from file " + fileName, e);
			return Collections.emptyList();
		}
	}
}