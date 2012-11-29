package edu.unicauca.artis.mongo_test.logic;

import edu.unicauca.artis.mongo_test.model.MongObject;

public class WriterMongo {

	public static MongObject insert_mongobject() {
		MongObject mo = new MongObject();
		mo.put("nombre", "Johan");
		mo.put("apellido", "Tique");
		mo.put("timestamp", System.currentTimeMillis());
		return mo;
		
	}
}
