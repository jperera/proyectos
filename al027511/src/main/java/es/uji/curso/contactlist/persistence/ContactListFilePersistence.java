package es.uji.curso.contactlist.persistence;

import java.io.*;

public class ContactListFilePersistence implements LineReader, LineWriter {

	private String fileName;

	public ContactListFilePersistence(String fileName) {
		this.fileName = fileName;
	}

	public void write(String line) throws IOException {
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(line);
			writer.close();
	}

	public String readline() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line = reader.readLine();
		reader.close();
		return line;
	}

}
