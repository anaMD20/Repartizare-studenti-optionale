package org.example;

import java.util.ArrayList;

public class Student {
	private final String nume;
	private final ArrayList<Curs<?>> preferinte;
	private double medie;
	private Curs<?> curs;

	public Student(String nume) {
		this.nume = nume;
		this.medie = 0.0;
		this.curs = null;
		this.preferinte = new ArrayList<>();
	}

	public String getNume() {
		return this.nume;
	}

	public double getMedie() {
		return this.medie;
	}

	public void setMedie(double medie) {
		this.medie = medie;
	}

	public Curs<?> getCurs() {
		return this.curs;
	}

	public void setCurs(Curs<?> curs) {
		this.curs = curs;
	}

	public ArrayList<Curs<?>> getPreferinte() {
		return this.preferinte;
	}
}
