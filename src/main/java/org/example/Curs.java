package org.example;

import java.util.Set;
import java.util.TreeSet;

public class Curs<T extends Student> {
	private final String nume;
	private final int capacitate;
	private final Set<T> studenti; // stocare studenti inscrisi la curs

	public Curs(String nume, int capacitate) {
		this.nume = nume;
		this.capacitate = capacitate;
		// initializare pentru comparare dupa nume
		// TreeSet folosit pentru a mentine ordinea alfabetica
		this.studenti = new TreeSet<>((student1, student2) -> {
			// daca numele este acelasi, atunci studentii sunt identici ca sa evitam duplicarea
			if (student1.getNume().equals(student2.getNume())) {
				return 0;
			}
			return student1.getNume().compareTo(student2.getNume());
		});
	}

	public String getNume() {
		return nume;
	}

	public int getCapacitate() {
		return capacitate;
	}

	public Set<T> getStudenti() {
		return studenti;
	}

	public void adaugaStudent(Student student) {
		studenti.add((T) student);
	}
}
