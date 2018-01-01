package com.sc.main;

import java.util.Comparator;

public class Country {

	private String name;
	private long population;
	private double popDensity;
	private double square;

	public Country(String name, String population, String popDensity) {
		super();
		this.name = name;
		this.population = Long.parseLong(population);
		this.popDensity = Double.parseDouble(popDensity);
		this.square= this.population/this.popDensity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPopulation() {
		return population;
	}

	public void setPopulation(long population) {
		this.population = population;
	}

	public double getPopDensity() {
		return popDensity;
	}

	public void setPopDensity(double popDensity) {
		this.popDensity = popDensity;
	}

	public double getSquare() {
		return square;
	}

	public void setSquare(double square) {
		this.square = square;
	}

	static Comparator<Country> compareByPopulation() {
		return new Comparator<Country>() {
			@Override
			public int compare(Country o1, Country o2) {
				return (int) (o2.getPopulation() - o1.getPopulation());
			}
		};
	}

	static Comparator<Country> compareByDensity() {
		return new Comparator<Country>() {
			@Override
			public int compare(Country o1, Country o2) {
				return Double.compare(o2.getPopDensity(),o1.getPopDensity());
			}
		};
	}
	
	
	

}
