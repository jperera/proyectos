package es.uji.curso.contactlist;

import java.io.IOException;
import java.util.List;

public interface ContactListQuery {

	List<String> getPhonesById(int i) throws IOException;

}
