package es.uji.curso.contactlist.persistence;

import java.io.IOException;

public interface PersonPhoneRelationStorer {
	
	public void storeRelationOf(int person, String phone) throws IOException;

	public void writeLine(String line) throws IOException;
}
