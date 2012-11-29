package edu.unicauca.artis.mongo_test.main;

import java.awt.Color;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Arrays;
import java.util.Vector;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.DB;

import edu.unicauca.artis.mongo_test.logic.WriterMongo;
import edu.unicauca.artis.mongo_test.miscellaneus.Log;
import edu.unicauca.artis.mongo_test.model.MongObject;
import edu.unicauca.artis.mongo_test.model.MongObjectThread;
import edu.unicauca.artis.mongo_test.model.MongoDBConnection;
import edu.unicauca.artis.mongo_test.model.Template;
import enigma.console.Console;
import enigma.console.TextAttributes;
import enigma.core.Enigma;

public class Main {
	/**
	 * @param args
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException {

		int DEBUG = 2;

		MongoDBConnection mdbc = MongoDBConnection.getInstance();

		switch (DEBUG) {
		case 1: {// just watch
			mdbc.see_all_doc_by_coll();
			break;
		}
		case 2: {// remove and add
			mdbc.remove_all_doc_by_coll();
			Vector<MongObjectThread> mots = new Vector<MongObjectThread>();
			for (int j = 0; j < Template.NUMCLIENTS; j++) {
				MongObjectThread mot = new MongObjectThread();
				mot.set_id(j);
				mots.add(mot);
				mot = null;
				System.gc();
			}

			Iterator iter = mots.iterator();
			while (iter.hasNext()) {
				((MongObjectThread) iter.next()).run();
			}
			break;
		}
		case 3: {// remove
			mdbc.remove_all_doc_by_coll();
			break;
		}
		case 4:{// watch and remove
			mdbc.see_all_doc_by_coll();
			mdbc.remove_all_doc_by_coll();
			break;
		}
		default:{
			Log.print("Te equivocaste de numero :(");
			break;
		}
		}
		

		/*
		 * MongoClient mongoClient = new MongoClient(); DB db =
		 * mongoClient.getDB("test"); Log.print(db);
		 */

		// ***** instance BD Collections *****//

		/*
		 * Set<String> colls = db.getCollectionNames();
		 * 
		 * for (String s : colls) { Log.print(s); }
		 * 
		 * DBCollection coll = db.getCollection("test");
		 */
		// Log.print(coll);

		// - inserting document

		/*
		 * BasicDBObject doc = new BasicDBObject("name", "MongoDB")
		 * .append("type", "database").append("count", 1) .append("info", new
		 * BasicDBObject("x", 203).append("y", 102));
		 * 
		 * coll.insert(doc);
		 */

		// - finding document

		// System.out.println(coll.getCount());
		// DBObject myDoc = coll.findOne();
		// System.out.println(myDoc);

		/*
		 * DBCursor cursor = coll.find(); try { while (cursor.hasNext()) {
		 * System.out.println(cursor.next()); } } finally { cursor.close(); }
		 */

		// - create index

		// coll.createIndex(new BasicDBObject("i", 1));
		/*
		 * List<DBObject> list = coll.getIndexInfo();
		 * 
		 * for (DBObject o : list) { System.out.println(o); }
		 */

		// ***** Inserting docs and fields *****//
		// - inserting fields (put)
		/*
		 * DBObject doc = new BasicDBObject(); doc.put("username", "bwmcadams");
		 * doc.put("password", "MongoN"); coll.insert(doc);
		 */

		// - editing existing doc
		// editing a specific field, if it don't exist then MongoDB to create
		// it, otherwise the value's field is updated
		// myDoc.put("user_time",System.currentTimeMillis());
		// coll.save(myDoc);

		// another way is through of update method, but be careful, because this
		// method modified all the document
		// coll.update(myDoc, new
		// BasicDBObject("user_time",System.currentTimeMillis()));

		// to work with arrays, then proceed as follows
		// coll.update(myDoc, new BasicDBObject("$push",new
		// BasicDBObject("new_field",System.currentTimeMillis())));
		// coll.update(myDoc, new BasicDBObject("$put",new
		// BasicDBObject("new_field",System.currentTimeMillis())));

		// ***** OPERATION KEEPWORK *****//
		/*
		 * BasicDBObject bdbo = new BasicDBObject(); bdbo.put("nombre",
		 * "Edgar"); bdbo.put("apellido", "Tique"); bdbo.put("timestamp",
		 * System.currentTimeMillis()); coll.insert(bdbo);
		 */
		// DBObject myDoc = coll.findOne();
		// BasicDBList bdbl = new BasicDBList();
		// bdbl.add(WriterMongo.create_mongobject(1));
		// bdbl.add(WriterMongo.create_mongobject(2));
		// myDoc.put("hijos",bdbl);
		// coll.save(myDoc);

		// Log.print(coll.insert(WriterMongo.create_mr(2)).toString());

		// ***** show changes *****//

		/*
		 * DBCursor cursor = coll.find(); try { while (cursor.hasNext()) {
		 * System.out.println(cursor.next()); } } finally { cursor.close(); }
		 */

		// ***** remove all collections ******//

		/*
		 * cursor = coll.find(); try { while (cursor.hasNext()) {
		 * coll.remove(cursor.next()); } } finally { cursor.close(); }
		 */

		// mongoClient.close();

		// ***** Style Console *****//
		/*
		 * TextAttributes attrs = new TextAttributes(Color.GREEN, Color.BLACK);
		 * s_console.setTextAttributes(attrs);
		 * System.out.println("\nPrueba Finalizada!");
		 */

	}

	/*
	 * private static final Console s_console; static { s_console =
	 * Enigma.getConsole("MetaConsole"); }
	 */
}
