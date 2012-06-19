package es.uji.curso.contactlist.persistence;

import java.io.IOException;

public interface LineWriter {

	public void writeLine(String line) throws IOException;
}
