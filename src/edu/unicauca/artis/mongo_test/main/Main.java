package edu.unicauca.artis.mongo_test.main;

import java.awt.Color;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Arrays;
import java.util.Vector;

import javax.management.MBeanInfo;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.DB;

import edu.unicauca.artis.mongo_test.logic.ParserXML;
import edu.unicauca.artis.mongo_test.logic.WriterMongo;
import edu.unicauca.artis.mongo_test.miscellaneus.Log;
import edu.unicauca.artis.mongo_test.model.MongObject;
import edu.unicauca.artis.mongo_test.model.MongObjectThread;
import edu.unicauca.artis.mongo_test.model.MongoDBConnection;
import edu.unicauca.artis.mongo_test.model.Template;
import edu.unicauca.artis.mongo_test.model.TimeOfLife;
import edu.unicauca.artis.mongo_test.model.mbeanskeleton.MyMBeanAttributeInfo;
import edu.unicauca.artis.mongo_test.model.mbeanskeleton.MyMBeanInfo;
import enigma.console.Console;
import enigma.console.TextAttributes;
import enigma.core.Enigma;

public class Main {
	/**
	 * @param args
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException {

		int DEBUG = 7;

		MongoDBConnection mdbc = MongoDBConnection.getInstance();
		ParserXML pxml = new ParserXML();
		TimeOfLife tol = new TimeOfLife();

		Scanner scanner = new Scanner(System.in);
		System.out.println("********** Recuerde configurar el template para la generación de la BD en MONGO *********");
		while (true) {
			System.out.println("1. Revisar los documentos de la BD...");
			System.out.println("2. Remover y añadir nuevos documentos...");
			System.out.println("3. Remover todos los documentos de la BD...");
			System.out.println("4. Revisar y remover los documentos...");
			System.out.println("5. Realizar una consulta simple en la BD...");
			System.out.println("6. Convertir un documeto de la BD a XML...");
			System.out.println("7. Convertir un documento a XML Mbeans independientes!");
			System.out.println("8. Prueba simple de Mbean a XML...");
			System.out.println("9. Generar una agregacion simple, max ID de la coleccion...");
			System.out.println("10. Salir");
			int selection = scanner.nextInt();
			if (selection == 10) {
				break;
			}
			switch (selection) {
			case 1: {// just watch
				try {
					Runtime.getRuntime().exec("clear");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
			case 4: {// watch and remove
				mdbc.see_all_doc_by_coll();
				mdbc.remove_all_doc_by_coll();
				break;
			}
			case 5: {// find simple query
				System.out.println("Digite el ID del documento a consultar");
				selection = scanner.nextInt();
				tol.set_home_time(System.currentTimeMillis());
				mdbc.get_document_by_key("id", selection);
				tol.set_end_time(System.currentTimeMillis());
				Log.print("tiempo de consulta: " + tol.get_tot_() + " ms");
				break;
			}
			case 6: {// convert to simple xml
				System.out.println("Digite el ID del documento a convertir");
				selection = scanner.nextInt();
				tol.set_home_time(System.currentTimeMillis());
				try {
					BasicDBObject dbo = mdbc.get_document_by_key("id", selection);
					ParserXML.to_convert_simple_xml(dbo);
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (XMLStreamException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FactoryConfigurationError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerFactoryConfigurationError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tol.set_end_time(System.currentTimeMillis());
				Log.print("tiempo de consulta: " + tol.get_tot_() + " ms");
				break;
			}
			case 7: {// convert document to Single XML Mbeans
				System.out.println("Digite el ID del documento a consultar");
				selection = scanner.nextInt();
				tol.set_home_time(System.currentTimeMillis());
				ParserXML.document_to_single_xml_myMBeanInfo(mdbc
						.get_document_by_key("id", selection));
				tol.set_end_time(System.currentTimeMillis());
				Log.print("tiempo de consulta: " + tol.get_tot_() + " ms");
				Log.print("Revise los xml generados en la raiz del proyecto...");
				break;
			}
			case 8: {// test object to XML MBean
				tol.set_home_time(System.currentTimeMillis());
				try {
					Serializer serializer = new Persister();
					MyMBeanInfo test = new MyMBeanInfo();
					test.setClassName("test");
					test.setDescription("descripcion");
					MyMBeanAttributeInfo[] mmbais = new MyMBeanAttributeInfo[5];
					for (int i = 0; i < 5; i++) {
						MyMBeanAttributeInfo mmbai = new MyMBeanAttributeInfo();
						mmbai.setName("atributo_" + i);
						mmbai.setDescription("dexcr");
						mmbai.setIs(false);
						mmbai.setReadable(true);
						mmbai.setType("whatever");
						mmbai.setWritable(true);
						mmbais[i] = mmbai;
					}
					test.setAttributes(mmbais);
					File result = new File("test.xml");
					serializer.write(test, result);
				} catch (Exception e) {
					e.printStackTrace();
				}
				tol.set_end_time(System.currentTimeMillis());
				Log.print("tiempo de consulta: " + tol.get_tot_() + " ms");
				break;
			}
			case 9: {// test aggregation framework
				
				tol.set_home_time(System.currentTimeMillis());
				mdbc.do_test_aggregation();
				tol.set_end_time(System.currentTimeMillis());
				Log.print("tiempo de consulta: " + tol.get_tot_() + " ms");
				break;
			}
			default: {
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
			 * .append("type", "database").append("count", 1) .append("info",
			 * new BasicDBObject("x", 203).append("y", 102));
			 * 
			 * coll.insert(doc);
			 */

			// - finding document

			// System.out.println(coll.getCount());
			// DBObject myDoc = coll.findOne();
			// System.out.println(myDoc);

			/*
			 * DBCursor cursor = coll.find(); try { while (cursor.hasNext()) {
			 * System.out.println(cursor.next()); } } finally { cursor.close();
			 * }
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
			 * DBObject doc = new BasicDBObject(); doc.put("username",
			 * "bwmcadams"); doc.put("password", "MongoN"); coll.insert(doc);
			 */

			// - editing existing doc
			// editing a specific field, if it don't exist then MongoDB to
			// create
			// it, otherwise the value's field is updated
			// myDoc.put("user_time",System.currentTimeMillis());
			// coll.save(myDoc);

			// another way is through of update method, but be careful, because
			// this
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
			 * System.out.println(cursor.next()); } } finally { cursor.close();
			 * }
			 */

			// ***** remove all collections ******//

			/*
			 * cursor = coll.find(); try { while (cursor.hasNext()) {
			 * coll.remove(cursor.next()); } } finally { cursor.close(); }
			 */

			// mongoClient.close();

			// ***** Style Console *****//

			/*
			 * TextAttributes attrs = new TextAttributes(Color.GREEN,
			 * Color.BLACK); s_console.setTextAttributes(attrs);
			 */
			System.out.println("\nPrueba Finalizada!");

		}

		/*
		 * private static final Console s_console; static { s_console =
		 * Enigma.getConsole("MetaConsole"); }
		 */
	}
}
