package es.uji.curso.contactlist.persistence;

import java.io.IOException;
import java.util.List;

public interface LinesReader {

	List<String> readLines() throws IOException;

}
