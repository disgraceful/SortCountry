package com.sc.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Main {
	private static final File CSV_FILE = new File("C:\\Users\\Kashapov\\Desktop\\countries.csv");
	private static final File JSON_FILE_POP = new File("C:\\Users\\Kashapov\\Desktop\\jsonPop.json");
	private static final File JSON_FILE_DENS = new File("C:\\Users\\Kashapov\\Desktop\\jsonDens.json");

	public static void main(String[] args) {
		List<Country> countries = new ArrayList<>();

		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE));) {
			while ((line = br.readLine()) != null) {
				String[] splitted = line.split(cvsSplitBy);
				Country country = new Country(splitted[0], splitted[1], splitted[2]);
				countries.add(country);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Country c : sortByPopulation(countries)) {
			System.out.println(c.getName() + " " + c.getPopulation() + " " + c.getPopDensity());
		}
		toJson(countries, JSON_FILE_POP);
		for (Country c : sortByDensity(countries)) {
			System.out.println(c.getName() + " " + c.getPopulation() + " " + c.getPopDensity());
		}
		toJson(countries, JSON_FILE_DENS);
		
		for(Country c:countries ){
			System.out.println(c.getName() + " " + c.getSquare()+"km2");
		}
	}

	public static List<Country> sortByPopulation(List<Country> country) {
		Collections.sort(country, Country.compareByPopulation());
		return country;
	}

	public static List<Country> sortByDensity(List<Country> country) {
		Collections.sort(country, Country.compareByDensity());
		return country;
	}

	public static void toJson(List<Country> countries, File file) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter writer = mapper.writer();
		try {
			writer.writeValue(file, countries);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
