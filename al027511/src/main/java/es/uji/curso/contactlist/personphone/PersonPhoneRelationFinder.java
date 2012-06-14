package es.uji.curso.contactlist.personphone;

import java.io.IOException;
import java.util.List;

public interface PersonPhoneRelationFinder {

	List<String> findPhonesByPersonId(int i) throws IOException;

}
