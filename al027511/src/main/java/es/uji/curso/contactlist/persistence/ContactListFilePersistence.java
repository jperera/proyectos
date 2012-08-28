package es.uji.curso.contactlist.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactListFilePersistence implements LinesReader, PersonPhoneRelationStorer {

	private static final String WHITE_SPACE = " ";
	private String fileName;

	public ContactListFilePersistence(String fileName) {
		this.fileName = fileName;
	}

	public void storeRelationOf(int person, String phone) throws IOException {
		writeLine(person + WHITE_SPACE + phone);
	}

	public void writeLine(String line) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
		writer.write(line);
		writer.newLine();
		writer.close();
	}

	public List<String> readLines() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		List<String> lines = new ArrayList<String>();
		String line = getNextLine(reader);
		while (isProcesableLine(line)) {
			addNewLineToLines(lines, line);
			line = getNextLine(reader);
		}
		reader.close();

		return lines;
	}

	private void addNewLineToLines(List<String> lines, String newLine) {
		lines.add(newLine);
	}

	private String getNextLine(BufferedReader reader) throws IOException {
		return reader.readLine();
	}

	private boolean isProcesableLine(String line) {
		return line != null && !line.isEmpty();
	}


}
