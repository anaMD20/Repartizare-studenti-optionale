package org.example;

import java.io.*;
import java.util.ArrayList;

public class Secretariat {
	private final ArrayList<Student> studenti = new ArrayList<>();
	private final ArrayList<Curs<?>> cursuri = new ArrayList<>();

	// adaugare student nou cu verificare de duplicat
	public void adaugaStudent(String programDeStudii, String numeStudent) throws StudentExistaExceptie {
		for (int i = 0; i < studenti.size(); i++) {
			// arunca exceptie daca numele studentului este duplicat
			if (studenti.get(i).getNume().equals(numeStudent)) {
				throw new StudentExistaExceptie("Student duplicat: " + numeStudent);
			}
		}
		// adaugare student la lista in functie de programul de studii
		if (programDeStudii.equals("licenta")) {
			studenti.add(new StudentLicenta(numeStudent));
		} else if (programDeStudii.equals("master")) {
			studenti.add(new StudentMaster(numeStudent));
		}
	}

	// adaugare un curs nou in functie de programul de studii
	public void adaugaCurs(String programDeStudii, String numeCurs, int capacitate) {
		if (programDeStudii.equals("licenta")) {
			cursuri.add(new Curs<StudentLicenta>(numeCurs, capacitate));
		} else if (programDeStudii.equals("master")) {
			cursuri.add(new Curs<StudentMaster>(numeCurs, capacitate));
		}
	}

	public void citesteMedii(String numeFisier) {
		try {
			for (int i = 1; i < 4; i++) {
				File file = new File("src/main/resources/" + numeFisier + "/" + "note_" + i + ".txt");
				if (!file.exists()) {
					break;
				}
				// citire fisier
				try (FileReader fileReader = new FileReader("src/main/resources/" + numeFisier + "/" + "note_" + i + ".txt")) {
					try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
						String line;
						while ((line = bufferedReader.readLine()) != null) {
							String[] tokens = line.split(" - ");
							for (int j = 0; j < studenti.size(); j++) {
								if (studenti.get(j).getNume().equals(tokens[0])) {
									studenti.get(j).setMedie(Double.parseDouble(tokens[1]));
								}
							}
						}
					}
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	// scriere medii in fisier
	public void posteazaMedii(String numeFisier) {
		// sortare studenti
		studenti.sort((student1, student2) -> {
			if (student1.getMedie() == student2.getMedie()) {
				return student1.getNume().compareTo(student2.getNume());
			} else {
				return Double.compare(student2.getMedie(), student1.getMedie());
			}
		});

		try (FileWriter fileWriter = new FileWriter("src/main/resources/" + numeFisier + "/" + numeFisier + ".out",
			true)) {
			try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
				bufferedWriter.write("***\n");
				for (int i = 0; i < studenti.size(); i++) {
					bufferedWriter.write(studenti.get(i).getNume() + " - " + studenti.get(i).getMedie() + "\n");
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void contestatie(String numeStudent, Double medie) {
		for (int i = 0; i < studenti.size(); i++) {
			// verificare existenta student si modificare medie
			if (studenti.get(i).getNume().equals(numeStudent)) {
				studenti.get(i).setMedie(medie);
			}
		}
	}

	public void posteazaStudent(String numeFisier, String numeStudent) {
		try (FileWriter fileWriter = new FileWriter("src/main/resources/" + numeFisier + "/" + numeFisier + ".out",
			true)) {
			try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
				bufferedWriter.write("***\n");
				for (int i = 0; i < studenti.size(); i++) {
					if (studenti.get(i).getNume().equals(numeStudent)) {
						Student student = studenti.get(i);
						if (student instanceof StudentLicenta) {
							bufferedWriter.write("Student Licenta: " + student.getNume() + " - " + student.getMedie() + " - " + student.getCurs().getNume() + "\n");
						} else if (student instanceof StudentMaster) {
							bufferedWriter.write("Student Master: " + student.getNume() + " - " + student.getMedie() + " - " + student.getCurs().getNume() + "\n");
						}
					}
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void posteazaCurs(String numeFisier, String numeCurs) {
		try (FileWriter fileWriter = new FileWriter("src/main/resources/" + numeFisier + "/" + numeFisier + ".out",
			true)) {
			try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
				for (int i = 0; i < cursuri.size(); i++) {
					if (cursuri.get(i).getNume().equals(numeCurs)) {
						Curs<?> curs = cursuri.get(i);
						bufferedWriter.write("***\n");
						bufferedWriter.write(numeCurs + " (" + curs.getCapacitate() + ")\n");
						for (Student student : curs.getStudenti()) {
							bufferedWriter.write(student.getNume() + " - " + student.getMedie() + "\n");
						}
					}
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void adaugaPreferinte(String[] tokens) {
		String numeStudent = tokens[1];
		for (int i = 2; i < tokens.length; i++) {
			// parcurgere cursuri si adaugare preferinte
			for (int j = 0; j < cursuri.size(); j++) {
				if (cursuri.get(j).getNume().equals(tokens[i])) {
					for (int k = 0; k < studenti.size(); k++) {
						if (studenti.get(k).getNume().equals(numeStudent)) {
							studenti.get(k).getPreferinte().add(cursuri.get(j));
						}
					}
				}
			}
		}
	}

	public void repartizeaza() {
		for (int i = 0; i < studenti.size(); i++) {
			for (int j = 0; j < studenti.get(i).getPreferinte().size(); j++) {
				Curs<?> curs = studenti.get(i).getPreferinte().get(j);
				if (curs.getCapacitate() > curs.getStudenti().size()) {
					curs.adaugaStudent(studenti.get(i));
					studenti.get(i).setCurs(curs);
					break;
				} else {
					// verificare egalitate medii pentru a face repartizarea
					for (Student student : curs.getStudenti()) {
						if (student.getMedie() == studenti.get(i).getMedie()) {
							curs.adaugaStudent(studenti.get(i));
							studenti.get(i).setCurs(curs);
							break;
						}
					}
				}
			}
		}
	}
}
