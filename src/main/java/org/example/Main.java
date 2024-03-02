package org.example;

import java.io.*;

public class Main {
	public static void main(String[] args) {
		Secretariat secretariat = new Secretariat();
		try (FileReader fileReader = new FileReader("src/main/resources/" + args[0] + "/" + args[0] + ".in")) {
			try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					String[] tokens = line.split(" - ");
					switch (tokens[0]) {
						case "adauga_student" : {
							try {
								secretariat.adaugaStudent(tokens[1], tokens[2]);
							} catch (StudentExistaExceptie e) {
								try (FileWriter fileWriter = new FileWriter("src/main/resources/" + args[0] + "/" + args[0] + ".out",
									true)) {
									try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
										bufferedWriter.write("***\n");
										bufferedWriter.write(e.getMessage() + "\n");
									}
								} catch (IOException exceptie) {
									System.out.println(exceptie.getMessage());
								}
							}
						}
						break;
						case "adauga_curs" :
							secretariat.adaugaCurs(tokens[1], tokens[2], Integer.parseInt(tokens[3]));
							break;
						case "citeste_mediile" :
							secretariat.citesteMedii(args[0]);
							break;
						case "posteaza_mediile" :
							secretariat.posteazaMedii(args[0]);
							break;
						case "contestatie" :
							secretariat.contestatie(tokens[1], Double.parseDouble(tokens[2]));
							break;
						case "posteaza_student" :
							secretariat.posteazaStudent(args[0], tokens[1]);
							break;
						case "posteaza_curs" :
							secretariat.posteazaCurs(args[0], tokens[1]);
							break;
						case "adauga_preferinte" :
							secretariat.adaugaPreferinte(tokens);
							break;
						case "repartizeaza" :
							secretariat.repartizeaza();
							break;
					}
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
